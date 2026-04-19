package club.sk1er.patcher.mixins.bugfixes.render.entity;

import net.minecraft.client.render.entity.ArrowRenderer;
import net.minecraft.client.render.platform.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArrowRenderer.class)
public class ArrowRendererMixin_AlphaFix {

    @Inject(
            method = "render(Lnet/minecraft/entity/projectile/ArrowEntity;DDDFF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/platform/GlStateManager;translatef(FFF)V")
    )
    private void patcher$alphaFix(CallbackInfo ci) {
        GlStateManager.enableAlphaTest();
    }

}
