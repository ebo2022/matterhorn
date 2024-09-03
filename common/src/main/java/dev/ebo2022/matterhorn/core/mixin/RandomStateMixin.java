package dev.ebo2022.matterhorn.core.mixin;

import dev.ebo2022.matterhorn.common.densityfunctions.HumidityUtil;
import dev.ebo2022.matterhorn.common.densityfunctions.AlteredTemperature;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.RandomState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RandomState.class)
public class RandomStateMixin {

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/NoiseRouter;mapAll(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/NoiseRouter;"))
    private NoiseRouter alterNoiseRouter(NoiseRouter instance, DensityFunction.Visitor visitor) {
        return new NoiseRouter(
                instance.barrierNoise().mapAll(visitor),
                instance.fluidLevelFloodednessNoise(),
                instance.fluidLevelSpreadNoise().mapAll(visitor),
                instance.lavaNoise().mapAll(visitor),
                AlteredTemperature.create(instance.temperature(), instance.continents()).mapAll(visitor),
                HumidityUtil.create(instance.vegetation(), instance.temperature()).mapAll(visitor),
                instance.continents().mapAll(visitor),
                instance.erosion().mapAll(visitor),
                instance.depth().mapAll(visitor),
                instance.ridges().mapAll(visitor),
                instance.initialDensityWithoutJaggedness().mapAll(visitor),
                instance.finalDensity().mapAll(visitor),
                instance.veinToggle().mapAll(visitor),
                instance.veinRidged().mapAll(visitor),
                instance.veinGap().mapAll(visitor)
        );
    }
}
