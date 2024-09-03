package dev.ebo2022.matterhorn.common.densityfunctions;

import net.minecraft.world.level.levelgen.DensityFunction;

import static net.minecraft.world.level.levelgen.DensityFunctions.*;

public class AlteredTemperature {

    public static DensityFunction create(DensityFunction original, DensityFunction continentalness) {
        // keep ocean biomes as-is
        return rangeChoice(
                continentalness,
                -0.19D,
                2.0D,
                rangeChoice(
                        original,
                        0.57D,
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
                                        constant(0.37D)
                                ),
                                original
                        )
                ),
                original
        );
    }
}
