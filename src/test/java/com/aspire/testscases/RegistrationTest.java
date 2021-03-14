package com.aspire.testscases;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.aspire.base.BaseTest;
import com.aspire.data.TestData;
import com.aspire.pages.BasePage;
import com.aspire.pages.BusinessDetailsPage;
import com.aspire.pages.IdentifyDetailsPage;
import com.aspire.pages.MainPage;
import com.aspire.pages.MiddleNavigationPage;
import com.aspire.pages.PersonalDetailsPage;
import com.aspire.pages.RegistrationPage;
import com.aspire.pages.OTPVerificationPage;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Screenshots;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistrationTest extends BaseTest{
	@Managed
	BasePage basePage;
	@Managed
	public MainPage mainPage;
	@Managed
	public RegistrationPage registrationPage;
	@Managed
	public OTPVerificationPage oTPVerificationPage;
	@Managed 
	public MiddleNavigationPage navigationPage;
	@Managed
	public PersonalDetailsPage personalDetailPage;
	@Managed
	BusinessDetailsPage businessDetailPage;
	@Managed
	IdentifyDetailsPage identityDetailPage;
	
	@Test
	@Screenshots(afterEachStep=true)
	public void a_registerAccount() {
		mainPage.clickRegisterLink();
		registrationPage.inputRegisterForm(TestData.FULL_NAME, TestData.EMAIL, TestData.PHONE_NUMBER, TestData.REFERRAL_INFO);
		oTPVerificationPage.verifyOTPDestinationIsCorrect(TestData.PHONE_NUMBER);
		oTPVerificationPage.inputVerificationCode(TestData.OTP_CODE);
	}
	
	@Test
	@Screenshots
	public void b_registerPersonalDetails() {
		mainPage.loginWithPhoneNumber(TestData.PHONE_NUMBER);
		oTPVerificationPage.verifySMSOTPCode(TestData.PHONE_NUMBER, TestData.OTP_CODE);
		navigationPage.navigateToPersonalDetailPage();
		personalDetailPage.verifyPersonalDetailsPage();
		personalDetailPage.verifyPersonalDetailsPageWithCorrectInfo(TestData.FULL_NAME, TestData.PHONE_NUMBER, TestData.EMAIL);
		personalDetailPage.inputPersonalInformation(TestData.ID_CARD, TestData.DATE_OF_BIRTH, TestData.NATIONALITY, TestData.GENDER, TestData.PURPOSES);
		oTPVerificationPage.verifyEmailOTPCode(TestData.EMAIL, TestData.OTP_CODE);
	}
	
	@Test
	@Screenshots
	public void c_registerBusinessDetails() {
		mainPage.loginWithPhoneNumber(TestData.PHONE_NUMBER);
		oTPVerificationPage.verifySMSOTPCode(TestData.PHONE_NUMBER, TestData.OTP_CODE);
		navigationPage.clickGetStartedButton();
		businessDetailPage.verifyBusinessDetailsPage();
		businessDetailPage.inputBusinessDetails(TestData.COMPANY_NAME, TestData.REGISTRATION_TYPE, TestData.UEN_NUMBER, TestData.INDUSTRY, TestData.SUB_INDUSTRY);
	}
	
	@Test
	@Screenshots
	public void d_verificationWithIdentityDetails() {
		mainPage.loginWithPhoneNumber(TestData.PHONE_NUMBER);
		oTPVerificationPage.verifySMSOTPCode(TestData.PHONE_NUMBER, TestData.OTP_CODE);
		navigationPage.clickGetStartedButton();
		navigationPage.clickBeginVerification();
		identityDetailPage.verifyKTPStep(TestData.KTP_IMAGE);
		basePage.verifyRegisterAccountSuccessfully();
	}
	
}
