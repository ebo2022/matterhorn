package dev.ebo2022.matterhorn.common.util.neoforge;

import net.neoforged.fml.ModList;

public class PlatformUtilImpl {

    public static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }
}