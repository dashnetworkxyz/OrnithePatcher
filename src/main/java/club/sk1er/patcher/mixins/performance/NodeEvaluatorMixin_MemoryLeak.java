package club.sk1er.patcher.mixins.performance;

import net.minecraft.entity.ai.pathing.NodeEvaluator;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NodeEvaluator.class)
public class NodeEvaluatorMixin_MemoryLeak {

    @Shadow
    protected WorldView world;

    @Inject(method = "done", at = @At("HEAD"))
    private void patcher$cleanupBlockAccess(CallbackInfo ci) {
        world = null;
    }

}
