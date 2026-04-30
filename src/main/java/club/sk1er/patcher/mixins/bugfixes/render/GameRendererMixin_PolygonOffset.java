package club.sk1er.patcher.mixins.bugfixes.render;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.platform.GlStateManager;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin_PolygonOffset {

    @Inject(
            method = "render(IFJ)V",
            slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/client/render/block/BlockLayer;TRANSLUCENT:Lnet/minecraft/client/render/block/BlockLayer;", opcode = Opcodes.GETSTATIC)),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/world/WorldRenderer;render(Lnet/minecraft/client/render/block/BlockLayer;DILnet/minecraft/entity/Entity;)I", ordinal = 0)
    )
    private void patcher$enablePolygonOffset(CallbackInfo ci) {
        GlStateManager.enablePolygonOffset();
        GlStateManager.polygonOffset(-0.325F, -0.325F);
    }

    @Inject(
            method = "render(IFJ)V",
            slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/client/render/block/BlockLayer;TRANSLUCENT:Lnet/minecraft/client/render/block/BlockLayer;", opcode = Opcodes.GETSTATIC)),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/world/WorldRenderer;render(Lnet/minecraft/client/render/block/BlockLayer;DILnet/minecraft/entity/Entity;)I", ordinal = 0, shift = At.Shift.AFTER)
    )
    private void patcher$disablePolygonOffset(CallbackInfo ci) {
        GlStateManager.disablePolygonOffset();
    }

}
