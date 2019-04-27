package dev.mff.modtutorial;

import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ConfigTutorial.SERVER_SPECS);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigTutorial.CLIENT_SPECS);
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (mc, gui) -> gui);
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
