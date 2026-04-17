package club.sk1er.patcher.mixins.bugfixes.network;

import net.minecraft.client.entity.living.player.LocalClientPlayerEntity;
import net.minecraft.client.network.handler.ClientPlayNetworkHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin_SignChatSpam {

    @Redirect(
            method = "handleSignBlockEntityUpdate",
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=Unable to locate sign at ", ordinal = 0)),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/living/player/LocalClientPlayerEntity;sendMessage(Lnet/minecraft/text/Text;)V")
    )
    private void patcher$removeDebugMessage(LocalClientPlayerEntity entity, Text message) {
        // No-op
    }

}
