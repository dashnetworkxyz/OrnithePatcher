package xyz.dashnetwork.patcher.mixins;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.dashnetwork.patcher.config.PatcherConfig;

import java.io.File;

@Mixin(Minecraft.class)
public class MinecraftMixin_PatcherConfigSetup {

    @Shadow @Final
    public File gameDir;

    @Inject(method = "init", at = @At("HEAD"))
    private void patcher$setupConfig(CallbackInfo ci) {
        PatcherConfig.init(gameDir);
        PatcherConfig.load();
    }

}
