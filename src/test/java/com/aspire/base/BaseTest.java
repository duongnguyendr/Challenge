package com.aspire.base;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)
public class BaseTest {
	@Managed
	public WebDriver driver;
	
	@Before
	public void suiteSetup() {
		driver.get("https://feature-qa.customer-frontend.staging.aspireapp.com/sg/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
}
