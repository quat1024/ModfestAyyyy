package agency.highlysuspect.mysterymodfest.dialogue;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.SimpleRegistry;

public class DialogueLineRegistry {
	SimpleRegistry<DialogueLineType> lines = new SimpleRegistry<>();
	
	public DialogueLineType addLine(Identifier id, DialogueLineType line) {
		return lines.add(id, line);
	}
	
	public Identifier getId(DialogueLineType line) {
		return lines.getId(line);
	}
	
	public DialogueLineType get(Identifier id) {
		return lines.get(id);
	}
}
