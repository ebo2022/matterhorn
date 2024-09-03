package dev.ebo2022.matterhorn.core;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class Matterhorn {

    public static final String MOD_ID = "matterhorn";

    public static void init() {

    }

    public static void registerDensityFunctions() {

    }

    public static ResourceLocation id(String string) {
        return string.contains(":") ? ResourceLocation.parse(string) : ResourceLocation.fromNamespaceAndPath(MOD_ID, string);
    }

    public static <T> ResourceKey<T> key(ResourceKey<? extends Registry<T>> registry, String string) {
        return ResourceKey.create(registry, id(string));
    }
}
