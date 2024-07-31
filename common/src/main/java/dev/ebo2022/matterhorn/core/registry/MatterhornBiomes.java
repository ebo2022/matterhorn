package dev.ebo2022.matterhorn.core.registry;

import dev.ebo2022.matterhorn.core.Matterhorn;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class MatterhornBiomes {

    public static final ResourceKey<Biome> LAKE = ResourceKey.create(Registries.BIOME, Matterhorn.id("lake"));
}
