package dev.ebo2022.matterhorn.common.world.level.levelgen.terrain;

import dev.ebo2022.matterhorn.core.registry.MatterhornBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.BlockColumn;
import net.minecraft.world.level.chunk.ChunkAccess;

import java.util.function.Function;

public class MatterhornTerrainExtension {


    public static void runExtension(Holder<Biome> currentBiome, ChunkAccess chunk, BiomeManager biomeManager, BlockColumn column, int x, int z, int surfaceHeight, boolean useLegacyRandom) {
        if (currentBiome.is(MatterhornBiomes.LAKE)) {
            for (int y = Mth.floor(surfaceHeight - (10 * blendBiomeEdge(currentBiome, biomeManager, new BlockPos(x, surfaceHeight, z), 12))); y < surfaceHeight; y++) {
                column.setBlock(y, y <= 62 ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState());
            }
        }
    }

    public static double blendBiomeEdge(Holder<Biome> currentBiome, BiomeManager biomeManager, BlockPos origin, int blendRadius) {
        // TODO: implement blending
        return 1;
    }
}
