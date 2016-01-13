package com.famous_smoke.automation.factory;

import com.famous_smoke.automation.pageobjects.HeaderSection;
import org.openqa.selenium.SearchContext;

/**
 * Created by jorge on 11-01-2016.
 */
public class SectionFactory {
    private SectionFactory() {
        //not called
    }

    public static HeaderSection createHeader(final SearchContext context,
                                             final String selector) {
        return new HeaderSection(context, selector);
    }
}
