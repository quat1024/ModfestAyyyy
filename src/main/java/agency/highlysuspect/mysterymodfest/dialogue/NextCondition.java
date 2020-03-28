package agency.highlysuspect.mysterymodfest.dialogue;

import agency.highlysuspect.mysterymodfest.util.JsonHelper2;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.JsonHelper;

public interface NextCondition {
	boolean test(PlayerEntity player, VariableStore variables);
	
	static NextCondition fromJson(JsonElement je) {
		JsonObject j = JsonHelper2.ensureType(je, JsonObject.class);
		
		String condition = JsonHelper.getString(j, "condition");
		
		//TODO add more of these, maybe put them in a registry kinda like the vanilla adv conditions
		//Oh fuck I can actually use the vanilla adv conditions can't I
		
		//noinspection SwitchStatementWithTooFewBranches
		switch(condition) {
			case "compare_variable": {
				String variable = JsonHelper.getString(j, "variable");
				
				if(j.has("at_least")) {
					int target = JsonHelper.getInt(j, "at_least");
					return (player, variables) -> variables.get(variable) >= target;
				}
				
				if(j.has("at_most")) {
					int target = JsonHelper.getInt(j, "at_most");
					return (player, variables) -> variables.get(variable) <= target;
				}
			}
			default: {
				throw new JsonParseException("Unknown condition " + condition);
			}
		}
	}
}
