package dev.mff.modtutorial.capability;

import dev.mff.modtutorial.network.TutorialNetwork;
import dev.mff.modtutorial.network.packet.SyncExhaustionPacket;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.network.PacketDistributor;

public class PlayerExhaustionHolder extends ExhaustionHolder
{

    private EntityPlayerMP player;

    public PlayerExhaustionHolder(EntityPlayerMP player)
    {
        this.player = player;
    }

    @Override
    public void setExhaustion(int value)
    {
        super.setExhaustion(value);
        if (player.connection != null)
        {
            player.getCapability(CapabilityExhaustion.EXHAUSTION_CAPABILITY)
                    .ifPresent(capa -> TutorialNetwork.CHANNEL.send(
                            PacketDistributor.PLAYER.with(() -> this.player),
                            new SyncExhaustionPacket(capa))
                    );
        }
    }

}
