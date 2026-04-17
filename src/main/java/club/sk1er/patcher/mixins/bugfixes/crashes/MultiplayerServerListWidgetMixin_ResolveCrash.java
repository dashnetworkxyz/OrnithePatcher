package club.sk1er.patcher.mixins.bugfixes.crashes;

import net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.gui.widget.LanServerEntry;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(MultiplayerServerListWidget.class)
public class MultiplayerServerListWidgetMixin_ResolveCrash {

    @Shadow @Final private List<LanServerEntry> lanServers;
    @Shadow @Final private EntryListWidget.Entry scanningWidget;

    @Inject(
            method = "getEntry",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/multiplayer/MultiplayerServerListWidget;lanServers:Ljava/util/List;", opcode = Opcodes.GETFIELD),
            cancellable = true
    )
    private void patcher$resolveIndexError(int index, CallbackInfoReturnable<EntryListWidget.Entry> cir) {
        if (index >= lanServers.size())
            cir.setReturnValue(scanningWidget);
    }

}
