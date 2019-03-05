package dev.mff.modtutorial;

import dev.mff.modtutorial.network.TutorialNetwork;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	}

	private void setup(final FMLCommonSetupEvent event) {
		LOGGER.info("Mod tutorial setup");

		TutorialNetwork.registerNetworkPackets();
	}

	private void clientSetup(final FMLClientSetupEvent event) {
		LOGGER.info("Mod tutorial client setup");
	}

	private void serverSetup(final FMLDedicatedServerSetupEvent event) {
		LOGGER.info("Mod tutorial server setup");
	}
}
