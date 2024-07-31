package dev.ebo2022.matterhorn.common.world.level.levelgen.biome.region;

import com.mojang.datafixers.util.Pair;
import dev.ebo2022.matterhorn.common.platform.PlatformUtil;
import dev.ebo2022.matterhorn.common.world.level.levelgen.biome.MatterhornBiomeParameters;
import dev.ebo2022.matterhorn.core.Matterhorn;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class MatterhornRegion extends Region {

    public static final Region INSTANCE = new MatterhornRegion();

    private MatterhornRegion() {
        super(Matterhorn.id("overworld"), RegionType.OVERWORLD, 1);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        MatterhornBiomeParameters.getList(PlatformUtil.getCurrentServer().map(MinecraftServer::registryAccess).orElseThrow()).forEach(mapper);
    }
}
