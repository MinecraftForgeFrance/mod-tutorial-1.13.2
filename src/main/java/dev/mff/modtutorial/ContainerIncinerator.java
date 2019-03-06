package dev.mff.modtutorial;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

/**
 * Classe qui permet au client et au serveur de communiquer sur le contenu (et la position) des slots
 */
public class ContainerIncinerator extends Container {

    public static final ResourceLocation ID = new ResourceLocation(ModTutorial.MOD_ID, "container/incinerator");

    public ContainerIncinerator() {

    }

    /**
     * Le joueur donné peut-il interagir avec ce container?
     * @param player le joueur en question
     * @return 'true' si et seulement si le joueur peut interagir
     */
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return false;
    }
}
