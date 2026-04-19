package club.sk1er.patcher.mixins.bugfixes.crashes;

import net.minecraft.client.network.ServerAddress;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.net.IDN;

@Mixin(ServerAddress.class)
public class ServerAddressMixin_ResolveCrash {

    @Shadow @Final
    private String address;

    /**
     * @author LlamaLad7
     * @reason Fix crash - MC-89698
     */
    @Overwrite
    public String getAddress() {
        try {
            return IDN.toASCII(address);
        } catch (IllegalArgumentException exception) {
            return "";
        }
    }

}
