package agency.highlysuspect.mysterymodfest.dialogue;

import agency.highlysuspect.mysterymodfest.util.JsonHelper2;
import agency.highlysuspect.mysterymodfest.util.Junk;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonHelper;

import java.util.function.Function;

public interface DialogueAction {
	void apply(boolean isClient, PlayerEntity player, VariableStore store);
	
	class Set implements DialogueAction {
		public Set(String variable, int value) {
			this.variable = variable;
			this.value = value;
		}
		
		private final String variable;
		private final int value;
		
		@Override
		public void apply(boolean isClient, PlayerEntity player, VariableStore store) {
			store.put(variable, value);
		}
	}
	
	class Increment implements DialogueAction {
		public Increment(String variable, int by) {
			this.variable = variable;
			this.by = by;
		}
		
		private final String variable;
		private final int by;
		
		@Override
		public void apply(boolean isClient, PlayerEntity player, VariableStore store) {
			store.incr(variable, by);
		}
	}
	
	class GiveFixed implements DialogueAction {
		public GiveFixed(ItemStack stack) {
			this.stack = stack;
		}
		
		private final ItemStack stack;
		
		@Override
		public void apply(boolean isClient, PlayerEntity player, VariableStore store) {
			Junk.giveToPlayer(player, stack.copy());
		}
	}
	
	class GiveVar implements DialogueAction {
		public GiveVar(ItemStack stack, String var) {
			this.stack = stack;
			this.var = var;
		}
		
		private final ItemStack stack;
		private final String var;
		
		@Override
		public void apply(boolean isClient, PlayerEntity player, VariableStore store) {
			ItemStack a = stack.copy();
			a.setCount(store.get(var));
			Junk.giveToPlayer(player, a);
		}
	}
	
	static DialogueAction fromJson(JsonElement je) {
		//todo maybe use a registry for these (overengineering warning ayyyy)
		JsonObject j = JsonHelper2.ensureType(je, JsonObject.class);
		
		String type = JsonHelper.getString(j, "action");
		
		switch(type) {
			case "set": {
				String variable = JsonHelper.getString(j, "variable");
				int value = JsonHelper.getInt(j, "value");
				
				return new Set(variable, value);
			}
			case "increment": {
				String variable = JsonHelper.getString(j, "variable");
				int by = JsonHelper.getInt(j, "by", 1);
				
				return new Increment(variable, by);
			}
			case "give_item": {
				Item item = JsonHelper.getItem(j, "item");
				
				Function<VariableStore, ItemStack> stackGetter;
				
				if(j.has("count")) {
					JsonPrimitive prim = JsonHelper2.getJsonPrimitive(j, "count");
					if (prim.isNumber()) {
						int count = prim.getAsInt();
						return new GiveFixed(new ItemStack(item, count));
					} else {
						String var = prim.getAsString();
						return new GiveVar(new ItemStack(item), var);
					}
				} else {
					return new GiveFixed(new ItemStack(item));
				}
			}
			default: {
				throw new JsonParseException("Unknown action: " + type);
			}
		}
	}
}
