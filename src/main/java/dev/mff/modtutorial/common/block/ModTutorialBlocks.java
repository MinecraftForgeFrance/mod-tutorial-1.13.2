package dev.mff.modtutorial.common.block;

import dev.mff.modtutorial.ModTutorial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = ModTutorial.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTutorialBlocks {

	@ObjectHolder(ModTutorial.MOD_ID + ":block_tutorial")
	public static final Block BLOCK_TUTORIAL = null;

	@SubscribeEvent
	public static void registerBlock(final RegistryEvent.Register<Block> event) {
		event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(25F, 600F)).setRegistryName("block_tutorial"));
	}

	@SubscribeEvent
	public static void registerItem(final RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new ItemBlock(BLOCK_TUTORIAL, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(BLOCK_TUTORIAL.getRegistryName()));
	}
}
