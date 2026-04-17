package xyz.dashnetwork.ornithepatcher.mixin.old;

import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.dashnetwork.ornithepatcher.OrnithePatcher;

import java.io.IOException;
import java.net.URI;
import java.util.Locale;

@Mixin(Screen.class)
public final class ScreenMixin {

    @Inject(method = "openLink", at = @At("HEAD"), cancellable = true)
    public void onOpenLink(URI uri, CallbackInfo callback) {
        callback.cancel();

        String stringUri = uri.toString();

        String[] execArguments = switch (System.getProperty("os.name").toLowerCase(Locale.ROOT)) {
            case "win" -> new String[] { "rundll32", "url.dll,FileProtocolHandler", stringUri };
            case "mac" -> new String[] { "open", stringUri };
            default -> {
                if (uri.getScheme().equals("file"))
                    stringUri = stringUri.replaceFirst("file:", "file://");

                yield new String[] { "xdg-open", stringUri };
            }
        };

        try {
            Process process = Runtime.getRuntime().exec(execArguments);
            process.getInputStream().close();
            process.getErrorStream().close();
            process.getOutputStream().close();
        } catch (IOException exception) {
            OrnithePatcher.LOGGER.error("Couldn't open location '{}'", uri, exception);
        }
    }

}
