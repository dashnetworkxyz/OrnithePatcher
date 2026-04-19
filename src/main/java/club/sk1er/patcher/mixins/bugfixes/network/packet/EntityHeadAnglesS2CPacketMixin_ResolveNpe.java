package club.sk1er.patcher.mixins.bugfixes.network.packet;

import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityHeadAnglesS2CPacket;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityHeadAnglesS2CPacket.class)
public class EntityHeadAnglesS2CPacketMixin_ResolveNpe {

    @Inject(method = "getEntity", at = @At("HEAD"), cancellable = true)
    private void patcher$addNullCheck(World world, CallbackInfoReturnable<Entity> cir) {
        if (world == null)
            cir.setReturnValue(null);
    }

}
