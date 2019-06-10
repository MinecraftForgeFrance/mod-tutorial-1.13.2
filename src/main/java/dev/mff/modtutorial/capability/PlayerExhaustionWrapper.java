package dev.mff.modtutorial.capability;

import net.minecraft.nbt.INBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerExhaustionWrapper implements ICapabilitySerializable<INBTBase>
{

    private IExhaustable holder;
    private final LazyOptional<IExhaustable> lazyOptional = LazyOptional.of(() -> this.holder);

    public PlayerExhaustionWrapper(IExhaustable holder) {
        this.holder = holder;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable EnumFacing side)
    {
        return CapabilityExhaustion.EXHAUSTION_CAPABILITY.orEmpty(cap, lazyOptional);
    }

    @Override
    public INBTBase serializeNBT()
    {
        return CapabilityExhaustion.EXHAUSTION_CAPABILITY.writeNBT(this.holder, null);
    }

    @Override
    public void deserializeNBT(INBTBase nbt)
    {
        CapabilityExhaustion.EXHAUSTION_CAPABILITY.readNBT(holder, null, nbt);
    }

}
