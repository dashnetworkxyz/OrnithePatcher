package club.sk1er.patcher.mixins.bugfixes;

import net.minecraft.client.gui.screen.Screen;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;
import java.net.URI;
import java.util.Locale;

@Mixin(Screen.class)
public class ScreenMixin_FixLinuxLink {

    @Shadow @Final
    private static Logger LOGGER;

    /**
     * @author MasterDash5
     * @reason java.awt.Desktop has no implementation for Linux
     */
    @Overwrite
    private void openLink(URI uri) {
        String platform = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        String url = uri.toString();
        String[] arguments;

        if (platform.contains("win"))
            arguments = new String[] { "rundll32", "url.dll,FileProtocolHandler", url };
        else if (platform.contains("mac"))
            arguments = new String[] { "open", url };
        else {
            if (uri.getScheme().equals("file"))
                url = url.replace("file:", "file://");

            arguments = new String[] { "xdg-open", url };
        }

        try {
            Process process = Runtime.getRuntime().exec(arguments);
            process.getInputStream().close();
            process.getOutputStream().close();
            process.getErrorStream().close();
        } catch (IOException exception) {
            LOGGER.error("Couldn't open link", exception);
        }
    }

}
