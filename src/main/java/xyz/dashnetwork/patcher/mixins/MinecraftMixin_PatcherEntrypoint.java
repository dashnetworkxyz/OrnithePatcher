package xyz.dashnetwork.patcher.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.main.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.dashnetwork.patcher.Patcher;

@Mixin(Minecraft.class)
public class MinecraftMixin_PatcherEntrypoint {

    @Inject(method = "<init>", at = @At("TAIL"))
    private static void patcher$entrypoint(RunArgs session, CallbackInfo ci) {
        new Patcher(session.location.gameDir);
    }

}
