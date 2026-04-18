package xyz.dashnetwork.patcher.mixins.twitch;

import net.minecraft.client.Minecraft;
import net.minecraft.client.twitch.ErrorTwitchStream;
import net.minecraft.client.twitch.TwitchStream;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

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

}
