package agency.highlysuspect.mysterymodfest;

import agency.highlysuspect.mysterymodfest.net.MyModPacketsCommon;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
public class MyModInit implements ModInitializer {
	public static final String MODID = "mymodayyyy";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	@Override
	public void onInitialize() {
		MyModPacketsCommon.onInitialize();
	}
}
