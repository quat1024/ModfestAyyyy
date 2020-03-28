package agency.highlysuspect.mysterymodfest.dialogue;

import agency.highlysuspect.mysterymodfest.util.JsonHelper2;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.util.List;

public class NextOption {
	public NextOption(Identifier nextId, String text, List<String> formats, List<NextCondition> conditions) {
		this.nextId = nextId;
		this.text = text;
		this.formats = formats;
		this.conditions = conditions;
	}
	
	private final Identifier nextId;
	
	//maybe break this out into its own "variablyformattedstring" class or something
	//cuz this pattern's pretty common
	private final String text;
	private final List<String> formats;
	
	private final List<NextCondition> conditions;
	
	public static NextOption fromJson(JsonElement je) {
		JsonObject j = JsonHelper2.ensureType(je, JsonObject.class);
		
		return new NextOption(
			JsonHelper2.getIdentifier(j, "target"),
			JsonHelper.getString(j, "text"),
			JsonHelper2.getList(j, "formats", JsonElement::getAsString),
			JsonHelper2.getList(j, "conditions", NextCondition::fromJson)
		);
	}
}
