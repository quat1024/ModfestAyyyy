package agency.highlysuspect.mysterymodfest.dialogue;

import agency.highlysuspect.mysterymodfest.util.PlayerExt;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;
import java.util.Map;

public class VariableStore {
	//TODO maybe expand this to other types besides integers, after getting a bit more of the system sketched in
	private final Map<String, Integer> values = new HashMap<>();
	
	public void put(String key, int value) {
		values.put(key, value);
	}
	
	public int get(String key) {
		return values.getOrDefault(key, 0);
	}
	
	public void incr(String key, int shift) {
		put(key, get(key) + shift);
	}
	
	public CompoundTag toTag() {
		CompoundTag tag = new CompoundTag();
		values.forEach(tag::putInt);
		return tag;
	}
	
	public void fromTag(CompoundTag tag) {
		values.clear();
		for(String key : tag.getKeys()) {
			values.put(key, tag.getInt(key));
		}
	}
	
	public static VariableStore getPersonalStore(PlayerEntity player) {
		return ((PlayerExt) player).getDataStore();
	}
}
