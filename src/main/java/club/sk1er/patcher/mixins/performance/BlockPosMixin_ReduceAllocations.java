package club.sk1er.patcher.mixins.performance;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BlockPos.class)
public class BlockPosMixin_ReduceAllocations extends Vec3i {

    public BlockPosMixin_ReduceAllocations(int x, int y, int z) {
        super(x, y, z);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos up() {
        return new BlockPos(getX(), getY() + 1, getZ());
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos up(int offset) {
        return offset == 0 ? (BlockPos) (Object) this : new BlockPos(getX(), getY() + offset, getZ());
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos down() {
        return new BlockPos(getX(), getY() - 1, getZ());
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos down(int offset) {
        return offset == 0 ? (BlockPos) (Object) this : new BlockPos(getX(), getY() - offset, getZ());
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos north() {
        return new BlockPos(getX(), getY(), getZ() - 1);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos north(int offset) {
        return offset == 0 ? (BlockPos) (Object) this : new BlockPos(getX(), getY(), getZ() - offset);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos south() {
        return new BlockPos(getX(), getY(), getZ() + 1);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos south(int offset) {
        return offset == 0 ? (BlockPos) (Object) this : new BlockPos(getX(), getY(), getZ() + offset);
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos west() {
        return new BlockPos(getX() - 1, getY(), getZ());
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos west(int offset) {
        return offset == 0 ? (BlockPos) (Object) this : new BlockPos(getX() - offset, getY(), getZ());
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos east() {
        return new BlockPos(getX() + 1, getY(), getZ());
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos east(int offset) {
        return offset == 0 ? (BlockPos) (Object) this : new BlockPos(getX() + offset, getY(), getZ());
    }

    /**
     * @author asbyth
     * @reason Inline method to reduce allocations
     */
    @Overwrite
    public BlockPos offset(Direction direction) {
        return new BlockPos(getX() + direction.getOffsetX(), getY() + direction.getOffsetY(), getZ() + direction.getOffsetZ());
    }

}
