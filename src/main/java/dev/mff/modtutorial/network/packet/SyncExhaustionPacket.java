package dev.mff.modtutorial.network.packet;

import dev.mff.modtutorial.capability.CapabilityExhaustion;
import dev.mff.modtutorial.capability.IExhaustable;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncExhaustionPacket
{

    private int exhaustion;

    public SyncExhaustionPacket(IExhaustable instance) {
        this.exhaustion = instance.getExhaustion();
    }

    public SyncExhaustionPacket(int exhaustion)
    {
        this.exhaustion = exhaustion;
    }

    public static void encode(SyncExhaustionPacket pck, PacketBuffer buf)
    {
        buf.writeInt(pck.exhaustion);
    }

    public static SyncExhaustionPacket decode(PacketBuffer buf)
    {
        return new SyncExhaustionPacket(buf.readInt());
    }

    public static void handle(SyncExhaustionPacket pck, Supplier<NetworkEvent.Context> ctxSupplier)
    {
        if(ctxSupplier.get().getDirection().getReceptionSide() == LogicalSide.CLIENT)
            ctxSupplier.get().enqueueWork(() -> handleClientUpdate(pck));
        ctxSupplier.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    private static void handleClientUpdate(SyncExhaustionPacket pck)
    {
        Minecraft.getInstance().player.getCapability(CapabilityExhaustion.EXHAUSTION_CAPABILITY)
                .ifPresent(capa -> capa.setExhaustion(pck.exhaustion));
    }

}
