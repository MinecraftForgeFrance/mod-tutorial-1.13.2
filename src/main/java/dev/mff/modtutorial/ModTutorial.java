package dev.mff.modtutorial;

import dev.mff.modtutorial.common.block.ModTutorialBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.command.Commands;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CommandEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.lang.reflect.Field;

@Mod(ModTutorial.MOD_ID)
public class ModTutorial {

	// la constante avec l'identifiant du mod
	public static final String MOD_ID = "modtutorial";

	// le logger du mod
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public static final ItemGroup MFF_GROUP = new ItemGroup("mff") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModTutorialBlocks.BLOCK_TUTORIAL);
		}
	};

    public static final ItemGroup MFF_GROUP_SEARCH = (new ItemGroup("enchant") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.ENCHANTED_BOOK);
        }

		@Override
		public boolean hasSearchBar() {
			return true;
		}

        @Override
        public void fill(NonNullList<ItemStack> items) {
            super.fill(items);

			if(Minecraft.getInstance().player.getCommandSource().hasPermissionLevel(4))
				items.add(0, new ItemStack(Items.DEBUG_STICK));
        }

        }).setNoTitle().setBackgroundImageName("mff_search.png").setRelevantEnchantmentTypes(EnumEnchantmentType.values());

	// constructeur de la classe principale, on enregistre ici nos events
	public ModTutorial() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);
	}

	private void setup(final FMLCommonSetupEvent event) {
		LOGGER.info("Mod tutorial setup");
	}

	private void clientSetup(final FMLClientSetupEvent event) {
		LOGGER.info("Mod tutorial client setup");
	}

	private void serverSetup(final FMLDedicatedServerSetupEvent event) {
		LOGGER.info("Mod tutorial server setup");
	}
}
