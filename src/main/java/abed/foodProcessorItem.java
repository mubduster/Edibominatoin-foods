package abed;

import java.util.function.Function;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class foodProcessorItem {
    public static final Item register(String name, Function<Item.Settings,Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EdibominationFoods.MOD_ID, name));
        
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }
 
    public static final Item FOOD_PROCESSOR_CATEGORY_ITEM = register("142367the_food_processor_category_item", Item::new, new Item.Settings());

    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(EdibominationFoods.MOD_ID, "item_group")); 
    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
        .icon(() -> new ItemStack(foodProcessorBlocks.FOOD_PROCESSOR))
        .displayName(Text.translatable("mod-category"))
        .build();

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
    }
}
