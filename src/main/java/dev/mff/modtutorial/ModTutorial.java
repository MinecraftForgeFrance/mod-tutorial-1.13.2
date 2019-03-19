package dev.mff.modtutorial;

import dev.mff.modtutorial.commands.SetFireCommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
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

		MinecraftForge.EVENT_BUS.addListener(this::serverStartingEvent);
	}

	private void serverStartingEvent(FMLServerStartingEvent event)
	{
		SetFireCommand.register(event.getCommandDispatcher());
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
