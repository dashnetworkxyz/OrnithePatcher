package club.sk1er.patcher.mixins.bugfixes.gui;

import net.minecraft.client.gui.screen.DeathScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathScreen.class)
public class DeathScreenMixin_ResolveButtonClick {

    @Shadow
    private int ticksSinceDeath;

    @Inject(method = "init", at = @At("HEAD"))
    private void patcher$allowClickable(CallbackInfo ci) {
        this.ticksSinceDeath = 0;
    }

}
