package com.famous_smoke.automation.validators;

/**
 * Created by jorge on 29-12-2015.
 */
public class SourceValidators {

    private SourceValidators() {
        //not called
    }

    public static boolean isNotFound(final String source) {
        return source.toLowerCase().contains("unknown host")
                ||
                (source.toLowerCase().contains("not found") &&
                        source.contains("404")
                );
    }

    public static boolean isInternalError(final String source) {
        return source.toLowerCase().contains("internal server error");
    }
}
