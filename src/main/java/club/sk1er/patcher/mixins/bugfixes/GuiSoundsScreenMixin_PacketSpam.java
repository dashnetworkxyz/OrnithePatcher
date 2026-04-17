package club.sk1er.patcher.mixins.bugfixes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.client.gui.screen.SoundsScreen$SoundSliderWidget")
public class GuiSoundsScreenMixin_PacketSpam {

    // don't send a packet for every frame the slider is dragged, instead save that for when the slider is released
    @Redirect(
            method = "mouseClicked",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/options/GameOptions;save()V")
    )
    private void patcher$cancelSaving(GameOptions instance) {
        // no-op
    }

    @Inject(
            method = "mouseReleased",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/sound/system/SoundManager;play(Lnet/minecraft/client/sound/instance/SoundInstance;)V")
    )
    private void patcher$save(int mouseX, int mouseY, CallbackInfo ci) {
        Minecraft.getInstance().options.save();
    }

}
