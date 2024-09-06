package dev.ebo2022.matterhorn.common.swappers;

import de.cristelknight.wwee.ExpandedEcosphere;
import dev.ebo2022.matterhorn.common.util.PlatformUtil;
import dev.ebo2022.matterhorn.core.integration.TerraBlenderIntegration;
import dev.ebo2022.matterhorn.core.integration.WWEEIntegration;
import net.minecraft.world.level.levelgen.DensityFunction;

import static net.minecraft.world.level.levelgen.DensityFunctions.*;
import static dev.ebo2022.matterhorn.common.swappers.DensitySwapper.vanillaTemperature;

public class WWEEDensitySwapper {

    static DensityFunction temperature(DensityFunction original, DensityFunction continentalness) {
        DensityFunction base = rangeChoice(
                // keep ocean biomes as-is
                continentalness,
                -0.19D,
                2.0D,
                // move desert biomes to jungle regions
                rangeChoice(
                        original,
                        0.57D,
                        2.0D,
                        add(
                                original,
                                constant(-0.35D)
                        ),
                        // move jungle biomes to desert regions
                        rangeChoice(
                                original,
                                0.2D,
                                0.55D,
                                add(
                                        original,
                                        constant(0.37D)
                                ),
                                original
                        )
                ),
                original
        );
        return PlatformUtil.isModLoaded("terrablender") && ExpandedEcosphere.currentMode == ExpandedEcosphere.Mode.COMPATIBLE
                ? TerraBlenderIntegration.regionChoice(WWEEIntegration.REGION_LOCATION, base, vanillaTemperature(original, continentalness))
                : base;
    }
}
