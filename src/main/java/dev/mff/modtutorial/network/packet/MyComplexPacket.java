package dev.mff.modtutorial.network.packet;

import dev.mff.modtutorial.object.MyComplexObject;
import net.minecraft.network.PacketBuffer;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyComplexPacket
{

    private Collection<MyComplexObject> myCollection;

    public MyComplexPacket(Collection<MyComplexObject> collection)
    {
        this.myCollection = collection;
    }

    public static void encode(MyComplexPacket pck, PacketBuffer buf)
    {
        // J'écris le nombre d'objets que je vais sérialiser
        buf.writeInt(pck.myCollection.size());

        // Puis je sérialiser chaque objet
        pck.myCollection.forEach(o -> o.writeToBuffer(buf));
    }

    public static MyComplexPacket decode(PacketBuffer buffer)
    {
        // Je lis combien d'objets on été sérialisés
        int collectionSize = buffer.readInt();

        // Puis je lis autant d'objets qui ont été sérialisés
        Collection<MyComplexObject> complexObjects = Stream
                .generate(() -> new MyComplexObject(buffer))
                .limit(collectionSize)
                .collect(Collectors.toList());

        // Enfin je retourne l'instance de mon paquet
        return new MyComplexPacket(complexObjects);
    }

}
