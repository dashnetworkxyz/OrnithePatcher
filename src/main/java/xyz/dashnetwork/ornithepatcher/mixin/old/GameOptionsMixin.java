package xyz.dashnetwork.ornithepatcher.mixin.old;

import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptions.class)
public final class GameOptionsMixin {

    @Shadow public boolean realmsNotifications;
    @Shadow public boolean snooperEnabled;
    @Shadow public boolean showInventoryAchievementHint;
    @Shadow public KeyBinding streamStartStopKey;
    @Shadow public KeyBinding streamPauseKey;

    @Inject(method = "<init>*", at = @At("TAIL"))
    public void onConstruct(CallbackInfo callback) {
        realmsNotifications = false;
        snooperEnabled = false;
        showInventoryAchievementHint = false;
        streamStartStopKey.setKeyCode(0);
        streamPauseKey.setKeyCode(0);
    }

}
