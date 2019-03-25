package dev.mff.modtutorial.common.item;

import dev.mff.modtutorial.ModTutorial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = ModTutorial.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTutorialItems {

    @ObjectHolder(ModTutorial.MOD_ID + ":item_tutorial")
    public static final Item ITEM_TUTORIAL = null;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemTutorial(new Item.Properties().group(ItemGroup.MISC).maxStackSize(16)).setRegistryName("item_tutorial"));
    }
}
