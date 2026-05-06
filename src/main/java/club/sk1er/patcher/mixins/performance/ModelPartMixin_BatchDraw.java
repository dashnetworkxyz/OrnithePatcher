package club.sk1er.patcher.mixins.performance;

import net.minecraft.client.render.model.ModelPart;
import net.minecraft.client.render.vertex.DefaultVertexFormat;
import net.minecraft.client.render.vertex.Tesselator;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.dashnetwork.patcher.Patcher;

@Mixin(ModelPart.class)
public class ModelPartMixin_BatchDraw {

    @Shadow
    private boolean compiled;

    @Unique
    private boolean patcher$compiledState;

    @Inject(method = "render", at = @At("HEAD"))
    private void patcher$resetCompiled(CallbackInfo ci) {
        if (patcher$compiledState != Patcher.get().config().options().batchModelRendering.get())
            compiled = false;
    }

    @Inject(
            method = "compile",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/vertex/Tesselator;getBuffer()Lnet/minecraft/client/render/vertex/BufferBuilder;", shift = At.Shift.AFTER)
    )
    private void patcher$beginRendering(CallbackInfo ci) {
        patcher$compiledState = Patcher.get().config().options().batchModelRendering.get();

        if (Patcher.get().config().options().batchModelRendering.get())
            Tesselator.getInstance().getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormat.POSITION_TEX_NORMAL);
    }

    @Inject(
            method = "compile",
            at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEndList()V", remap = false)
    )
    private void patcher$draw(CallbackInfo ci) {
        if (Patcher.get().config().options().batchModelRendering.get())
            Tesselator.getInstance().end();
    }

}
