package dev.ebo2022.matterhorn.core;

import dev.ebo2022.matterhorn.common.world.level.levelgen.biome.region.MatterhornRegion;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class Matterhorn {

    public static final String MOD_ID = "matterhorn";

    public static void init() {

    }

    public static void initTerrablender() {
        Regions.register(MatterhornRegion.INSTANCE);
    }

    public static ResourceLocation id(String string) {
        return string.contains(":") ? ResourceLocation.parse(string) : ResourceLocation.fromNamespaceAndPath(MOD_ID, string);
    }
}
