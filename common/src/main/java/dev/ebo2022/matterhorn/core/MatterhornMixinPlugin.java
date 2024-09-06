package dev.ebo2022.matterhorn.core;

import com.google.common.base.Suppliers;
import dev.architectury.injectables.annotations.PlatformOnly;
import dev.architectury.injectables.targets.ArchitecturyTarget;
import dev.ebo2022.matterhorn.common.util.PlatformUtil;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class MatterhornMixinPlugin implements IMixinConfigPlugin {

    private static final Set<String> TERRABLENDER_MIXINS = Set.of(
    );
    private static final Supplier<Boolean> TERRABLENDER_PRESENT = Suppliers.memoize(() -> {
        // neo crashes if we access ModList too early, so we have to check for classes directly
        if (ArchitecturyTarget.getCurrentTarget().equals("neoforge")) {
            try {
                Class.forName("terrablender.core.TerraBlender", false, MatterhornMixinPlugin.class.getClassLoader());
                return true;
            } catch (ClassNotFoundException e) {
                return false;
            }
        } else {
            return PlatformUtil.isModLoaded("terrablender");
        }
    });

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (TERRABLENDER_MIXINS.contains(mixinClassName) && !TERRABLENDER_PRESENT.get())
            return false;
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
