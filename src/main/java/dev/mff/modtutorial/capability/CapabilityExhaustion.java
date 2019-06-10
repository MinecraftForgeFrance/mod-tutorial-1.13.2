package dev.mff.modtutorial.capability;

import dev.mff.modtutorial.ModTutorial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber(modid = ModTutorial.MOD_ID)
public class CapabilityExhaustion
{

    public static final ResourceLocation CAP_KEY = new ResourceLocation(ModTutorial.MOD_ID, "exhaustion");

    @CapabilityInject(IExhaustable.class)
    public static final Capability<IExhaustable> EXHAUSTION_CAPABILITY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IExhaustable.class, new DefaultExhaustionStorage(), ExhaustionHolder::new);
    }

    public static class DefaultExhaustionStorage implements Capability.IStorage<IExhaustable> {

        @Nullable
        @Override
        public INBTBase writeNBT(Capability<IExhaustable> capability, IExhaustable instance, EnumFacing side)
        {
            return new NBTTagInt(instance.getExhaustion());
        }

        @Override
        public void readNBT(Capability<IExhaustable> capability, IExhaustable instance, EnumFacing side, INBTBase nbt)
        {
            instance.setExhaustion(((NBTTagInt)nbt).getInt());
        }

    }

    @SubscribeEvent
    public static void attachToEntities(AttachCapabilitiesEvent<Entity> event)
    {
        if(event.getObject() instanceof EntityLivingBase)
        {
            IExhaustable holder;
            if(event.getObject() instanceof EntityPlayerMP)
            {
                holder = new PlayerExhaustionHolder((EntityPlayerMP)event.getObject());
            }
            else
            {
                holder = CapabilityExhaustion.EXHAUSTION_CAPABILITY.getDefaultInstance();
            }

            PlayerExhaustionWrapper wrapper = new PlayerExhaustionWrapper(holder);
            event.addCapability(CAP_KEY, wrapper);

            if(event.getObject() instanceof EntityPlayer)
            {
                event.addListener(() -> wrapper.getCapability(CapabilityExhaustion.EXHAUSTION_CAPABILITY).ifPresent(cap -> INVALIDATED_CAPS.put(event.getObject(), cap)));
            }
        }
    }

    private static final Map<Entity, IExhaustable> INVALIDATED_CAPS = new WeakHashMap<>();

    @SubscribeEvent
    public static void copyCapabilities(PlayerEvent.Clone event)
    {
        if(event.isWasDeath())
        {
            event.getEntityPlayer().getCapability(CapabilityExhaustion.EXHAUSTION_CAPABILITY).ifPresent(newCapa -> {
                if(INVALIDATED_CAPS.containsKey(event.getOriginal()))
                {
                    INBTBase nbt = CapabilityExhaustion.EXHAUSTION_CAPABILITY.writeNBT(INVALIDATED_CAPS.get(event.getOriginal()), null);
                    CapabilityExhaustion.EXHAUSTION_CAPABILITY.readNBT(newCapa, null, nbt);
                }
            });
        }
    }

}
