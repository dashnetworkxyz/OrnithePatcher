package xyz.dashnetwork.patcher.mixins;

import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptions.class)
public class GameOptionsMixin_AdjustOptions {

    @Shadow public boolean showInventoryAchievementHint;
    @Shadow public boolean snooperEnabled;
    @Shadow public boolean realmsNotifications;
    @Shadow public KeyBinding streamStartStopKey;
    @Shadow public KeyBinding streamPauseKey;
    @Shadow public KeyBinding streamCommercialKey;
    @Shadow public KeyBinding streamToggleMicKey;

    @Inject(method = "<init>*", at = @At("TAIL"))
    private void patcher$adjustOptions(CallbackInfo ci) {
        showInventoryAchievementHint = false;
        snooperEnabled = false;
        realmsNotifications = false;

        streamStartStopKey.setKeyCode(0);
        streamPauseKey.setKeyCode(0);
        streamCommercialKey.setKeyCode(0);
        streamToggleMicKey.setKeyCode(0);
    }

}
