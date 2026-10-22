package xyz.dashnetwork.patcher.config;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ConfigOptions {

    // Features
    public final AtomicBoolean f3nKeybind = new AtomicBoolean(false);
    public final AtomicBoolean borderlessFullscreen = new AtomicBoolean(false);
    public final AtomicBoolean removeWaterOverlay = new AtomicBoolean(false);
    public final AtomicBoolean fixedInventoryPosition = new AtomicBoolean(true);
    public final AtomicBoolean removeChatLogLimit = new AtomicBoolean(false);
    public final AtomicBoolean disableToast = new AtomicBoolean(false);
    public final AtomicBoolean scoreboardNumbers = new AtomicBoolean(true);
    public final AtomicBoolean betterCamera = new AtomicBoolean(true);
    public final AtomicInteger extendedChatLength = new AtomicInteger(1); // AUTO
    public final AtomicInteger pumpkinOverlayOpacity = new AtomicInteger(100);

    // Performance
    public final AtomicBoolean batchModelRendering = new AtomicBoolean(false);

}
