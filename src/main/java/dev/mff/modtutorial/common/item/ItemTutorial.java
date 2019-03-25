package dev.mff.modtutorial.common.item;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemTutorial extends Item {

    public ItemTutorial(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TextComponentString("Hello MFF!"));
    }
}
