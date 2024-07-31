package dev.ebo2022.matterhorn.common.world.level.levelgen.biome;

import com.mojang.datafixers.util.Pair;
import dev.ebo2022.matterhorn.common.platform.PlatformUtil;
import dev.ebo2022.matterhorn.core.Matterhorn;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.LevelStem;

import java.util.List;

public class MatterhornBiomeParameters {

    private static final ResourceKey<LevelStem> PLACEHOLDER_KEY = ResourceKey.create(Registries.LEVEL_STEM, Matterhorn.id("placeholder"));

    public static List<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> getList(RegistryAccess registryAccess) {
        LevelStem levelStem = PlatformUtil.getCurrentServer()
                .map(MinecraftServer::registryAccess)
                .map(access -> access.registryOrThrow(Registries.LEVEL_STEM).getOrThrow(PLACEHOLDER_KEY))
                .orElseThrow(() -> new IllegalStateException("No biome data found for Matterhorn"));
        if (levelStem.generator().getBiomeSource() instanceof MultiNoiseBiomeSource biomeSource) {
            return biomeSource.parameters().values().stream().map(pair -> pair.mapSecond(holder -> holder.unwrapKey().orElseThrow())).toList();
        } else {
            throw new IllegalStateException("Placeholder biome source is not multi-noise");
        }
    }
}
