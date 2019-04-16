package dev.mff.modtutorial;

import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModTutorial.MOD_ID, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ModTutorialClientEvents {

    @SubscribeEvent
    public static void onOverlay(RenderGameOverlayEvent.Text event) {
        event.getLeft().add("MFF");
    }

    @SubscribeEvent
    public static void onMessage(ClientChatReceivedEvent event) {
        if (event.getMessage() instanceof TextComponentTranslation) {
            TextComponentTranslation translation = (TextComponentTranslation) event.getMessage();
            if (translation.getKey().equals("multiplayer.player.joined")) {
                event.setCanceled(true);
            }
        }
    }
}
