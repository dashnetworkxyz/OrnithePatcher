package club.sk1er.patcher.mixins.bugfixes;

import net.minecraft.client.options.ServerList;
import net.minecraft.client.options.ServerListEntry;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.*;
import xyz.dashnetwork.patcher.Patcher;

import java.util.List;

@Mixin(ServerList.class)
public abstract class ServerListMixin_ResolveNpe {

    @Unique
    private static final Logger patcher$logger = Patcher.get().logger();

    @Shadow @Final
    private List<ServerListEntry> entries;

    @Shadow
    public abstract void save();

    /**
     * @author LlamaLad7
     * @reason resolve NPE
     */
    @Overwrite
    public ServerListEntry get(int index) {
        try {
            return entries.get(index);
        } catch (Exception exception) {
            patcher$logger.error("Failed to get server entry.", exception);
            return null;
        }
    }

    /**
     * @author LlamaLad7
     * @reason resolve NPE
     */
    @Overwrite
    public void remove(int index) {
        try {
            entries.remove(index);
        } catch (Exception exception) {
            patcher$logger.error("Failed to remove server entry.", exception);
        }
    }

    /**
     * @author LlamaLad7
     * @reason resolve NPE
     */
    @Overwrite
    public void add(ServerListEntry entry) {
        try {
            entries.add(entry);
        } catch (Exception exception) {
            patcher$logger.error("Failed to add server entry.", exception);
        }
    }

    /**
     * @author LlamaLad7
     * @reason resolve NPE
     */
    @Overwrite
    public void swap(int index1, int index2) {
        try {
            ServerListEntry entry = get(index1);
            entries.set(index1, get(index2));
            entries.set(index2, entry);
            save();
        } catch (Exception exception) {
            patcher$logger.error("Failed to swap server entries.", exception);
        }
    }

    /**
     * @author LlamaLad7
     * @reason resolve NPE
     */
    @Overwrite
    public void set(int index, ServerListEntry entry) {
        try {
            entries.set(index, entry);
        } catch (Exception exception) {
            patcher$logger.error("Failed to set server entry.", exception);
        }
    }

}
