package com.cucumberTests.steps;



import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;

public class CalculatorStepsDef {
	
	
	@BeforeStep
	public void before_each_step() {
	System.out.println("before step");	
	}
	@AfterStep
	public void after_eachStep() {
		System.out.println("after step");	
	}
	@Before
	public void before() {
		System.out.println("before");
	}
	@After
	public void after() {
		System.out.println("after");
	}
	@BeforeAll
	public void BeforeAll() {
		System.out.println("hello12");
	}
	@AfterAll
	public void AfterAll() {
		System.out.println("hello12");	
	}
	
	int a, b, res;

	@Given("a and b;")
	public void a_and_b() {
	  a=100;
	  b=2;
	}

	@When("i add a and b;")
	public void i_add_a_and_b() {
	    res= a+b;
	}

	@Then("result should be sum of the two number")
	public void result_should_be_sum_of_the_two_number() {
		int exp=102;
	Assert.assertEquals(res,exp);
	   }
	   
	@When("i subtract a and b;")
	public void i_subtract_a_and_b() {
	  res=a-b;
	}

	@Then("result should be subtraction of the two number")
	public void result_should_be_subtraction_of_the_two_number() {
	 int exp = 98;
	 Assert.assertEquals(res, exp);
	}
	
	@When("i multiply a and b;")
	public void i_multiply_a_and_b() {
	 int exp= 200;
	 
	}

	@Then("result should be multiplication of the two number")
	public void result_should_be_multiplication_of_the_two_number() {
	   
	}


}
