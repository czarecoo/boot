package com.czareg.boot.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.springframework.lang.Nullable;

import java.util.function.Function;

@UtilityClass
public class NullUtil {

    public static <T, R> R applyOrNull(@NonNull Function<T, R> function, @Nullable T initialValue) {
        if (initialValue == null) {
            return null;
        }
        return function.apply(initialValue);
    }

    public static <T, R, U> U applyOrNull(@NonNull Function<T, R> firstfunction, @NonNull Function<R, U> secondfunction, @Nullable T initialValue) {
        R firstFunctionResult = applyOrNull(firstfunction, initialValue);
        return applyOrNull(secondfunction, firstFunctionResult);
    }
}