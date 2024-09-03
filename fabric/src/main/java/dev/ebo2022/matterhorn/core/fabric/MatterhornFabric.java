package dev.ebo2022.matterhorn.core.fabric;

import dev.ebo2022.matterhorn.core.Matterhorn;
import net.fabricmc.api.ModInitializer;

public class MatterhornFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Matterhorn.init();
    }
}
