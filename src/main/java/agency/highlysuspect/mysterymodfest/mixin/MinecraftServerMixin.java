package agency.highlysuspect.mysterymodfest.mixin;

import agency.highlysuspect.mysterymodfest.dialogue.DialogueLineLoader;
import agency.highlysuspect.mysterymodfest.dialogue.DialogueLineRegistry;
import agency.highlysuspect.mysterymodfest.util.MinecraftServerExt;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.datafixers.DataFixer;
import net.minecraft.resource.ReloadableResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListenerFactory;
import net.minecraft.server.command.CommandManager;
import net.minecraft.util.UserCache;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.net.Proxy;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin implements MinecraftServerExt {
	@Unique private DialogueLineLoader dialogueLineLoader;
	
	@Shadow @Final private ReloadableResourceManager dataManager;
	
	@Inject(
		method = "<init>",
		at = @At("TAIL")
	)
	private void onConstruct(File gameDir, Proxy proxy, DataFixer dataFixer, CommandManager commandManager, YggdrasilAuthenticationService authService, MinecraftSessionService sessionService, GameProfileRepository gameProfileRepository, UserCache userCache, WorldGenerationProgressListenerFactory worldGenerationProgressListenerFactory, String levelName, CallbackInfo ci) {
		dialogueLineLoader = new DialogueLineLoader();
		dataManager.registerListener(dialogueLineLoader);
	}
	
	@Override public DialogueLineRegistry getDialogueRegistry() {
		return dialogueLineLoader.getRegistry();
	}
}
