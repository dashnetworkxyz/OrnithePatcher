package club.sk1er.patcher.mixins.features;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.dashnetwork.patcher.Patcher;

@Mixin(GameRenderer.class)
public class GameRendererMixin_CameraPerspective {

    @Redirect(method = "transformCamera", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;rayTrace(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/world/HitResult;"))
    private HitResult patcher$changeBlockingType(ClientWorld world, Vec3d from, Vec3d to) {
        return Patcher.get().config().options().betterCamera.get()
                ? world.rayTrace(from, to, false, true, true)
                : world.rayTrace(from, to);
    }

}
