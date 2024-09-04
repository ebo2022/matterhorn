package dev.ebo2022.matterhorn.core.integration;

import dev.ebo2022.matterhorn.common.util.PlatformUtil;
import dev.ebo2022.matterhorn.core.Matterhorn;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.NoiseRouter;

public class LarionIntegration {

    public static final String MOD_ID = "larion";
    private static final ResourceKey<DensityFunction> TEMPERATURE = Matterhorn.key(Registries.DENSITY_FUNCTION, "larion:overworld/temperature_swapped");
    private static final ResourceKey<DensityFunction> VEGETATION = Matterhorn.key(Registries.DENSITY_FUNCTION, "larion:overworld/vegetation_adjusted");

    public static boolean isLarionLoaded(NoiseRouter router) {
        if (PlatformUtil.isModLoaded(MOD_ID)) {
            return true;
        } else {
            // also check for the datapack by looking at the noise router
            return is(router.temperature(), TEMPERATURE) && is(router.vegetation(), VEGETATION);
        }
    }

    private static boolean is(DensityFunction densityFunction, ResourceKey<DensityFunction> key) {
        return densityFunction instanceof DensityFunctions.HolderHolder holderHolder && holderHolder.function().is(key);
    }
}
