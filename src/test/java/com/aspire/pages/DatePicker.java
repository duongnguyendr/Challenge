package com.aspire.pages;

import java.text.DateFormatSymbols;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.common.collect.Range;

import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class DatePicker extends PageObject{
	
	private static final String NAV_LEFT = "//div[@class='col-auto'][1]//button[contains(@class, 'q-btn-item')]";
	private static final String NAV_RIGHT = "//div[@class='col-auto'][2]//button[contains(@class, 'q-btn-item')]";
	
	private static final String YEAR_PICKER = "//div[@class='relative-position overflow-hidden flex flex-center']";
	private static final String MONTH_PICKER = "//div[@class='relative-position overflow-hidden flex flex-center col']";
	
	private static final String YEAR_ITEMS = "//div[contains(@class, 'q-date__years-content')]//button";
	private static final String MONTH_ITEMS = "//div[contains(@class, 'date__months-item')]/button";
	private static final String DAY_ITEMS = "//div[@class= 'q-date__calendar-days fit']/div/button";
	
	
	public void selectDateInPicker(String date) {
		String [] dates = date.split("-");
		String sMonth  = new DateFormatSymbols().getShortMonths()[Integer.parseInt(dates[1])];
		selectYearInDatePicker(dates[2]);
		selectMonthInDatePicker(sMonth);
		selectDayInDatePicker(dates[0]);
		
	}
	
	private void selectDayInDatePicker(String sday) {
		ListOfWebElementFacades day_items = findAll(By.xpath(DAY_ITEMS));
		for (WebElementFacade item : day_items) {
			if(item.getText().equals(sday)) {
				item.click();
				break;
			}
		}
	}
	
	private void selectMonthInDatePicker(String month) {
		find(By.xpath(MONTH_PICKER)).click();
		ListOfWebElementFacades month_items = findAll(By.xpath(MONTH_ITEMS));
		for (WebElementFacade item : month_items) {
			if(item.getText().equals(month)) {
				item.click();
				break;
			}
		}
		
	}
	
	private void selectYearInDatePicker(String year) {
		waitFor(ExpectedConditions.elementToBeClickable(By.xpath(YEAR_PICKER)));
		find(By.xpath(YEAR_PICKER)).click();
		clickYear(year);
		
	}
	
	private boolean clickYear(String year) {
		find(By.xpath(YEAR_ITEMS)).isCurrentlyVisible();
		ListOfWebElementFacades yearItems = findAll(By.xpath(YEAR_ITEMS));
		int firstYear = Integer.parseInt(yearItems.get(0).getText());
		int lastYear = Integer.parseInt(yearItems.get(yearItems.size() - 1).getText());
		System.out.println("First:" + firstYear);
		System.out.println("Last:"+ lastYear);
		Range<Integer> range = Range.closed(firstYear, lastYear);
		if (range.contains(Integer.parseInt(year))) {
			for(WebElementFacade ele : yearItems) {
				if (ele.getText().equals(year)) {
					ele.click();
					break;
				}
			}
		}else if(Integer.parseInt(year) < firstYear) {
			waitFor(NAV_LEFT);
			find(By.xpath(NAV_LEFT)).click();
			clickYear(year);
			
		}else {
			waitFor(NAV_RIGHT);
			find(By.xpath(NAV_RIGHT)).click();
			clickYear(year);
		}
		return false;
	}

}
