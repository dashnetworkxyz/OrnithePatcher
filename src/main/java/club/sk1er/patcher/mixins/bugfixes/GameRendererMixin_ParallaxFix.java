package club.sk1er.patcher.mixins.bugfixes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.living.player.LocalClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.dashnetwork.patcher.Patcher;

@Mixin(value = GameRenderer.class, priority = 1001)
public abstract class GameRendererMixin_ParallaxFix {

    @Shadow
    private Minecraft minecraft;

    @ModifyConstant(method = "transformCamera", constant = @Constant(floatValue = -0.1F))
    private float patcher$modifyParallax(float original) {
        return Patcher.get().config().options().parallaxFix ? 0.05F : original;
    }

    @Redirect(
            method = "renderAxisIndicators",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/platform/GlStateManager;translatef(FFF)V")
    )
    private void patcher$moveAxisIndicators(float x, float y, float z) {
        if (!Patcher.get().config().options().parallaxFix) {
            GlStateManager.translatef(x, y, z);
            return;
        }

        // TODO: hide in F5
        LocalClientPlayerEntity player = minecraft.player;
        Vec3d vec = player.getRotationVector(player.pitch, player.yaw);

        GlStateManager.translated(vec.x * 0.15, (vec.y * 0.15) + y, vec.z * 0.15);
    }

}
