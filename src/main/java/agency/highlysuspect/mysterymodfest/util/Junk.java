package agency.highlysuspect.mysterymodfest.util;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class Junk {
	/**
	 * Gives an item to a player, or if their inventory's full, spawns it at their feet.
	 * Like the /give command. Which is where I copied this stuff from basically.
	 */
	public static void giveToPlayer(PlayerEntity player, ItemStack stack) {
		boolean didntFit = player.inventory.insertStack(stack);
		
		//TODO: Mahjongo spawns an item entity even on the happy path, where it does fit in your inv.
		//What's up with that? do I need to?
		
		player.world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((player.getRandom().nextFloat() - player.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
		
		if(didntFit && !stack.isEmpty()) {
			ItemEntity ent = player.dropItem(stack, false);
			if(ent != null) {
				ent.resetPickupDelay();
				ent.setOwner(player.getUuid());
			}
		} else {
			player.playerContainer.sendContentUpdates();
		}
	}
}
