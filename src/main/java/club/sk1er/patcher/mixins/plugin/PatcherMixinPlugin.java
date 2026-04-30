package club.sk1er.patcher.mixins.plugin;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class PatcherMixinPlugin implements IMixinConfigPlugin {

    private static boolean optifine = false;

    @Override
    public void onLoad(String mixinPackage) {
        try {
            Class.forName("me.modmuss50.optifabric.mod.Optifabric");
            optifine = true;
        } catch (ClassNotFoundException ignored) {
            ignored.printStackTrace();
        }
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        String mixinPackage = mixinClassName.contains(".")
                ? mixinClassName.substring(0, mixinClassName.lastIndexOf('.'))
                : mixinClassName;

        if (!optifine && mixinPackage.endsWith("optifine"))
            return false;

        return !optifine || !mixinPackage.endsWith("vanilla");
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

}
