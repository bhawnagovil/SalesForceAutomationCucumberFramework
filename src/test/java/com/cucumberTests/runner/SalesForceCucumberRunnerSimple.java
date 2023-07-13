package com.cucumberTests.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	features= {"src/test/resources/feature/SalesForceLogin.feature"},
	glue = {"com.cucumberTests.steps"}, monochrome= true,
	plugin= {"pretty", "html:target/HtmlReports"})
		
public class SalesForceCucumberRunnerSimple extends AbstractTestNGCucumberTests {

}
