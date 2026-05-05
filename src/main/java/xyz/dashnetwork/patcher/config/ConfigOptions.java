package xyz.dashnetwork.patcher.config;

import java.util.concurrent.atomic.AtomicBoolean;

public class ConfigOptions {

    // Features
    public AtomicBoolean f3nKeybind = new AtomicBoolean(true);
    public AtomicBoolean borderlessFullscreen = new AtomicBoolean(false);
    public AtomicBoolean removeWaterOverlay = new AtomicBoolean(false);

    // Performance
    public AtomicBoolean batchModelRendering = new AtomicBoolean(true);

}
