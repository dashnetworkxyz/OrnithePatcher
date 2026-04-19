package club.sk1er.patcher.mixins.performance;

import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@Mixin(RedstoneTorchBlock.class)
public class RedstoneTorchBlockMixin_MemoryLeak {

    @Shadow
    private static Map<World, List<?>> RECENT_TOGGLES = new WeakHashMap<>();

}
