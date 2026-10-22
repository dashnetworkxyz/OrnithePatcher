package club.sk1er.patcher.mixins.performance;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.world.CompiledChunk;
import net.minecraft.client.render.world.RenderChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RenderChunk.class)
public class RenderChunkMixin_OptimizeBlockEntities { // TODO: fix

    @Unique
    private BlockEntityRenderer<BlockEntity> patcher$blockEntityRenderer;

    @ModifyVariable(method = "compile", at = @At("STORE"), ordinal = 0)
    private BlockEntityRenderer<BlockEntity> patcher$getRenderedBlockEntity(BlockEntityRenderer<BlockEntity> renderer) {
        patcher$blockEntityRenderer = renderer;
        return renderer;
    }

    /**
     * Minecraft typically renders special tile entities twice per frame. This is noticeable by comparing the opacity
     * of a beacon beam when the beacon is in view versus when it's out of view (fly 50 blocks above, for instance).
     * <p>
     * This fix is based off a Forge PR that was added to 1.11.x:
     * <a href="https://github.com/MinecraftForge/MinecraftForge/pull/3651/files">forge PR</a>
     */
    @Redirect(method = "compile", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/world/CompiledChunk;addRenderableBlockEntity(Lnet/minecraft/block/entity/BlockEntity;)V"))
    private void patcher$renderBlockEntitiesOnce(CompiledChunk chunk, BlockEntity blockEntity) {
        if (patcher$blockEntityRenderer != null && patcher$blockEntityRenderer.shouldRenderOffScreen())
            chunk.addRenderableBlockEntity(blockEntity);
    }

}
