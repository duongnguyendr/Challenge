package com.aspire.pages;

import static com.aspire.pages.BasePage.SUBMIT_BTN;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;

public class RegistrationPage extends PageObject{
	BasePage basePage;
	
	public RegistrationPage() {
		basePage = new BasePage();
	}

	private static String FULL_NAME_INPUT = "//input[@name='full_name']";
	
	private static String EMAIL_INPUT = "//input[@name='email']";
	
	private static String PHONE_INPUT = "//input[@name='phone']";
	
	private static String APPOINTER_DIRECTOR_RADIO = "//div[@aria-label='Appointed director'][@role='radio']/div[contains(@class, 'q-radio__inner')]";
	
	private static String NON_DIRECTOR_RADIO = "//div[@aria-label='Non-director'][@role='radio']/div[contains(@class, 'q-radio__inner')]";
	
	private static String REFERRAL_INFO = "//div[@label='Where did you hear about us?']//input";
	
	private static String REFERRAL_INFO_LIST = "//div[contains(@style,'visibility: visible;')]";
	
	private static String TERM_AND_CONDIRIONS = "//div[contains(@class,'register-step-person__privacy')]//div[@class='q-checkbox__bg absolute']";

	private static final String FLAG_BTN = "//label[contains(@class, 'flag-select')]";
	
	
	@Step("Input Register Form")
	public void inputRegisterForm(String fullname, String email, String phoneNumber, String referral) {
		inputFullname(fullname);
		inputEmail(email);
		inputPhoneNumber(phoneNumber);
		selectAppointedDirectorCheckbox();
		selectReferralInformation(referral);
		clickTermAndConditions();
		verifyContinueButtonHasEnabled();
		clickContinueButton();
	}
	
	public void inputFullname(String fullname) {
		find(By.xpath(FULL_NAME_INPUT)).sendKeys(fullname);
	}
	@Step("Input email")
	public void inputEmail(String email) {
		find(By.xpath(EMAIL_INPUT)).sendKeys(email);
	}
	
	public void inputPhoneNumber(String phoneNumber) {
		find(By.xpath(FLAG_BTN)).click();
		find(By.xpath(PHONE_INPUT)).sendKeys(phoneNumber);
	}
	
	public void selectAppointedDirectorCheckbox() {
		find(By.xpath(APPOINTER_DIRECTOR_RADIO)).click();
	}
	
	public void selectNonDirectorCheckbox() {
		find(By.xpath(NON_DIRECTOR_RADIO)).click();
	}
	
	public void selectReferralInformation(String referalFrom) {
		find(By.xpath(REFERRAL_INFO)).click();
		chooseReferralFrom(referalFrom);
	}
	
	private void chooseReferralFrom(String referalFrom) {
		String xpath = String.format("//div[text()='%s']", referalFrom);
		find(By.xpath(REFERRAL_INFO_LIST)).find(By.xpath(xpath)).click();
	}
	
	public void clickTermAndConditions() {
		find(By.xpath(TERM_AND_CONDIRIONS)).click();
	}
	
	public void clickContinueButton() {
		basePage.clickSubmitButton();
	}
	
	public void verifyContinueButtonHasEnabled() {
		String class_value = find(By.xpath(SUBMIT_BTN)).getAttribute("class");
		boolean result = class_value.contains("q-focusable q-hoverable");
		assertTrue("Continue Button should be enabled", result);
	}
}

