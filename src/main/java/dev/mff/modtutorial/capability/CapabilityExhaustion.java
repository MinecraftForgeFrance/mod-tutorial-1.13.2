package dev.mff.modtutorial.capability;

import dev.mff.modtutorial.ModTutorial;
import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = ModTutorial.MOD_ID)
public class CapabilityExhaustion
{

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

}
