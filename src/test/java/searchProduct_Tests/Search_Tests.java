package searchProduct_Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.Home_PO;
import pageObjects.SearchProduct_PO;
import testBase.BaseClass;
import utilities.TestUtilities;

public class Search_Tests extends BaseClass{
	
	public Home_PO home_PO;
	public TestUtilities util;
	public Search_Tests search_TESTS;
	public SearchProduct_PO searchProduct_PO;
	
	@BeforeClass //#AM-001
	public void beforeClass() {
		home_PO = new Home_PO(driver);
		searchProduct_PO = new SearchProduct_PO(driver);
		util = new TestUtilities(driver);
		util.explicitWaitinit(driver);
	}
	
//	#AM-008 Search for a Product in Search bar
	@Test(priority = 3)
	void searchProduct_SearchTextbox() throws InterruptedException {
		driver.navigate().to(prop.getProperty("url"));
		if(util.verifyElementDisplayed(home_PO.getAmazon_logo())) {
			System.out.println("Amazon logo displayed");}
		if(util.verifyElementDisplayed(searchProduct_PO.getSearch_TextBox())) {
			System.out.println("ALL slider icon displayed");}
		util.elementToBeClickableAt(searchProduct_PO.getSearch_TextBox());
		searchProduct_PO.getSearch_TextBox().sendKeys("WWE 2K22");
		util.blindWait(3);
		util.visibilityofWebElement(searchProduct_PO.getFirst_AutoSuggest_Link());
		util.printFromLists(searchProduct_PO.getAll_AutoSuggest_Link());
		util.elementToBeClickableAt(searchProduct_PO.getFirst_AutoSuggest_Link());
		util.visibilityofWebElement(searchProduct_PO.getResults_Header());
		if(util.verifyElementDisplayed(searchProduct_PO.getFirstProduct_result_Link())) {
			System.out.println("Product displayed for Search result");}
		System.out.println(util.getText(searchProduct_PO.getFirstProduct_result_Text()));
		System.out.println(util.getText(searchProduct_PO.getFirstProductPrice_result_Text()));
		util.elementToBeClickableAt(searchProduct_PO.getFirstProduct_result_Link());
		
	}

}
