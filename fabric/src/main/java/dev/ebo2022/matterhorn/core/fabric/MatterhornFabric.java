package dev.ebo2022.matterhorn.core.fabric;

import dev.ebo2022.matterhorn.core.Matterhorn;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import terrablender.api.TerraBlenderApi;

public class MatterhornFabric implements ModInitializer, TerraBlenderApi {

    public static MinecraftServer currentServer;

    @Override
    public void onInitialize() {
        Matterhorn.init();
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> currentServer = null);
    }

    @Override
    public void onTerraBlenderInitialized() {
        Matterhorn.initTerrablender();
    }
}
