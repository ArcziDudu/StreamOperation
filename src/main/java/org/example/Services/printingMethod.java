package org.example.Services;

import java.util.Map;

public abstract class printingMethod {
    static void printing(Map<?, ?> inputMap) {
        inputMap.forEach((key, value) -> System.out.println(key + " " + value));
    }
}
