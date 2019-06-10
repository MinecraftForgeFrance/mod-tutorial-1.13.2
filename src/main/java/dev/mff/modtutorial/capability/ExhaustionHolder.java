package dev.mff.modtutorial.capability;

public class ExhaustionHolder implements IExhaustable
{

    private int exhaustion = 0;

    @Override
    public int getExhaustion()
    {
        return this.exhaustion;
    }

    @Override
    public void setExhaustion(int value)
    {
        this.exhaustion = clamp(value);
    }

    private int clamp(int value) {
        if(value > 10000) return 10000;
        if(value < 0) return 0;
        return value;
    }

}
