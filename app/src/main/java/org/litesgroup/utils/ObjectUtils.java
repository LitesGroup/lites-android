package org.litesgroup.utils;

import org.jetbrains.annotations.Nullable;

public class ObjectUtils {

    public static <T> T cast(@Nullable Object object) {
        //noinspection unchecked
        return (T) object;
    }
}
