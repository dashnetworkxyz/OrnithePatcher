package club.sk1er.patcher.mixins.accessors;

import net.minecraft.client.render.vertex.BufferBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BufferBuilder.class)
public interface BufferBuilderAccessor {

    @Accessor("building")
    boolean isBuilding();

}
