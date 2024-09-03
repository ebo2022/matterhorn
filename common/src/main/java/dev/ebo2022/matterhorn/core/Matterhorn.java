package dev.ebo2022.matterhorn.core;

import com.mojang.logging.LogUtils;
import dev.ebo2022.matterhorn.common.util.PlatformUtil;
import dev.ebo2022.matterhorn.core.integration.TerraBlenderDensityFunctions;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;
import org.slf4j.Logger;

import java.util.function.BiConsumer;

public class Matterhorn {

    public static final String MOD_ID = "matterhorn";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {

    }

    public static void registerDensityFunctions(BiConsumer<ResourceLocation, KeyDispatchDataCodec<? extends DensityFunction>> registry) {
        if (PlatformUtil.isModLoaded("terrablender"))
            TerraBlenderDensityFunctions.register(registry);
    }

    public static ResourceLocation id(String string) {
        return string.contains(":") ? ResourceLocation.parse(string) : ResourceLocation.fromNamespaceAndPath(MOD_ID, string);
    }

    public static <T> ResourceKey<T> key(ResourceKey<? extends Registry<T>> registry, String string) {
        return ResourceKey.create(registry, id(string));
    }
}
