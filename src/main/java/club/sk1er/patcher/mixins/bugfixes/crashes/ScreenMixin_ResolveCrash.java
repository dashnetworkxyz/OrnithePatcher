package club.sk1er.patcher.mixins.bugfixes.crashes;

import club.sk1er.patcher.screen.ResolutionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin_ResolveCrash {

    @Shadow
    protected Minecraft minecraft;

    @SuppressWarnings("RedundantCast")
    @Inject(
            method = "handleInputs",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;handleKeyboard()V"),
            cancellable = true
    )
    private void patcher$checkScreen(CallbackInfo ci) {
        if ((Screen) (Object) this != this.minecraft.screen) {
            ResolutionHelper.setScaleOverride(-1);
            ci.cancel();
        }
    }

}
