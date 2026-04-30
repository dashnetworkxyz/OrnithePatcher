package club.sk1er.patcher.mixins.performance;

import club.sk1er.patcher.mixins.accessors.BufferBuilderAccessor;
import net.minecraft.client.render.model.Polygon;
import net.minecraft.client.render.vertex.BufferBuilder;
import net.minecraft.client.render.vertex.DefaultVertexFormat;
import net.minecraft.client.render.vertex.Tesselator;
import net.minecraft.client.render.vertex.VertexFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.dashnetwork.patcher.Patcher;

@Mixin(Polygon.class)
public class PolygonMixin_BatchDraw {

    @Unique
    private boolean patcher$drawOnSelf;

    @Redirect(
            method = "compile",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/vertex/BufferBuilder;begin(ILnet/minecraft/client/render/vertex/VertexFormat;)V")
    )
    private void patcher$beginDraw(BufferBuilder builder, int drawMode, VertexFormat format) {
        patcher$drawOnSelf = !((BufferBuilderAccessor) builder).isBuilding();

        if (patcher$drawOnSelf || !Patcher.get().config().options().batchModelRendering.get())
            builder.begin(drawMode, DefaultVertexFormat.POSITION_TEX_NORMAL);
    }

    @Redirect(
            method = "compile",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/vertex/Tesselator;end()V")
    )
    private void patcher$endDraw(Tesselator tesselator) {
        if (patcher$drawOnSelf || !Patcher.get().config().options().batchModelRendering.get())
            tesselator.end();
    }

}
