package club.sk1er.patcher.mixins.bugfixes.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GameGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameGui.class)
public class GameGuiMixin_RemoveSpectatorPumpkinOverlay {

    @Inject(method = "renderPumpkinOverlay", at = @At("HEAD"), cancellable = true)
    private void patcher$removeSpectatorPumpkinOverlay(CallbackInfo ci) {
        if (Minecraft.getInstance().player.isSpectator())
            ci.cancel();
    }

}
