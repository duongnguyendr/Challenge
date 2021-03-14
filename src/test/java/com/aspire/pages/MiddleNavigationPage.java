package com.aspire.pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;

public class MiddleNavigationPage extends PageObject{
	BasePage basePage;
	public MiddleNavigationPage() {
		basePage = new BasePage();
	}
	
	private static final String BUSINESS_DETAILS_HEADER = "Let’s know you better!";
	private static final String CORPORATE_HEADER = "Is your business incorporated in Singapore?";
	
	private static final String MY_INFO_REGISTRATION_GET_STARTED = "//div[contains(@class, 'q-pb-md onboarding-step-register')]//button";
	
	private static final String STANDARD_REGISTRATION_GET_STARTED = "//div[contains(@class, 'q-mt-lg onboarding-step-register')]//button";
	
	private static final String CONTINUE_BTN_WITH_BUSINESS = "//div[contains(@class, 'q-pb-md incorporated_selector__column')]//button";
	
	private static final String CONTINUE_BTN_NO_BUSINESS = "//div[contains(@class, 'q-mt-lg incorporated_selector__column')]//button";
	
	private static final String HEADER_TITLE = "//div[@class='absolute full-width']//div[contains(@class, 'text-h4')]";
	
	@Step
	public void navigateToPersonalDetailPage() {
		verifyBusinessCorporatedPage();
		clickContinueHasBusiness();
		clickGetStartWithStandardRegistration();
		clickGetStartedButton();
	}
	
	public void veirifyBusinessDetails() {
		waitForAnyTextToAppear(BUSINESS_DETAILS_HEADER);
		String header = find(By.xpath(HEADER_TITLE)).getText();
		assertEquals(BUSINESS_DETAILS_HEADER, header);
	}
	
	public void clickGetStartWithMyInfoRegistration() {
		find(By.xpath(MY_INFO_REGISTRATION_GET_STARTED)).click();
	}
	
	public void clickGetStartWithStandardRegistration() {
		find(By.xpath(STANDARD_REGISTRATION_GET_STARTED)).click();
	}
	
	public void verifyBusinessCorporatedPage() {
		waitForAnyTextToAppear(CORPORATE_HEADER);
		String header = find(By.xpath(HEADER_TITLE)).getText();
		assertEquals(CORPORATE_HEADER, header);
	}
	
	public void clickContinueHasBusiness() {
		find(By.xpath(CONTINUE_BTN_WITH_BUSINESS)).click();
	}
	
	public void clickContinueNoBusiness() {
		find(By.xpath(CONTINUE_BTN_NO_BUSINESS)).click();
	}
	
	public void clickGetStartedButton() {
		basePage.clickSubmitButton();	
	}
	
	public void clickBeginVerification() {
		basePage.clickSubmitButton();	
	}
}
