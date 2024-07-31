package dev.ebo2022.matterhorn.common.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PlatformUtil {

    @ExpectPlatform
    public static Optional<MinecraftServer> getCurrentServer() {
        throw new AssertionError();
    }
}
