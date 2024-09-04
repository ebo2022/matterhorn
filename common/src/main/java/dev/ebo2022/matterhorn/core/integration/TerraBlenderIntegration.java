package dev.ebo2022.matterhorn.core.integration;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.ebo2022.matterhorn.core.Matterhorn;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;
import terrablender.api.Region;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class TerraBlenderIntegration {

    public static final String MOD_ID = "terrablender";

    public static void register(BiConsumer<ResourceLocation, KeyDispatchDataCodec<? extends DensityFunction>> registry) {
        registry.accept(Matterhorn.id("region_choice"), RegionChoice.CODEC);
    }

    public static DensityFunction regionChoice(ResourceLocation region, DensityFunction whenInRegion, DensityFunction whenOutOfRegion) {
        return new RegionChoice(region, whenInRegion, whenOutOfRegion);
    }

    private record RegionChoice(ResourceLocation region, DensityFunction whenInRegion, DensityFunction whenOutOfRegion) implements DensityFunction {

        private static final KeyDispatchDataCodec<RegionChoice> CODEC = KeyDispatchDataCodec.of(RecordCodecBuilder.mapCodec(instance -> instance.group(
                ResourceLocation.CODEC.fieldOf("region").forGetter(RegionChoice::region),
                DensityFunction.HOLDER_HELPER_CODEC.fieldOf("when_in_region").forGetter(RegionChoice::whenInRegion),
                DensityFunction.HOLDER_HELPER_CODEC.fieldOf("when_out_of_region").forGetter(RegionChoice::whenOutOfRegion)
        ).apply(instance, RegionChoice::new)));

        @Override
        public double compute(FunctionContext context) {
            if (context instanceof SinglePointContextWithRegion fullContext)
                return fullContext.region.get().getName() == this.region ? this.whenInRegion.compute(fullContext) : this.whenOutOfRegion.compute(fullContext);
            return this.whenOutOfRegion.compute(context);
        }

        @Override
        public void fillArray(double[] ds, ContextProvider contextProvider) {
            contextProvider.fillAllDirectly(ds, this);
        }

        @Override
        public DensityFunction mapAll(Visitor visitor) {
            return new RegionChoice(this.region, this.whenInRegion.mapAll(visitor), this.whenOutOfRegion.mapAll(visitor));
        }

        @Override
        public double minValue() {
            return Math.min(this.whenInRegion.minValue(), this.whenOutOfRegion.minValue());
        }

        @Override
        public double maxValue() {
            return Math.max(this.whenInRegion.maxValue(), this.whenOutOfRegion.maxValue());
        }

        @Override
        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            return CODEC;
        }
    }

    public record SinglePointContextWithRegion(int blockX, int blockY, int blockZ, Supplier<Region> region) implements DensityFunction.FunctionContext {}
}
