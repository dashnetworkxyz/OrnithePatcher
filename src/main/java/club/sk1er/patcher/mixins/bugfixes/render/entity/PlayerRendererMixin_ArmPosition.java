package club.sk1er.patcher.mixins.bugfixes.render.entity;

import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.model.entity.PlayerModel;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin_ArmPosition {

    @Redirect(
            method = "renderRightHand",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/model/entity/PlayerModel;sneaking:Z", ordinal = 0, opcode = Opcodes.PUTFIELD)
    )
    private void patcher$resetArmState(PlayerModel model, boolean value) {
        model.riding = model.sneaking = false;
    }

}
