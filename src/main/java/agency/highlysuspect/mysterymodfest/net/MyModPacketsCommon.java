package agency.highlysuspect.mysterymodfest.net;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public class MyModPacketsCommon {
	public static void onInitialize() {
		ServerSidePacketRegistry.INSTANCE.register(MyModPackets.DIALOGUE_CHOICE, (ctx, buf) -> {
			int choice = buf.readInt();
			
			ctx.getTaskQueue().execute(() -> {
				PlayerEntity player = ctx.getPlayer();
				
			});
		});
	}
	
	public static void openDialogueScreen(ServerPlayerEntity player, Identifier dialogueId) { //TODO more params, pass them to client
		PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
		
		buf.writeIdentifier(dialogueId);
		
		ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, MyModPackets.OPEN_DIALOGUE_UI, buf);
	}
	
	public static void closeDialogueScreen(ServerPlayerEntity player) {
		ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, MyModPackets.CLOSE_DIALOGUE_UI, new PacketByteBuf(Unpooled.buffer()));
	}
}
