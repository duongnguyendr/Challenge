package com.aspire.pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;import org.openqa.selenium.support.ui.ExpectedConditions;

import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class BasePage extends PageObject{
	private static final String SUCCESS_MESSAGE = "You have successfully completed the KYC processs and we have received your documents.";
	
	public static final String OPTIONS = "//div[contains(@class, 'q-menu--square')][contains(@style, 'visibility: visible')]//div[contains(@class, 'q-item__label')]";
	public static final String HEADER_TITLE = "//div[@class='absolute full-width']//div[contains(@class, 'text-h4')]";
	public static final String SUBMIT_BTN = "//button[@type='submit']";
	private static final String MESSAGE_ELE = "//div[@class='aspire-cta-screen__content']/p";
	
	public void selectOptionByName(String option) {
		waitFor(ExpectedConditions.presenceOfElementLocated(By.xpath(OPTIONS)));
		ListOfWebElementFacades options = findAll(By.xpath(OPTIONS));
		for (WebElementFacade ele : options) {
			if (ele.getText().equals(option)) {
				ele.click();
				break;
			}
		}
	}

	public void verifyPageLoaded(String expectedTitle) {
		waitForAnyTextToAppear(expectedTitle);
		String header = find(By.xpath(HEADER_TITLE)).getText();
		assertEquals(expectedTitle, header);
	}
	
	public void clickSubmitButton() {
		find(By.xpath(SUBMIT_BTN)).click();		
	}
	
	public void waitSomeSeconds(int seconds) {
		try {
			Thread.sleep(seconds *1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void verifyRegisterAccountSuccessfully() {
		waitForAnyTextToAppear(SUCCESS_MESSAGE);
		String msg = find(By.xpath(MESSAGE_ELE)).getText();
		assertEquals(SUCCESS_MESSAGE, msg);
		clickSubmitButton();
	}
}
