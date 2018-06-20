package com.qwinix.api.qa;


import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)


@CucumberOptions(features = "src/test/resources/features", tags = {"@Login_api"})


public class CukesTest {

}