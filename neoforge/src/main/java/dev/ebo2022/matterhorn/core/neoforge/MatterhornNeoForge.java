package dev.ebo2022.matterhorn.core.neoforge;

import dev.ebo2022.matterhorn.core.Matterhorn;
import net.minecraft.core.HolderLookup;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@Mod(Matterhorn.MOD_ID)
public class MatterhornNeoForge {

    public MatterhornNeoForge() {
        IEventBus bus = ModList.get().getModContainerById(Matterhorn.MOD_ID).map(ModContainer::getEventBus).orElseThrow();
        bus.addListener(this::onGatherData);
        bus.addListener(this::onCommonSetup);
    }

    private void onGatherData(GatherDataEvent event) {
        ExistingFileHelper existing = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(Matterhorn::initTerrablender);
    }
}
