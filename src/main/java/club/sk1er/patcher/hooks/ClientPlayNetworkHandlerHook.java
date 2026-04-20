package club.sk1er.patcher.hooks;

import net.minecraft.network.packet.s2c.play.ResourcePackS2CPacket;
import xyz.dashnetwork.patcher.Patcher;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class ClientPlayNetworkHandlerHook {

    public static boolean validateResourcePack(ResourcePackS2CPacket packet) {
        try {
            String url = packet.getUrl();
            final URI uri = new URI(url);
            final String scheme = uri.getScheme();
            final boolean isLevelProtocol = scheme.equals("level");

            if (!scheme.equals("http") && !scheme.equals("https") && !isLevelProtocol)
                return true;

            url = URLDecoder.decode(url.substring("level://".length()), StandardCharsets.UTF_8);

            if (isLevelProtocol && (url.contains("..") || !url.endsWith("/resources.zip")))
                throw new URISyntaxException(url, "Invalid levelstorage resourcepack path");

            return true;
        } catch (URISyntaxException exception) {
            Patcher.get().logger().error("Invalid resourcepack given by server!", exception);
            return false;
        }
    }

}
