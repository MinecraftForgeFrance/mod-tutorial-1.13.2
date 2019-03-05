package dev.mff.modtutorial.network.packet;

import dev.mff.modtutorial.ModTutorial;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MySimplePacket
{

    private int value;

    public MySimplePacket(int value)
    {
        this.value = value;
    }

    public static void encode(MySimplePacket packet, PacketBuffer buffer)
    {
        buffer.writeInt(packet.value);
    }

    public static MySimplePacket decode(PacketBuffer buffer)
    {
        return new MySimplePacket(buffer.readInt());
    }

    public static void handle(MySimplePacket packet, Supplier<NetworkEvent.Context> ctxProvider)
    {
        ModTutorial.LOGGER.debug("Le paquet est arrivé, il contient la valeur {}", packet.value);
        ctxProvider.get().setPacketHandled(true);
    }

}
