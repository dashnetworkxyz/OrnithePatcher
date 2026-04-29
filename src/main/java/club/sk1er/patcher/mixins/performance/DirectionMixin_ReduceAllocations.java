/**
 * THE FOLLOWING CODE IS LICENSED UNDER THE GNU Lesser General Public License v3.0
 * This work, "OrnithePatcher", uses code from CaffeineMC's "lithium-fabric", licensed under the LGPL-3.0 license. The original license is included in the repository.
 * https://github.com/CaffeineMC/lithium-fabric/tree/develop
 * https://github.com/CaffeineMC/lithium-fabric/blob/develop/LICENSE.txt
 */
package club.sk1er.patcher.mixins.performance;

import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Direction.class)
public class DirectionMixin_ReduceAllocations {

    @Shadow @Final private static Direction[] ALL;
    @Shadow @Final private int opposite;

    @Unique private int offsetX;
    @Unique private int offsetY;
    @Unique private int offsetZ;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void patcher$cacheOffsets(String string, int i, int id, int opposite, int idHorizontal, String key, Direction.AxisDirection axisDir, Direction.Axis axis, Vec3i normal, CallbackInfo ci) {
        offsetX = axis == Direction.Axis.X ? axisDir.getOffset() : 0;
        offsetY = axis == Direction.Axis.Y ? axisDir.getOffset() : 0;
        offsetZ = axis == Direction.Axis.Z ? axisDir.getOffset() : 0;
    }

    /**
     * @author jellysquid
     * @reason The cached offset X
     */
    @Overwrite
    public int getOffsetX() {
        return offsetX;
    }

    /**
     * @author jellysquid
     * @reason The cached offset Y
     */
    @Overwrite
    public int getOffsetY() {
        return offsetY;
    }

    /**
     * @author jellysquid
     * @reason The cached offset Z
     */
    @Overwrite
    public int getOffsetZ() {
        return offsetZ;
    }

    /**
     * @reason Avoid the modulo/abs operations
     * @author JellySquid
     */
    @Overwrite
    public Direction getOpposite() {
        return ALL[opposite];
    }

    /**
     * @reason Do not allocate an excessive number of Direction arrays
     * @author JellySquid
     */
    @Overwrite
    public static Direction pick(Random rand) {
        return ALL[rand.nextInt(ALL.length)];
    }

}
