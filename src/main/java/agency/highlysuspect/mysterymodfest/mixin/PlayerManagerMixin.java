package agency.highlysuspect.mysterymodfest.mixin;

import agency.highlysuspect.mysterymodfest.util.PlayerExt;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
	@Shadow @Final private List<ServerPlayerEntity> players;
	
	@Inject(
		method = "onDataPacksReloaded",
		at = @At("TAIL")
	)
	public void whenReloading(CallbackInfo ci) {
		players.forEach(p -> ((PlayerExt) p).setCurrentDialogueLine(null));
	}
}
