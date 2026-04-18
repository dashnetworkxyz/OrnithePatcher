package club.sk1er.patcher.mixins.bugfixes.crashes;

import net.minecraft.client.gui.widget.ServerListEntryWidget;
import net.minecraft.client.options.ServerListEntry;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.dashnetwork.patcher.Patcher;

@Mixin(ServerListEntryWidget.class)
public abstract class ServerListEntryWidgetMixin_ResolveCrash {

    @Shadow @Final
    private ServerListEntry entry;

    @Shadow
    protected abstract void loadServerIcon();

    @Redirect(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ServerListEntryWidget;loadServerIcon()V")
    )
    private void patcher$gracefulIconFail(ServerListEntryWidget widget) {
        try {
            loadServerIcon();
        } catch (Exception exception) {
            Patcher.LOGGER.error("Failed to prepare server icon, setting to default.", exception);
            entry.setIcon(null);
        }
    }

}
