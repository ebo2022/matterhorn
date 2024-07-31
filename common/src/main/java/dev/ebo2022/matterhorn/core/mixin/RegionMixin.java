package dev.ebo2022.matterhorn.core.mixin;

import dev.ebo2022.matterhorn.common.world.level.levelgen.biome.region.MatterhornRegion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import terrablender.api.Region;
import terrablender.api.RegionType;

@Mixin(value = Region.class, remap = false)
public class RegionMixin {

    @Shadow
    private RegionType type;

    /*
     Zeroes out the weight of all non-Matterhorn regions to stop them from generating if the config option is enabled.
    */
    @Inject(method = "getWeight", at = @At("HEAD"), cancellable = true)
    private void getWeight(CallbackInfoReturnable<Integer> cir) {
        if (this.type == RegionType.OVERWORLD) {
            if ((Region) (Object) this != MatterhornRegion.INSTANCE)
                cir.setReturnValue(0);
        }
    }
}
