package agency.highlysuspect.mysterymodfest.net;

import agency.highlysuspect.mysterymodfest.client.DialogueTreeScreen;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public class MyModPacketsClient {
	public static void onInitialize() {
		ClientSidePacketRegistry.INSTANCE.register(MyModPackets.OPEN_DIALOGUE_UI, (ctx, buf) -> {
			Identifier lineId = buf.readIdentifier();
			
			ctx.getTaskQueue().execute(() -> {
				MinecraftClient.getInstance().openScreen(new DialogueTreeScreen(lineId));
			});
		});
		
		ClientSidePacketRegistry.INSTANCE.register(MyModPackets.CLOSE_DIALOGUE_UI, (ctx, buf) -> {
			ctx.getTaskQueue().execute(() -> {
				MinecraftClient client = MinecraftClient.getInstance();
				if(client.currentScreen instanceof DialogueTreeScreen) {
					client.currentScreen.onClose();
				}
			});
		});
		
		ClientSidePacketRegistry.INSTANCE.register(MyModPackets.DIALOGUE_LINE, (ctx, buf) -> {
			ctx.getTaskQueue().execute(() -> {
				//add the dialogue line to the client side packet registry?
			});
		});
	}
}
