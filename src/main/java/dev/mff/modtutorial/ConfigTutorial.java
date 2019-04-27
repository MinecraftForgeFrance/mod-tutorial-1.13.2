package dev.mff.modtutorial;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.gui.GuiModList;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = ModTutorial.MOD_ID)
public class ConfigTutorial
{

    public static final ForgeConfigSpec CLIENT_SPECS;
    public static final Client CLIENT;

    public static final ForgeConfigSpec SERVER_SPECS;
    public static final Server SERVER;

    static {
        Pair<Server, ForgeConfigSpec> serverPair = new ForgeConfigSpec.Builder().configure(Server::new);
        SERVER_SPECS = serverPair.getRight();
        SERVER = serverPair.getLeft();

        Pair<Client, ForgeConfigSpec> clientPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPECS = clientPair.getRight();
        CLIENT = clientPair.getLeft();
    }


    public static class Server
    {

        public Server(ForgeConfigSpec.Builder builder)
        {
            builder.comment("Configuration du serveur")
                    .push("server");

            builder.pop();
        }

    }

    public static class Client
    {

        public final ForgeConfigSpec.BooleanValue allowModsGUI;

        public Client(ForgeConfigSpec.Builder builder)
        {
            builder.comment("Configuration du client")
                    .push("client");

            allowModsGUI = builder
                    .comment("Indique si le GUI de la liste des mods peut s'ouvrir")
                    .define("allowModsGUI", false);

            builder.pop();
        }

    }

    @SubscribeEvent
    public static void cancelModsGUI(GuiOpenEvent event)
    {
        if(GuiModList.class.equals(event.getGui().getClass()) && !CLIENT.allowModsGUI.get())
        {
            event.setCanceled(true);
        }
    }

}
