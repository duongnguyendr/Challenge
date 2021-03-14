package com.aspire.pages;

import org.openqa.selenium.By;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;

public class MainPage extends PageObject{
	BasePage basePage;
	
	public MainPage() {
		basePage = new BasePage();
	}
	
	private static final String REGISTER_LINK = "//div[@class='login-step-start__register']/a[@href='/sg/register']";
	private static final String PHONE_INPUT = "//input[@name='phone']";
	private static final String FLAG_BTN = "//label[contains(@class, 'flag-select')]";
//	private static String LOGIN_BUTTON = "//button[@type='submit'][contains(@class, 'q-btn--actionable q-focusable q-hoverable')]";
	
	
	@Step
	public void clickRegisterLink() {
		find(By.xpath(REGISTER_LINK)).click();
	}
	
	@Step
	public void loginWithPhoneNumber(String phoneNumber) {
		inputPhoneNumber(phoneNumber);
		clickLoginButton();
	}
	
	public void inputPhoneNumber(String phoneNumber) {
		waitForAnyTextToAppear("Login to Aspire");
		find(By.xpath(FLAG_BTN)).click();
		find(By.xpath(PHONE_INPUT)).sendKeys(phoneNumber);
	}
	
	public void clickLoginButton() {
		basePage.clickSubmitButton();
	}
}
