package club.sk1er.patcher.hooks;

import java.lang.reflect.Method;

public class OptiFineHook {

    public static long getBufferAllocated() {
        try {
            Class<?> NativeMemory = Class.forName("net.optifine.util.NativeMemory");
            Method getBufferAllocated = NativeMemory.getMethod("getBufferAllocated");

            return (long) getBufferAllocated.invoke(null);
        } catch (ReflectiveOperationException ignored) {}

        return -1;
    }

}
