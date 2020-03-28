package agency.highlysuspect.mysterymodfest.mixin;

import agency.highlysuspect.mysterymodfest.dialogue.DialogueLineType;
import agency.highlysuspect.mysterymodfest.net.MyModPacketsCommon;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin extends PlayerEntityMixin {
	@Override public void setCurrentDialogueLine(DialogueLineType line) {
		super.setCurrentDialogueLine(line);
		
		MyModPacketsCommon.closeDialogueScreen((ServerPlayerEntity) (Object) this);
	}
}
