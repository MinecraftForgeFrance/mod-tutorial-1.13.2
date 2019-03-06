package dev.mff.modtutorial.client;

import dev.mff.modtutorial.ContainerIncinerator;
import net.minecraft.client.gui.inventory.GuiContainer;

/**
 * Classe qui gère l'affichage à l'écran du menu
 */
public class GuiIncinerator extends GuiContainer {
    public GuiIncinerator() {
        super(new ContainerIncinerator());
    }

    /**
     * Permet de dessiner le fond du menu
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }

    /**
     * Permet au menu de réagir lorsque la molette de la souris est bougée
     * @return 'true' si ce menu a utilisé cet événement, 'false' sinon
     */
    @Override
    public boolean mouseScrolled(double wheelDelta) {
        return false;
    }

    /**
     * Permet au menu de réagir à une touche de clavier qui vient d'être relachée
     * @param key l'identifiant de la touche
     * @param scanCode le scan code de la touche
     * @param modifiers les éventuels modifieurs (Control, Shift, etc.) mis en jeu
     * @return 'true' si ce menu a utilisé cet événement, 'false' sinon
     */
    @Override
    public boolean keyReleased(int key, int scanCode, int modifiers) {
        return false;
    }

    /**
     * Permet au menu de réagir quand un caractère a été tapé
     * @param typedChar le caractère
     * @param modifiers les éventuels modifieurs (Control, Shift, etc.) mis en jeu
     * @return 'true' si ce menu a utilisé cet événement, 'false' sinon
     */
    @Override
    public boolean charTyped(char typedChar, int modifiers) {
        return false;
    }

    /**
     * Permet de notifier le menu qu'il a perdu ou gagné le focus
     * @param focusGained 'true' si le focus vient d'être récupéré, 'false' s'il vient d'être perdu
     */
    @Override
    public void focusChanged(boolean focusGained) {

    }

    /**
     * Le menu peut-il être focus?
     * (Utilisé par les GuiTextField pour gérer le fait qu'on clique dessus pour pouvoir les sélectionner et écrire)
     */
    @Override
    public boolean canFocus() {
        return false;
    }
}
