package dev.ebo2022.matterhorn.common.platform.fabric;

import dev.ebo2022.matterhorn.core.fabric.MatterhornFabric;
import net.minecraft.server.MinecraftServer;

import java.util.Optional;

public class PlatformUtilImpl {

    public static Optional<MinecraftServer> getCurrentServer() {
        return Optional.ofNullable(MatterhornFabric.currentServer);
    }
}
