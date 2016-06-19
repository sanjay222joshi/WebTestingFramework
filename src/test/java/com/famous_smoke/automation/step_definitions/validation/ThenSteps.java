package com.famous_smoke.automation.step_definitions.validation;


import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.pageobjects.BrandItemPage;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;

import static com.famous_smoke.automation.assertions.FamousSmokeAssertions.assertThat;


/**
 * Created by jorge on 11-01-2016.
 */
public class ThenSteps {

    @Then("^the brand identification should be visible$")
    public void the_brand_identification_should_be_vivisble() throws Throwable {
        assertThat(Hooks.testBrandPageData).isIdentified();
    }

    @Then("^the item identification should be visible$")
    public void the_item_identification_should_be_visible() throws Throwable {
        assertThat(Hooks.testBrandItemPageData).isIdentified();
    }
    
  //Added as na.gif validator
    @Then("^the page should not have any na_gif image$")
    public void the_page_does_not_have_any_na_gif_image() throws Throwable {
        assertThat(BrandItemPage.hasNaImage()).isFalse();
    }

}
