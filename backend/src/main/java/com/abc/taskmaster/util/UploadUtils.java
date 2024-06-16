package com.abc.taskmaster.util;

import java.util.Optional;
import java.util.function.Function;

public class UploadUtils {

    public static final Function<String, String> fileExtension = fileName -> {
        return Optional.of(fileName)
                .filter(name -> name.contains("."))
                .map(name -> "." + name.substring(fileName.lastIndexOf(".") + 1))
                .orElse(".png");
    };

}