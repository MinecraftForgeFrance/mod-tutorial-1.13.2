package dev.mff.modtutorial.network;

import dev.mff.modtutorial.ModTutorial;
import dev.mff.modtutorial.network.packet.MySimplePacket;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
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

        CHANNEL.messageBuilder(MySimplePacket.class, 0)
                .encoder(MySimplePacket::encode)
                .decoder(MySimplePacket::decode)
                .consumer(MySimplePacket::handle)
                .add();
    }

    public static void sendMySimplePacketToServer(int valueToSend)
    {
        CHANNEL.sendToServer(new MySimplePacket(valueToSend));

        // La ligne suivante est équivalente à la ligne précédente

        /*
            CHANNEL.send(PacketDistributor.SERVER.noArg(), new MySimplePacket(valueToSend));
        */
    }

    public static void sendMySimplePacketTo(EntityPlayerMP player, int valueToSend)
    {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new MySimplePacket(valueToSend));
    }

    public static void sendMySimplePacketToEveryone(int valueToSend)
    {
        CHANNEL.send(PacketDistributor.ALL.noArg(), new MySimplePacket(valueToSend));
    }

    public static void sendMySimplePacketToDimension(DimensionType dimension, int valueToSend)
    {
        CHANNEL.send(PacketDistributor.DIMENSION.with(() -> dimension), new MySimplePacket(valueToSend));
    }

}
