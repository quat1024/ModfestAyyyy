package agency.highlysuspect.mysterymodfest.dialogue;

import agency.highlysuspect.mysterymodfest.util.JsonHelper2;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.JsonHelper;

import java.util.List;

public class DialogueLineType {
	public DialogueLineType(String text, List<String> variableSubstitutions, List<DialogueAction> preActions, List<DialogueAction> postActions, List<NextOption> nextOptions) {
		this.text = text;
		this.variableSubstitutions = variableSubstitutions;
		this.preActions = preActions;
		this.postActions = postActions;
		this.nextOptions = nextOptions;
	}
	
	private final String text;
	private final List<String> variableSubstitutions;
	
	private final List<DialogueAction> preActions;
	private final List<DialogueAction> postActions;
	
	private final List<NextOption> nextOptions;
	
	public static DialogueLineType fromJson(JsonElement je) {
		JsonObject j = JsonHelper2.ensureType(je, JsonObject.class);
		
		return new DialogueLineType(
			JsonHelper.getString(j, "text"),
			JsonHelper2.getList(j, "formats", JsonElement::getAsString),
			JsonHelper2.getList(j, "pre_actions", DialogueAction::fromJson),
			JsonHelper2.getList(j, "post_actions", DialogueAction::fromJson),
			JsonHelper2.getList(j, "options", NextOption::fromJson)
		);
	}
}
