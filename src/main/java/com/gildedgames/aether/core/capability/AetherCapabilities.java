package com.gildedgames.aether.core.capability;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.core.capability.capabilities.arrow.PhoenixArrow;
import com.gildedgames.aether.core.capability.capabilities.arrow.PhoenixArrowProvider;
import com.gildedgames.aether.core.capability.capabilities.arrow.PhoenixArrowStorage;
import com.gildedgames.aether.core.capability.capabilities.cape.CapeEntity;
import com.gildedgames.aether.core.capability.capabilities.cape.CapeEntityProvider;
import com.gildedgames.aether.core.capability.capabilities.cape.CapeEntityStorage;
import com.gildedgames.aether.core.capability.capabilities.entity.AetherEntity;
import com.gildedgames.aether.core.capability.capabilities.entity.AetherEntityProvider;
import com.gildedgames.aether.core.capability.capabilities.entity.AetherEntityStorage;
import com.gildedgames.aether.core.capability.capabilities.player.AetherPlayer;
import com.gildedgames.aether.core.capability.capabilities.player.AetherPlayerProvider;
import com.gildedgames.aether.core.capability.capabilities.player.AetherPlayerStorage;
import com.gildedgames.aether.core.capability.interfaces.IAetherEntity;
import com.gildedgames.aether.core.capability.interfaces.IAetherPlayer;

import com.gildedgames.aether.core.capability.interfaces.ICapeEntity;
import com.gildedgames.aether.core.capability.interfaces.IPhoenixArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

public class AetherCapabilities
{

	@CapabilityInject(IAetherEntity.class)
	public static final Capability<IAetherEntity> AETHER_ENTITY_CAPABILITY = null;

	@CapabilityInject(ICapeEntity.class)
	public static final Capability<ICapeEntity> CAPE_ENTITY_CAPABILITY = null;

	@CapabilityInject(IAetherPlayer.class)
	public static final Capability<IAetherPlayer> AETHER_PLAYER_CAPABILITY = null;

	@CapabilityInject(IPhoenixArrow.class)
	public static final Capability<IPhoenixArrow> PHOENIX_ARROW_CAPABILITY = null;

	public static void register() {
		CapabilityManager.INSTANCE.register(IAetherEntity.class, new AetherEntityStorage(), () -> null);
		CapabilityManager.INSTANCE.register(ICapeEntity.class, new CapeEntityStorage(), () -> null);
		CapabilityManager.INSTANCE.register(IAetherPlayer.class, new AetherPlayerStorage(), () -> null);
		CapabilityManager.INSTANCE.register(IPhoenixArrow.class, new PhoenixArrowStorage(), () -> null);
	}
	
	@EventBusSubscriber(modid = Aether.MODID)
	public static class Registration
	{
		@SubscribeEvent
		public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof LivingEntity) {
				event.addCapability(new ResourceLocation(Aether.MODID, "aether_entity"), new AetherEntityProvider(new AetherEntity((LivingEntity) event.getObject())));
				event.addCapability(new ResourceLocation(Aether.MODID, "cape_entity"), new CapeEntityProvider(new CapeEntity((LivingEntity) event.getObject())));
				if (event.getObject() instanceof PlayerEntity) {
					event.addCapability(new ResourceLocation(Aether.MODID, "aether_player"), new AetherPlayerProvider(new AetherPlayer((PlayerEntity) event.getObject())));
				}
			}
			if (event.getObject() instanceof AbstractArrowEntity) {
				event.addCapability(new ResourceLocation(Aether.MODID, "phoenix_arrow"), new PhoenixArrowProvider(new PhoenixArrow((AbstractArrowEntity) event.getObject())));
			}
		}
	}
}
