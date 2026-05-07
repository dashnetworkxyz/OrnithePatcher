package xyz.dashnetwork.patcher.config;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ConfigOptions {

    // Features
    public AtomicBoolean f3nKeybind = new AtomicBoolean(false);
    public AtomicBoolean borderlessFullscreen = new AtomicBoolean(false);
    public AtomicBoolean removeWaterOverlay = new AtomicBoolean(false);
    public AtomicBoolean fixedInventoryPosition = new AtomicBoolean(false);
    public AtomicInteger extendedChatLength = new AtomicInteger(1); // AUTO

    // Performance
    public AtomicBoolean batchModelRendering = new AtomicBoolean(false);

}
