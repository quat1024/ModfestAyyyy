package agency.highlysuspect.mysterymodfest.util;

import com.google.gson.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class JsonHelper2 {
	public static <T extends JsonElement> T ensureType(JsonElement je, Class<T> type) {
		if(je.getClass().isAssignableFrom(type)) {
			//noinspection unchecked
			return (T) je;
		}
		
		throw new JsonParseException("expected a " + type.getSimpleName() + " but found a " + je.getClass().getSimpleName());
	}
	
	public static <T> List<T> getList(JsonObject j, String key, Function<JsonElement, ? extends T> reader) {
		ArrayList<T> list = new ArrayList<>();
		
		JsonElement thing = j.get(key);
		if(thing == null) return list;
		
		if(!thing.isJsonArray()) throw new JsonParseException("expected an array but found a " + thing.getClass().getSimpleName());
		
		JsonArray thingArray = thing.getAsJsonArray();
		thingArray.forEach(elem -> list.add(reader.apply(elem)));
		return list;
	}
	
	public static JsonPrimitive getJsonPrimitive(JsonObject j, String key) {
		JsonElement je = j.get(key);
		if(je.isJsonPrimitive()) return je.getAsJsonPrimitive();
		else throw new JsonParseException("expected a json primitive, found a " + je.getClass().getSimpleName());
	}
	
	public static Identifier getIdentifier(JsonObject j, String key) {
		return new Identifier(JsonHelper.getString(j, key));
	}
}
