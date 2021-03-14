package com.aspire.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;


import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;

public class OTPVerificationPage extends PageObject{
	private static String PHONE_TITLE = "Enter phone OTP";
	private static String EMAIL_TITLE = "Enter email OTP";
	
	private static String HEADER_TITLE = "//div[@class='absolute full-width']//div[contains(@class, 'text-h4')]";
	private static String PHONE_NUMBER = "//form//div[contains(@class, 'verify-otp__recipient')]";
	private static String INPUT_CODE = "//form//div[contains(@class, 'digit-input__input')]";
	private static String CURSOR_INPUT = "//form//div[contains(@class, 'cursor-pointer digit-input__input')]";
	private static String INPUT_DIGIT = "//div[@class='digit-input']/input";

	@Step("Verify OTP Verification Page")
	public void verifyOTPDestinationIsCorrect(String phoneNumber) {
		verifySmsVerificationPage();
		String phoneNum = find(By.xpath(PHONE_NUMBER)).getText();
		phoneNum = phoneNum.replace(" ", "");
		boolean result = phoneNum.contains(phoneNumber);
		assertTrue(String.format("Expected: %s, but found: %s", phoneNumber, phoneNum), result);
	}
	
	@Step
	public void verifySMSOTPCode(String phoneNumber, String otpCode) {
		verifySmsVerificationPage();
		verifyOTPDestinationIsCorrect(phoneNumber);
		inputVerificationCode(otpCode);
	}
	@Step
	public void verifyEmailOTPCode(String email, String otpCode) {
		verifySmsVerificationPage();
		verifyOTPDestinationIsCorrect(email);
		inputVerificationCode(otpCode);
	}
	
	public void verifySmsVerificationPage() {
		waitForTextToAppear(PHONE_TITLE, 15000);
		String title = find(By.xpath(HEADER_TITLE)).getText();
		assertEquals(title, PHONE_TITLE);
	}
	
	public void verifyEmailVerificationPage() {
		waitForTextToAppear(EMAIL_TITLE, 15000);
		String title = find(By.xpath(HEADER_TITLE)).getText();
		assertEquals(title, EMAIL_TITLE);
	}
	
	public void inputVerificationCode(String codes) {
		String smsCode[] = codes.split(",");
		List<WebElementFacade> listCodeField = findAll(By.xpath(INPUT_CODE));
		int index = 0;
		for (@SuppressWarnings("unused") WebElementFacade ele : listCodeField) {
			waitForPresenceOf(CURSOR_INPUT);
			find(By.xpath(INPUT_DIGIT)).sendKeys(smsCode[index]);
			index += 1;
		}
	}
	
	public void verifyInputCodeSuccessfully() {
		waitForAnyTextToAppear("You have successfully verified your mobile number. You’re on to a great start!");
	}
}
