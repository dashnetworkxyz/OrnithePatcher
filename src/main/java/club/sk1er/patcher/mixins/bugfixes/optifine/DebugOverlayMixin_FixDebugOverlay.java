package club.sk1er.patcher.mixins.bugfixes.optifine;

import club.sk1er.patcher.hooks.OptiFineHook;
import net.minecraft.client.gui.overlay.DebugOverlay;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.lang.reflect.Method;
import java.util.List;

@Mixin(DebugOverlay.class)
public class DebugOverlayMixin_FixDebugOverlay {

    @Dynamic("OptiFine")
    @Redirect(
            method = "getSystemInfo",
            at = @At(value = "INVOKE", target = "Ljava/util/List;add(ILjava/lang/Object;)V", ordinal = 0, remap = false)
    )
    private void patcher$removeNativeIfUnsupported(List<Object> list, int index, Object element) {
        if (OptiFineHook.getBufferAllocated() != -1)
            list.add(index, element);
    }

    @Dynamic("OptiFine")
    @Redirect(
            method = "getSystemInfo",
            at = @At(value = "INVOKE", target = "Ljava/util/List;set(ILjava/lang/Object;)Ljava/lang/Object;", remap = false)
    )
    private Object patcher$keepCpuInfo(List<Object> list, int index, Object element) {
        if (OptiFineHook.getBufferAllocated() == -1)
            index--;

        list.add(index, element);
        list.add(index + 1, "");
        return null;
    }

}
