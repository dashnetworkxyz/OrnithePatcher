package club.sk1er.patcher.mixins.performance.network;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.handler.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin_InstantDimensionSwapping {

    @ModifyArg(method = {"handleLogin", "handlePlayerRespawn"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;openScreen(Lnet/minecraft/client/gui/screen/Screen;)V"))
    private Screen patcher$skipTerrainScreen(Screen original) {
        return null;
    }

}
