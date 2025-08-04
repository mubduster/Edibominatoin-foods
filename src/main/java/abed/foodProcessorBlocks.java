package abed;


import java.util.function.Function;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class foodProcessorBlocks {
    
    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem){
        
        RegistryKey<Block> blockKey = keyOfBlock(name);

        Block block = blockFactory.apply(settings.registryKey(blockKey));

        if (shouldRegisterItem) {

            RegistryKey<Item> itemKey = keyOfItem(name);
            
            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(EdibominationFoods.MOD_ID, name));   
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EdibominationFoods.MOD_ID, name));
    }

    public static final Block FOOD_PROCESSOR = register(
        "food_processor",
        Block::new,
        AbstractBlock.Settings.create().sounds(BlockSoundGroup.WOOD),
        true
    );
    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(foodProcessorItem.CUSTOM_ITEM_GROUP_KEY).register((itemGroup) -> {
            itemGroup.add(foodProcessorBlocks.FOOD_PROCESSOR.asItem());
        });
    }
}

