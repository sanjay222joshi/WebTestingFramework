package com.famous_smoke.automation.validators;

/**
 * <p>Provides validators for the HTML source code of a
 * website.</p>
 */
public class SourceValidators {

    private SourceValidators() {
        //not called
    }

    /**
     * Determines if the source belongs to a 404
     * response.
     * @param source the HTML Source of the webpage.
     * @return true if the the source matches the 404
     * pattern.
     */
    public static boolean isNotFound(final String source) {
        return source.toLowerCase().contains("unknown host")
                ||
                (source.toLowerCase().contains("not found") &&
                        source.contains("404")
                );
    }

    /**
     * Determines if the source belongs to a 500
     * response.
     * @param source the HTML Source of the webpage.
     * @return true if the the source matches the 500
     * pattern.
     */
    public static boolean isInternalError(final String source) {
        return source.toLowerCase().contains("internal server error");
    }
}
