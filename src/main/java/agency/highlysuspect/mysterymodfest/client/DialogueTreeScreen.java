package agency.highlysuspect.mysterymodfest.client;

import agency.highlysuspect.mysterymodfest.MyModInit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class DialogueTreeScreen extends Screen {
	public DialogueTreeScreen(Identifier dialogueId) {
		super(new TranslatableText(MyModInit.MODID + ".screen.dialoguetree"));
		this.dialogueId = dialogueId;
	}
	
	private Identifier dialogueId;
	
	@Override
	public void render(int mouseX, int mouseY, float delta) {
		//renderBackground(); //probably render my own background instead of the super dark one
		
		drawCenteredString(MinecraftClient.getInstance().textRenderer, "wow cool dialogue tree", width / 2, height / 2, 0xFF0000);
		drawCenteredString(MinecraftClient.getInstance().textRenderer, dialogueId.toString(), width / 2, height / 2 + 20, 0xFFFFFF);
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
