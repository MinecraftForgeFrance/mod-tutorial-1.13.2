package dev.mff.modtutorial;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModTutorial.MOD_ID)
public class ModTutorial {

	// la constante avec l'identifiant du mod
	public static final String MOD_ID = "modtutorial";

	// le logger du mod
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	// constructeur de la classe principale, on enregistre ici nos events
	public ModTutorial() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);

		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, this::onCropGrow);
		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, this::onExplosion);
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

	public void onCropGrow(BlockEvent.CropGrowEvent.Pre event) {
	    if (event.getState().getBlock() == Blocks.CACTUS && event.getWorld().getBiome(event.getPos()) != Biomes.DESERT) {
	        event.setResult(Result.DENY);
	    }
	}

	public void onExplosion(ExplosionEvent.Detonate event) {
	    event.getAffectedBlocks().removeIf(pos -> event.getWorld().getBlockState(pos).getBlock() == Blocks.REDSTONE_LAMP);
	    event.getAffectedEntities().removeIf(e -> e instanceof EntityCreeper);
	}
}
