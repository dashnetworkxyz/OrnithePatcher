package club.sk1er.patcher.mixins.features;

import club.sk1er.patcher.hooks.MinecraftHook;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.dashnetwork.patcher.Patcher;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin_BorderlessFullscreen {

    @Inject(method = "toggleFullscreen", at = @At("HEAD"), cancellable = true)
    private void patcher$borderlessFullscreen(CallbackInfo ci) {
        if (!Patcher.get().config().options().borderlessFullscreen.get())
            return;

        if (Utils.getOS() != Utils.OS.WINDOWS) {
            Patcher.get().logger().info("Cannot use borderless fullscreen. This feature only works on Windows.");
            return;
        }

        /*
        if (MinecraftHook.toggleBorderlessFullscreen((Minecraft) (Object) this)) {

        }
         */
        MinecraftHook.test();

        ci.cancel();
    }

}
