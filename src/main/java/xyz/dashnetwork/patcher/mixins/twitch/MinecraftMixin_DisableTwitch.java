package xyz.dashnetwork.patcher.mixins.twitch;

import net.minecraft.client.Minecraft;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.twitch.ErrorTwitchStream;
import net.minecraft.client.twitch.TwitchStream;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
public class MinecraftMixin_DisableTwitch {

    @Shadow
    private TwitchStream twitchStream;

    /**
     * @author MasterDash5
     * @reason Twitch streaming no longer functional
     */
    @Overwrite
    private void initTwitchStream() {
        twitchStream = new ErrorTwitchStream(new UnsupportedOperationException("Twitch streaming is disabled."));
    }

    @Redirect(
            method = "handleGuiKeyBindings",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/options/KeyBinding;getKeyCode()I")
    )
    private int patcher$disableTwitchKeybinds(KeyBinding keyBinding) {
        if (keyBinding == null)
            return 0;

        return keyBinding.getKeyCode();
    }

}
