package dev.ebo2022.matterhorn.core.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.ebo2022.matterhorn.common.swappers.DensitySwapper;
import dev.ebo2022.matterhorn.core.Matterhorn;
import dev.ebo2022.matterhorn.core.integration.LarionIntegration;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.RandomState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RandomState.class)
public class RandomStateMixin {

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/NoiseRouter;mapAll(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/NoiseRouter;"))
    private NoiseRouter alterNoiseRouter(NoiseRouter instance, DensityFunction.Visitor visitor, Operation<NoiseRouter> original) {
        if (LarionIntegration.isLarionLoaded(instance)) {
            Matterhorn.LOGGER.info("Larion detected; self-disabling to avoid breakage");
            return original.call(instance, visitor);
        }
        return new NoiseRouter(
                instance.barrierNoise().mapAll(visitor),
                instance.fluidLevelFloodednessNoise().mapAll(visitor),
                instance.fluidLevelSpreadNoise().mapAll(visitor),
                instance.lavaNoise().mapAll(visitor),
                DensitySwapper.temperature(instance.temperature(), instance.continents()).mapAll(visitor),
                instance.vegetation().mapAll(visitor),
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
