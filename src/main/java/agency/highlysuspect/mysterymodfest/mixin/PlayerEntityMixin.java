package agency.highlysuspect.mysterymodfest.mixin;

import agency.highlysuspect.mysterymodfest.MyModInit;
import agency.highlysuspect.mysterymodfest.dialogue.ConversationParty;
import agency.highlysuspect.mysterymodfest.dialogue.DialogueLineType;
import agency.highlysuspect.mysterymodfest.dialogue.VariableStore;
import agency.highlysuspect.mysterymodfest.util.PlayerExt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements PlayerExt, ConversationParty {
	@Unique private VariableStore variables = new VariableStore();
	@Unique private DialogueLineType currentDialogueLine = null; //TODO change this to a dialogue state, or something
	
	@Unique private static final String VARIABLES = MyModInit.MODID + "-variables";
	@Unique private static final String CURRENT_DIALOGUE_LINE = MyModInit.MODID + "-current-dialogue-line";
	
	@Override
	public VariableStore getVariableStore() {
		return variables;
	}
	
	@Override
	public UUID getConversationUuid() {
		//noinspection ConstantConditions
		return ((Entity) (Object) this).getUuid();
	}
	
	@Override
	public String getConversationName() {
		return ((PlayerEntity) (Object) this).getEntityName();
	}
	
	@Override
	public DialogueLineType getCurrentDialogueLine() {
		return currentDialogueLine;
	}
	
	@Override
	public void setCurrentDialogueLine(DialogueLineType line) {
		currentDialogueLine = line;
	}
	
	@Inject(
		method = "writeCustomDataToTag",
		at = @At("TAIL")
	)
	public void whenWritingData(CompoundTag tag, CallbackInfo ci) {
		tag.put(VARIABLES, getVariableStore().toTag());
	}
	
	@Inject(
		method = "readCustomDataFromTag",
		at = @At("TAIL")
	)
	public void whenReadingData(CompoundTag tag, CallbackInfo ci) {
		getVariableStore().fromTag(tag.getCompound(VARIABLES));
	}
}
