package vip.xelapedia.discgolf.loader.internal.util;

public final class KeyGenerator {
    public static String generateKey(final String name) {
        return name.replaceAll("[\\W_]", "").toUpperCase();
    }
}
