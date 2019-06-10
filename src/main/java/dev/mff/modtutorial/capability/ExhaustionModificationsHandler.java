package dev.mff.modtutorial.capability;

import dev.mff.modtutorial.ModTutorial;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = ModTutorial.MOD_ID)
public class ExhaustionModificationsHandler
{

    private static final UUID MODIFIER_ID = UUID.fromString("73c800ed-7da9-4a6f-8fe1-b106096269f6");

    @SubscribeEvent
    public static void onLivingJump(LivingEvent.LivingJumpEvent event)
    {
        if(!event.getEntity().world.isRemote())
            event.getEntity().getCapability(CapabilityExhaustion.EXHAUSTION_CAPABILITY)
                    .ifPresent(cap -> cap.increaseExhaustion(50));
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingUpdateEvent event)
    {
        if(!event.getEntity().world.isRemote())
            event.getEntity().getCapability(CapabilityExhaustion.EXHAUSTION_CAPABILITY)
                    .ifPresent(cap -> {
                        cap.reduceExhaustion(2);
                        if(event.getEntity().motionX == 0 && event.getEntity().motionY == 0 && event.getEntity().motionZ ==0)
                        {
                            cap.reduceExhaustion(4);
                        }
                        if(event.getEntity().isSprinting())
                        {
                            cap.increaseExhaustion(3);
                        }
                        if(event.getEntity().isSwimming())
                        {
                            cap.increaseExhaustion(3);
                        }

                        // Operations :
                        //  0 : valeur à ajouter à la valeur de base
                        //          valeurFinale = valeurBase + quantite
                        //  1 : pourcentage de la valeur de base à ajouter
                        //          valeurFinale += valeurBase * quantite
                        //  2 : pourcentage de la valeur finale à rajouter en plus
                        //          valeurFinale *= 1 + quantite
                        AttributeModifier modifier = new AttributeModifier(
                                MODIFIER_ID,
                                "exhaustion",
                                - cap.getExhaustion() / 10_000f,
                                2
                        );

                        updateModifierFor(EntityLivingBase.SWIM_SPEED, modifier, event.getEntityLiving());
                        updateModifierFor(SharedMonsterAttributes.MOVEMENT_SPEED, modifier, event.getEntityLiving());
                        updateModifierFor(SharedMonsterAttributes.ATTACK_SPEED, modifier, event.getEntityLiving());

                    });
    }

    @SubscribeEvent
    public static void onLivingEat(LivingEntityUseItemEvent event)
    {
        if(!event.getEntity().world.isRemote())
            event.getEntity().getCapability(CapabilityExhaustion.EXHAUSTION_CAPABILITY).ifPresent(capa -> {
                if(event.getItem().getItem() instanceof ItemFood)
                {
                    ItemFood food = (ItemFood) event.getItem().getItem();
                    capa.reduceExhaustion(food.getUseDuration(event.getItem()));
                }
            });
    }

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event)
    {
        if(!event.getEntity().world.isRemote())
            event.getEntity().getCapability(CapabilityExhaustion.EXHAUSTION_CAPABILITY).ifPresent(capa -> capa.increaseExhaustion(50));
    }

    private static void updateModifierFor(IAttribute attribute, AttributeModifier modifier, EntityLivingBase entity)
    {
        IAttributeInstance instance = entity.getAttribute(attribute);
        if(instance.hasModifier(modifier))
        {
            instance.removeModifier(modifier);
        }

        if(entity instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) entity;
            if(player.isCreative() || player.isSpectator()) return;
        }

        instance.applyModifier(modifier);
    }

}
