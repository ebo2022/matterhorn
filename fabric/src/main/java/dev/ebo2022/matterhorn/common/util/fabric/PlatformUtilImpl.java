package dev.ebo2022.matterhorn.common.util.fabric;

import net.fabricmc.loader.api.FabricLoader;

public class PlatformUtilImpl {

    public static boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }
}