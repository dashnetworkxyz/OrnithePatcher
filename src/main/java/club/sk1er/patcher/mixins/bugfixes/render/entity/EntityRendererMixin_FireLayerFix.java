package club.sk1er.patcher.mixins.bugfixes.render.entity;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.platform.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin_FireLayerFix {

    @Inject(method = "renderOnFire", at = @At("HEAD"))
    private void patcher$fixFireLayer(CallbackInfo ci) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.enableAlphaTest();
    }

}
