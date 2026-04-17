package club.sk1er.patcher.mixins.bugfixes.crashes;

import net.minecraft.client.resource.pack.ResourcePacks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(ResourcePacks.class)
public class ResourcePacksMixin_ResolveCrash {

    @Shadow @Final
    private File serverPackDir;

    @Inject(method = "clearOldDownloads", at = @At("HEAD"), cancellable = true)
    private void patcher$skipWithoutDirectory(CallbackInfo ci) {
        if (!serverPackDir.exists())
            ci.cancel();
    }

}
