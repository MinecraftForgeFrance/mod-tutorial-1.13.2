package dev.mff.modtutorial.capability;

import dev.mff.modtutorial.ModTutorial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

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
        if(event.getObject() instanceof EntityLivingBase && !event.getObject().world.isRemote)
        {
            PlayerExhaustionWrapper wrapper = new PlayerExhaustionWrapper();
            event.addCapability(CAP_KEY, wrapper);
        }
    }

}
