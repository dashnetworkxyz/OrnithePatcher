package club.sk1er.patcher.mixins.accessors;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Minecraft.class)
public interface MinecraftAccessor {

    @Accessor
    void setFullscreen(boolean fullscreen);

    @Accessor
    int getInitWidth();

    @Accessor
    int getInitHeight();

    @Invoker
    void invokeOnResolutionChanged();

}
