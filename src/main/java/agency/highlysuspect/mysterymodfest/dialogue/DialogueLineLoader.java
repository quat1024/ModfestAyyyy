package agency.highlysuspect.mysterymodfest.dialogue;

import agency.highlysuspect.mysterymodfest.MyModInit;
import agency.highlysuspect.mysterymodfest.util.JsonHelper2;
import com.google.gson.*;
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;

public class DialogueLineLoader implements SimpleResourceReloadListener<DialogueLineRegistry> {
	private static final Identifier ID = new Identifier(MyModInit.MODID, "dialogue_loader");
	@Override
	public Identifier getFabricId() {
		return ID;
	}
	
	private static final Gson GSON = new GsonBuilder().create();
	
	private DialogueLineRegistry finishedRegistry;
	
	@Override
	public CompletableFuture<DialogueLineRegistry> load(ResourceManager resourceManager, Profiler profiler, Executor executor) {
		return CompletableFuture.supplyAsync(() -> {
			profiler.push(MyModInit.MODID + "-load");
			
			DialogueLineRegistry registry = new DialogueLineRegistry();
			
			for(Identifier fileId : resourceManager.findResources("dialogue", s -> s.endsWith(".json"))) {
				//mypack:dialogue/something.json -> mypack:something
				Identifier trimmedId = mapPath(fileId, p -> p.substring(9, p.length() - 5));
				
				try(BufferedReader reader = new BufferedReader(new InputStreamReader(resourceManager.getResource(fileId).getInputStream()))) {
					JsonObject linesObject = GSON.fromJson(reader, JsonObject.class);
					for (Map.Entry<String, JsonElement> entry : linesObject.entrySet()) {
						String idSuffix = entry.getKey();
						JsonObject j = JsonHelper2.ensureType(entry.getValue(), JsonObject.class);
						
						registry.addLine(
							mapPath(trimmedId, p -> p + '/' + idSuffix), //mypack:something { "a": {...}} -> mypack:something/a {...}
							DialogueLineType.fromJson(j)
						);
					}
				} catch(IOException e) {
					MyModInit.LOGGER.error("Problem when reading file " + fileId, e);
				} catch(JsonParseException e) {
					MyModInit.LOGGER.error("Problem parsing json in file " + fileId, e);
				}
			}
			
			profiler.pop();
			return registry;
		}, executor);
	}
	
	private static Identifier mapPath(Identifier id, Function<String, String> mapper) {
		//ur brain on functional programming
		return new Identifier(id.getNamespace(), mapper.apply(id.getPath()));
	}
	
	@Override
	public CompletableFuture<Void> apply(DialogueLineRegistry dialogueLineRegistry, ResourceManager resourceManager, Profiler profiler, Executor executor) {
		return CompletableFuture.supplyAsync(() -> {
			profiler.push(MyModInit.MODID + "-apply");
			
			finishedRegistry = dialogueLineRegistry;
			
			profiler.pop();
			return null;
		}, executor);
	}
	
	public DialogueLineRegistry getRegistry() {
		return finishedRegistry;
	}
}
