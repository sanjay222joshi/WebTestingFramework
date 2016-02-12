package com.famous_smoke.automation.assertions;

/**
 *  <p>Contains the Assertion Messages to
 *  be used in our custom assertions.</p>
 *
 *  <p>All the messages are formatted to receive parameters
 *  in runtime.</p>
 */
public class AssertionMessages {
    public static final String URL_NOT_BRAND        = "The URL %s is not of a brand.";
    public static final String URL_NOT_BRANDGROUP   = "The URL %s is not of a brand group.";
    public static final String BRAND_NOT_IDENTIFIED = "The URL %s is not identified by a logo or a video.";
    public static final String FIELD_IS_EMPTY       = "The field %s in the URL %s is empty.";
    public static final String FIELD_LENGTH_IS_LESS = "The field %s in the URL %s length is inappropriate.\n"
                                                    + "Current length is %s.\n"
                                                    + "It should be greater or equal than %s.\n"
                                                    + "Field value is: %s";
    public static final String FIELD_LENGTH_IS_MORE = "The field %s in the URL %s length is inappropriate.\n"
                                                    + "Current length is %s.\n"
                                                    + "It should be less or equal than %s.\n"
                                                    + "Field value is: %s";
    public static final String FIELD_NOT_MATCHING   = "The field %s in the URL %s doesn't match the scrapped value.\n"
                                                    + "Current value is: %s.\n"
                                                    + "Expected values is: %s.";

    private AssertionMessages() {
        //not called
    }
}
