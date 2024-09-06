package dev.ebo2022.matterhorn.common.swappers;

import dev.ebo2022.matterhorn.common.util.PlatformUtil;
import dev.ebo2022.matterhorn.core.integration.WWEEIntegration;
import net.minecraft.world.level.levelgen.DensityFunction;

import static net.minecraft.world.level.levelgen.DensityFunctions.*;

public class DensitySwapper {

    public static DensityFunction temperature(DensityFunction original, DensityFunction continentalness) {
        if (PlatformUtil.isModLoaded(WWEEIntegration.MOD_ID))
            return WWEEDensitySwapper.temperature(original, continentalness);
        return vanillaTemperature(original, continentalness);
    }

    static DensityFunction vanillaTemperature(DensityFunction original, DensityFunction continentalness) {
        // keep ocean biomes as-is
        return rangeChoice(
                continentalness,
                -0.19D,
                2.0D,
                // move j
                rangeChoice(
                        original,
                        0.55D,
                        2.0D,
                        add(
                                original,
                                constant(-0.35D)
                        ),
                        rangeChoice(
                                original,
                                0.2D,
                                0.55D,
                                add(
                                        original,
                                        constant(0.35D)
                                ),
                                original
                        )
                ),
                original
        );
    }
}
