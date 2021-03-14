package com.aspire.pages;

import org.openqa.selenium.By;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;

public class IdentifyDetailsPage extends PageObject{
	private static final String UPLOAD_IMAGE_BTN = "//button//input[@type='file']";
	
	@Managed
	BasePage basePage;
	
	@Step
	public void verifyKTPStep(String ktpImage) {
		uploadKTPImages(ktpImage);
		clickContinueButton();
		takeSelfiePicture();
		clickContinueButton();
	}
	
	public void uploadKTPImages(String image) {
		find(By.xpath(UPLOAD_IMAGE_BTN)).sendKeys(image);
	}
	
	public void takeSelfiePicture() {
		find(By.xpath(UPLOAD_IMAGE_BTN)).click();
		basePage.clickSubmitButton();
	}
	
	public void clickContinueButton() {
		basePage.clickSubmitButton();
	}
}
