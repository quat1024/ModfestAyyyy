package agency.highlysuspect.mysterymodfest.net;

import agency.highlysuspect.mysterymodfest.MyModInit;
import net.minecraft.util.Identifier;

public class MyModPackets {
	static final Identifier OPEN_DIALOGUE_UI = new Identifier(MyModInit.MODID, "open_dialogue_ui");
	static final Identifier CLOSE_DIALOGUE_UI = new Identifier(MyModInit.MODID, "close_dialogue_ui");
	
	static final Identifier DIALOGUE_LINE = new Identifier(MyModInit.MODID, "dialogue");
	
	static final Identifier DIALOGUE_CHOICE = new Identifier(MyModInit.MODID, "dialogue_click");
}
