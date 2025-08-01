package abed;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EdibominationFoods implements ModInitializer {
	public static final String MOD_ID = "edibomination-foods";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Foods you probably shouldnt eat are available and on their way!");
	
		// Initialize blocks
		foodPorcessorBlocks.initialize(
			ItemGroupEvents.modifyEntriesEvent(ModItems.CUSTOM_ITEM_GROUP_KEY).register((itemGroup) -> {
				itemGroup.add(foodPorcessorBlocks.FOOD_PROCESSOR.asItem());
			})
		);
	}
}