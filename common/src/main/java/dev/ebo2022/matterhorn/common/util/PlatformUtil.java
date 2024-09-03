package dev.ebo2022.matterhorn.common.util;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class PlatformUtil {

    @ExpectPlatform
    public static boolean isModLoaded(String modId) {
        throw new AssertionError();
    }
}