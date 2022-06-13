package home_Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.Home_PO;
import testBase.BaseClass;
import utilities.TestUtilities;


public class Home_Tests extends BaseClass{
	
	public Home_PO home_PO;
	public TestUtilities util;
	public Home_Tests home_TESTS;
	
	@BeforeClass //#AM-001
	public void beforeClass() {
		home_PO = new Home_PO(driver);
		util = new TestUtilities(driver);
		util.explicitWaitinit(driver);
	}
	
	@Test(priority = 0)
	public void verifyHeaders() throws SecurityException, IOException {
		log.info("============================Executing "+new Object(){}.getClass().getEnclosingMethod().getName()+" Testcase============================");
		if(util.verifyElementDisplayed(home_PO.getAmazon_logo())) {
			System.out.println("Amazon logo displayed");}
		if(util.verifyElementDisplayed(home_PO.getLocation_text())) {
			System.out.println("Location displayed");}
		util.visibilityofWebElement(home_PO.getAll_dropdown());
		if(util.verifyElementDisplayed(home_PO.getAll_dropdown())) {
			System.out.println("Search Dropdown displayed");}
		if(util.verifyElementDisplayed(home_PO.getSearch_textbox())) {
			System.out.println("Search Box displayed");}
		if(util.verifyElementDisplayed(home_PO.getSearch_button())) {
			System.out.println("Search Key displayed");}
		if(util.verifyElementDisplayed(home_PO.getLanguage_dropdown())) {
			System.out.println("Language selection displayed");}
		if(util.verifyElementDisplayed(home_PO.getSignIn_dropdown())) {
			System.out.println("SignIn selection displayed");}
		if(util.verifyElementDisplayed(home_PO.getOrders_link())) {
			System.out.println("Returns& Orders displayed");}	
		if(util.verifyElementDisplayed(home_PO.getCart_link())) {
			System.out.println("Cart displayed");}		
	}
	
	@Test(priority = 1)
	void verifyCategories_AmazonHome() throws SecurityException, IOException {
//		startTest(new Object(){}.getClass().getEnclosingMethod());
		log.info("============================Executing "+new Object(){}.getClass().getEnclosingMethod().getName()+" Testcase============================");
		driver.navigate().to(prop.getProperty("url"));
		if(util.verifyElementDisplayed(home_PO.getAmazon_logo())) {
			System.out.println("Amazon logo displayed");}
		if(util.verifyElementDisplayed(home_PO.getOpenMenu_drop())) {
			System.out.println("ALL slider icon displayed");}
		if(util.verifyMultipleElementsDisplayed(
				home_PO.getAllCategories_link(),"getAttribute","class","nav-a")) {
			System.out.println("All elements are displayed under Category section");}
		List<String> categories= util.getAllElements_Text(home_PO.getAllCategories_link());
		util.printFromList_JS(home_PO.getAllCategories_link());
		if(util.verifyElementDisplayed(home_PO.getAd_on_Category_link())) {
			System.out.println("Amazon Ad image displayed");}
	}
	
//	#AM-005 Verify Sign In Page
	@Test(priority = 2)
	void VerifySignInPage() {
		log.info("============================Executing "+new Object(){}.getClass().getEnclosingMethod().getName()+" Testcase============================");
		driver.navigate().to(prop.getProperty("url"));
		if(util.verifyElementDisplayed(home_PO.getAmazon_logo())) {
			System.out.println("Amazon logo displayed");}
		if(util.verifyElementDisplayed(home_PO.getOpenMenu_drop())) {
			System.out.println("ALL slider icon displayed");}
		if(util.verifyMultipleElementsDisplayed(
				home_PO.getAllCategories_link(),"getAttribute","class","nav-a")) {
			System.out.println("All elements are displayed under Category section");}
		List<String> categories= util.getAllElements_Text(home_PO.getAllCategories_link());
//		amazonHomePage.printFromList(categories);
		util.printFromList_JS(home_PO.getAllCategories_link());
		if(util.verifyElementDisplayed(driver.findElement(By.id("nav-swmslot")))) {
			System.out.println("Amazon Ad image displayed");}
	}
	


}
