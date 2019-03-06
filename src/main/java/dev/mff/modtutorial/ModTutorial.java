package dev.mff.modtutorial;

import dev.mff.modtutorial.client.GuiIncinerator;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.network.FMLPlayMessages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.function.Function;

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
	}

	private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Mod tutorial client setup");
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> this::dispatchGui); // enregistrement du gestionnaire de menus
    }

    /**
     * Cette méthode permet de donner le Gui correspondant au container ouvert. Elle permet aussi d'utiliser les informations additionnelles
     * via {@link FMLPlayMessages.OpenContainer#getAdditionalData()}
     * @param container les informations sur le container ouvert
     * @return un GuiScreen correspondant au container, ou 'null' si aucun n'a été trouvé
     */
    private GuiScreen dispatchGui(FMLPlayMessages.OpenContainer container) {
        ResourceLocation id = container.getId();
        if(id.equals(ContainerIncinerator.ID)) {
            return new GuiIncinerator();
        }
        // aucun id correspondant
        return null;
    }

	private void serverSetup(final FMLDedicatedServerSetupEvent event) {
		LOGGER.info("Mod tutorial server setup");
	}
}
