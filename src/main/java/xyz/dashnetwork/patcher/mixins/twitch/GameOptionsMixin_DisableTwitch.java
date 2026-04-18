package xyz.dashnetwork.patcher.mixins.twitch;

import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptions.class)
public class GameOptionsMixin_DisableTwitch {

    @Shadow public KeyBinding[] keyBindings;
    @Shadow public KeyBinding streamStartStopKey;
    @Shadow public KeyBinding streamPauseKey;
    @Shadow public KeyBinding streamCommercialKey;
    @Shadow public KeyBinding streamToggleMicKey;

    @Inject(method = "<init>*", at = @At("TAIL"))
    private void patcher$removeTwitchBindings(CallbackInfo ci) {
        keyBindings = ArrayUtils.removeElements(keyBindings,
                streamStartStopKey,
                streamPauseKey,
                streamCommercialKey,
                streamToggleMicKey
        );

        streamStartStopKey = null;
        streamPauseKey = null;
        streamCommercialKey = null;
        streamToggleMicKey = null;
    }

}
