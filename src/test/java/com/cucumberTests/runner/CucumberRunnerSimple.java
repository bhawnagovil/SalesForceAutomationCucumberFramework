package com.cucumberTests.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	features= {"src/test/resources/feature/simpleFeature.feature"},
	glue = {"com.cucumberTests.steps"}			
		)
public class CucumberRunnerSimple extends AbstractTestNGCucumberTests {

}
