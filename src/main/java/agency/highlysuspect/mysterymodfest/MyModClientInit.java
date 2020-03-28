package agency.highlysuspect.mysterymodfest;

import agency.highlysuspect.mysterymodfest.dialogue.DialogueLineRegistry;
import agency.highlysuspect.mysterymodfest.net.MyModPacketsClient;
import net.fabricmc.api.ClientModInitializer;

@SuppressWarnings("unused")
public class MyModClientInit implements ClientModInitializer {
	public static DialogueLineRegistry clientSideDialogueLineRegistry = new DialogueLineRegistry(); 
	
	@Override
	public void onInitializeClient() {
		MyModPacketsClient.onInitialize();
	}
}
