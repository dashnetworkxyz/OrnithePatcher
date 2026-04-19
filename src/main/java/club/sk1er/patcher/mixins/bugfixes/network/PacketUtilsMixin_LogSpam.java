package club.sk1er.patcher.mixins.bugfixes.network;

import net.minecraft.client.network.handler.ClientPlayNetworkHandler;
import net.minecraft.network.DifferentThreadException;
import net.minecraft.network.PacketUtils;
import net.minecraft.network.handler.PacketHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.BlockableEventLoop;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PacketUtils.class)
public class PacketUtilsMixin_LogSpam {

    /**
     * @author MasterDash5
     * @reason Prevent log spam from closed connections
     */
    @Overwrite
    public static <T extends PacketHandler> void ensureOnSameThread(final Packet<T> packet, final T listener, BlockableEventLoop runner) throws DifferentThreadException {
        if (!runner.isOnSameThread()) {
            runner.executeTask(() -> {
                if (listener instanceof ClientPlayNetworkHandler handler) {
                    if (handler.getConnection().isConnected())
                        packet.handle(listener);
                } else
                    packet.handle(listener);
            });
            throw DifferentThreadException.INSTANCE;
        }
    }

}
