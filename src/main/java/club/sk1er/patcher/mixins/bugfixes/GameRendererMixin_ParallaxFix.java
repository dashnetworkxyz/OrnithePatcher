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

@Mixin(value = GameRenderer.class, priority = 1001)
public class GameRendererMixin_ParallaxFix {

    @Shadow
    private Minecraft minecraft;

    @ModifyConstant(method = "transformCamera", constant = @Constant(floatValue = -0.1F))
    private float patcher$modifyParallax(float original) {
        return 0.05F;
    }

    @Redirect(
            method = "renderAxisIndicators",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/platform/GlStateManager;translatef(FFF)V")
    )
    private void patcher$moveAxisIndicators(float x, float y, float z) {
        LocalClientPlayerEntity player = minecraft.player;
        Vec3d vec = player.getRotationVector(player.pitch, player.yaw);

        GlStateManager.translated(vec.x * 0.15F, (vec.y * 0.15F) + y, vec.z * 0.15F);
    }

}
