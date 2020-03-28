package agency.highlysuspect.mysterymodfest.mixin;

import agency.highlysuspect.mysterymodfest.net.MyModPacketsCommon;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieEntity.class)
public class TempMixinZombie {
	@Inject(
		method = "interactMob",
		at = @At("HEAD"),
		cancellable = true
	)
	public void whenInteracting(PlayerEntity player, Hand hand, CallbackInfoReturnable<Boolean> cir) {
		if(player.world.isClient) return;
		
		ItemStack held = player.getStackInHand(hand);
		if(held.getItem() == Items.CARROT) { //Silly thing just to get the UI opening before i implement my own mob to click on to open the ui
			MyModPacketsCommon.openDialogueScreen((ServerPlayerEntity) player, new Identifier("mymodayyyy", "test/first"));
			cir.setReturnValue(true);
		}
	}
}
