package xyz.dashnetwork.patcher.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.living.player.LocalClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin_DebugCreativeSpectatorSwap {

    @Shadow
    public Screen screen;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;handleGuiKeyBindings()V"))
    private void patcher$f3n(CallbackInfo callback) {
        if (Keyboard.getEventKeyState() && screen == null) {
            int pressed = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();

            if (pressed == Keyboard.KEY_N && Keyboard.isKeyDown(Keyboard.KEY_F3)) {
                LocalClientPlayerEntity player = Minecraft.getInstance().player;

                if (player == null)
                    return;

                if (player.isSpectator())
                    player.sendChat("/gamemode creative");
                else
                    player.sendChat("/gamemode spectator");
            }
        }
    }

}
