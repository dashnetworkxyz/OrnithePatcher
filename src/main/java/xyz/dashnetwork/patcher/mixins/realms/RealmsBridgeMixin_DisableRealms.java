package xyz.dashnetwork.patcher.mixins.realms;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.realms.RealmsScreenProxy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RealmsBridge.class)
public class RealmsBridgeMixin_DisableRealms {

    /**
     * @author MasterDash5
     * @reason Realms no longer functional
     */
    @Overwrite
    public void createMainMenuScreen(Screen parent) {
        // no-op
    }

    /**
     * @author MasterDash5
     * @reason Realms no longer functional
     */
    @Overwrite
    public RealmsScreenProxy createNotificationsScreen(Screen parent) {
        return null;
    }

}
