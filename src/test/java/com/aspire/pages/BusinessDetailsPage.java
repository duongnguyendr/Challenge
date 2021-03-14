package com.aspire.pages;

import org.openqa.selenium.By;

import net.thucydides.core.pages.PageObject;

public class BusinessDetailsPage extends PageObject{
	
	BasePage basePage;
	
	private static final String BUSINESS_DETAILS_HEADER = "Business Details";
	private static final String BUSINESS_NAME = "//div[@label='Business Name']//input";
	private static final String REGISTRATION_TYPE = "//div[@label='Registration Type']//div[@class='q-field__append q-field__marginal row no-wrap items-center q-anchor--skip']";
	private static final String UEN_FIELD = "//input[@data-cy='register-business-registration-numer']";
	private static final String INDUSTRY_FIELD = "//div[@label='Industry']//div[@class='q-field__append q-field__marginal row no-wrap items-center q-anchor--skip']";
	private static final String SUB_INDUSTRY_FIELD = "//div[@label='Sub Industry']//div[@class='q-field__append q-field__marginal row no-wrap items-center q-anchor--skip']";
	
	public BusinessDetailsPage() {
		basePage = new BasePage();
	}
	
	public void inputBusinessDetails(String businessName, String type, String UENNumber, String industry, String subIndustry) {
		inputBusinessName(businessName);
		selectRegistrationType(type);
		inputUENNumber(UENNumber);
		selectIndustry(industry);
		selectSubIndustry(subIndustry);
		basePage.clickSubmitButton();
	}

	public void verifyBusinessDetailsPage() {
		basePage.verifyPageLoaded(BUSINESS_DETAILS_HEADER);
	}
	
	public void inputBusinessName(String name) {
		find(By.xpath(BUSINESS_NAME)).sendKeys(name);
	}
	
	public void selectRegistrationType(String type) {
		for (int i = 0; i < 10; i ++) {
			find(By.xpath(REGISTRATION_TYPE)).click();
		}
		find(By.xpath(REGISTRATION_TYPE)).click();
		basePage.selectOptionByName(type);
	}
	
	public void inputUENNumber(String uenNumber) {
		find(By.xpath(UEN_FIELD)).sendKeys(uenNumber);
	}
	
	public void selectIndustry(String industry) {
		find(By.xpath(INDUSTRY_FIELD)).click();
		basePage.waitSomeSeconds(2);
		basePage.selectOptionByName(industry);
	}
	
	public void selectSubIndustry(String subIndustry) {
		find(By.xpath(SUB_INDUSTRY_FIELD)).click();
		basePage.waitSomeSeconds(2);
		basePage.selectOptionByName(subIndustry);
	}

}
