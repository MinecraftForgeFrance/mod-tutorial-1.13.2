package dev.mff.modtutorial.network;

import dev.mff.modtutorial.ModTutorial;
import dev.mff.modtutorial.network.packet.SyncExhaustionPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class TutorialNetwork
{

    public static final String PROTOCOL_VERSION = String.valueOf(1);

    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(ModTutorial.MOD_ID, "my_channel"))
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .simpleChannel();

    public static void registerNetworkPackets()
    {
        ModTutorial.LOGGER.debug("Registering packets for mod {}", ModTutorial.MOD_ID);
        CHANNEL.messageBuilder(SyncExhaustionPacket.class, 0)
                .encoder(SyncExhaustionPacket::encode)
                .decoder(SyncExhaustionPacket::decode)
                .consumer(SyncExhaustionPacket::handle)
                .add();
    }

}
