package com.aspire.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.pages.PageObject;

public class PersonalDetailsPage extends PageObject{
	@Managed
	BasePage basePage;
	@Managed
	DatePicker datePicker;

	private static final String PERSONAL_DETAILS_PAGE_TITLE = "Personal Details";
	
	private static final String HEADER_TITLE = "//div[@class='absolute full-width']//div[contains(@class, 'text-h4')]";
	
	private static final String FULL_NAME_FIELD = "//div[@name='full_name']";
	
	private static final String PHONE_NUMBER_FIELD = "//div[@name='phone']";

	private static final String EMAIL_FIELD = "//div[@name='email']";
	
	private static final String ID_CARD_FIELD = "//div[@label='Identity Card Number']//input";
	
	private static final String DATE_OF_BIRTHD_FIELD = "//div[@label='Date of Birth']//input";
	
	private static final String NATIONALITY_INPUT = "//div[@label='Nationality']//input";
	
	private static final String GENDER_INPUT = "//div[@label='Gender']//input";
	
	private static final String PURPOSES = "//div[@data-cy='person-edit-purposes'][@url='options']";
	
	public void verifyPersonalDetailsPageWithCorrectInfo(String name, String phoneNumber, String email) {
		verifyPersonalDetailsPage();
		verifyFullNameDisplayCorrectly(name);
		verifyPhoneNumberDisplayCorrectly(phoneNumber);
		verifyEmailDisplayCorrectly(email);
	}
	
	public void inputPersonalInformation(
			String idCard, String dateOfBirth, String nationality, String gender, String purposes) {
		inputIDCardNumber(idCard);
		selectDateOfBirth(dateOfBirth);
		selectGender(gender);
		selectNationality(nationality);
		selectPurpose(purposes);
		basePage.clickSubmitButton();
	}
	
	public void verifyPersonalDetailsPage() {
		waitForAnyTextToAppear(PERSONAL_DETAILS_PAGE_TITLE);
		String title = find(By.xpath(HEADER_TITLE)).getText();
		assertEquals(title, PERSONAL_DETAILS_PAGE_TITLE);
		
	}
	
	public void verifyFullNameDisplayCorrectly(String fullname) {
		String fName = find(By.xpath(FULL_NAME_FIELD)).getAttribute("value");
		assertEquals(fName, fullname);
	}
	
	public void verifyPhoneNumberDisplayCorrectly(String phoneNumber) {
		String phone = find(By.xpath(PHONE_NUMBER_FIELD)).getAttribute("value");
		boolean isContains = phone.contains(phoneNumber);
		assertTrue(isContains);
	}
	
	public void verifyEmailDisplayCorrectly(String email) {
		String sEmail = find(By.xpath(EMAIL_FIELD)).getAttribute("value");
		assertEquals(email, sEmail);
	}
	
	public void inputIDCardNumber(String cardNumber) {
		find(By.xpath(ID_CARD_FIELD)).sendKeys(cardNumber);
	}
	
	public void selectDateOfBirth(String dateOfBirth) {
		find(By.xpath(DATE_OF_BIRTHD_FIELD)).click();
		datePicker.selectDateInPicker(dateOfBirth);
	}
	
	public void selectNationality(String country) {
		find(By.xpath(NATIONALITY_INPUT)).click();
		find(By.xpath(NATIONALITY_INPUT)).sendKeys(country);
		basePage.selectOptionByName(country);
		
	}
	
	public void selectGender(String gender) {
		find(By.xpath(GENDER_INPUT)).click();
		basePage.selectOptionByName(gender);
	}
	
	public void selectPurpose(String purposes) {
		find(By.xpath(PURPOSES)).click();
		basePage.selectOptionByName(purposes);
		find(By.xpath(PURPOSES)).click();
	}
}
