package dev.ebo2022.matterhorn.core.mixin;

import dev.ebo2022.matterhorn.common.world.level.levelgen.terrain.MatterhornTerrainExtension;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.chunk.BlockColumn;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SurfaceSystem.class)
public class SurfaceSystemMixin {

    @Inject(method = "buildSurface", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Holder;is(Lnet/minecraft/resources/ResourceKey;)Z", shift = At.Shift.BEFORE, ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
    private void terrainExtension(RandomState randomState, BiomeManager biomeManager, Registry<Biome> registry, boolean bl, WorldGenerationContext worldGenerationContext, ChunkAccess chunkAccess, NoiseChunk noiseChunk, SurfaceRules.RuleSource ruleSource, CallbackInfo ci, BlockPos.MutableBlockPos mutableBlockPos, ChunkPos chunkPos, int i, int j, BlockColumn blockColumn, SurfaceRules.Context context, SurfaceRules.SurfaceRule surfaceRule, BlockPos.MutableBlockPos mutableBlockPos2, int k, int l, int m, int n, int o, Holder<Biome> holder) {
        MatterhornTerrainExtension.runExtension(holder, chunkAccess, biomeManager, blockColumn, m, n, o, bl);
    }
}
