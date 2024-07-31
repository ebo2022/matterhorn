package dev.ebo2022.matterhorn.common.platform.neoforge;

import net.minecraft.server.MinecraftServer;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PlatformUtilImpl {

    public static Optional<MinecraftServer> getCurrentServer() {
        return Optional.ofNullable(ServerLifecycleHooks.getCurrentServer());
    }
}
