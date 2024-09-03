package dev.ebo2022.matterhorn.common.densityfunctions;

import net.minecraft.world.level.levelgen.DensityFunction;

import static net.minecraft.world.level.levelgen.DensityFunctions.*;

public class HumidityUtil {

    public static DensityFunction create(DensityFunction original, DensityFunction temperature) {
        return rangeChoice(
                temperature,
                0.185D,
                0.2D,
                max(
                        // this is capped at -0.34 so flower forests & sunflower plains (which generate at humidities <= -0.35) don't spawn next to deserts
                        constant(-0.34D),
                        add(
                                original,
                                constant(-0.5D)
                        )
                ),
                original
        );
    }
}
