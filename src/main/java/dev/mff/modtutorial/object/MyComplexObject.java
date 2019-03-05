package dev.mff.modtutorial.object;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

import java.util.UUID;

public class MyComplexObject
{

    private String foo;
    private int bar;
    private UUID foobar;
    private ItemStack stack;

    public MyComplexObject(PacketBuffer buffer)
    {
        this.foo = buffer.readString(32767); // 32767 correspond à la longueur max de la chaine de caractères
        this.bar = buffer.readInt();
        this.foobar = buffer.readUniqueId();
        this.stack = buffer.readItemStack();
    }

    public void writeToBuffer(PacketBuffer buffer)
    {
        buffer.writeString(this.foo);
        buffer.writeInt(this.bar);
        buffer.writeUniqueId(this.foobar);
        buffer.writeItemStack(this.stack);
    }

}
