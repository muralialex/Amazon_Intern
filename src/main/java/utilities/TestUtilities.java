package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import testBase.BaseClass;


public class TestUtilities extends BaseClass{
	
	public static WebDriver driver;
	public WebDriverWait wait;
	public Select s;
	public int i;
	public int count = 3;
	public SoftAssert sa;
	public static String filepath = "user.dir" + "/Screenshots";

	public TestUtilities(WebDriver driver) {
		this.driver=driver;
	}
	public boolean verifyElementDisplayed(WebElement element) {
		boolean status= false;
		if(element.isDisplayed()) {
			status= true;
		}
		return status;
	}
	
	public boolean verifyMultipleElementsDisplayed(List<WebElement> elements, String att_or_Text, String attrType, String value) {
		boolean status= false;
		int size= elements.size();
		int stat= 0;
		if(att_or_Text.equalsIgnoreCase("getAttribute")) {
			for(WebElement element:elements) {
				if(element.getAttribute(attrType).contains(value)) {
					stat++;
				}
			}
		}
		else if(att_or_Text.equalsIgnoreCase("getText")) {
			for(WebElement element:elements) {
				if(element.getText().contains(value)) {
					stat++;
				}
			}
		}
		
		if(size==stat) {
			status=true;
		}
		return status;
	}
	
	public List<String> getAllElements_Text(List<WebElement> elements) {
		ArrayList<String> myElements_Text= new ArrayList<String>();
		for(WebElement element:elements) {
			myElements_Text.add(element.getText());
		}
		return myElements_Text;
	}
	
	public void printFromStringList(List<String> myDatas) {
		for(String text:myDatas) {
			System.out.println(text);
		}
	}
	
	public void printFromLists(List<WebElement> elements) {
		ArrayList<String> myElements_Text= new ArrayList<String>();
		for(WebElement element:elements) {
			System.out.println(element.getText());
		}
	}
	
	public void printFromList_JS(List<WebElement> elements) {
		ArrayList<String> myElements_Text= new ArrayList<String>();
		for(WebElement element:elements) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			System.out.println(((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML;",element));
		}
	}
	
	public void blindWait(int seconds) throws InterruptedException {
		Thread.sleep(seconds*1000);
	}
	public WebDriver switchtoFrameByWebElement(WebElement frameElement) {

		driver.switchTo().frame(frameElement);
		return driver;
	}

	public void switchtoFrameByIndex(int frameNumber) {

		driver.switchTo().frame(frameNumber);
	}

	public void switchtoFrameByName(String frameName) {

		driver.switchTo().frame(frameName);
	}

	public void explicitWaitinit(WebDriver driver) {
		wait = new WebDriverWait(driver, 10);
	}

	public void visibilityofWebElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void elementToBeClickableAt(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void SelectByValue(WebElement ele, String SelectValue) {
		s = new Select(ele);
		s.selectByValue(SelectValue);
	}

	public void SelectValue(WebDriver driver, WebElement element, String value, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
		s = new Select(element);
		s.selectByValue(value);
	}

	public void SelectVisibleText(WebDriver driver, WebElement element, String value, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
		s = new Select(element);
		s.selectByVisibleText(value);
	}

	public void SelectByVisibleText(WebElement ele, String StringText) {
		s = new Select(ele);
		s.selectByVisibleText(StringText);
	}

	public String CreateFolderForScreenshot() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		Date date = new Date();
		File file = new File(prop.getProperty("ScreenshotPath") + sdf.format(date));
		file.mkdir();
		String newScreenshotFolderPath = file.getAbsolutePath();
		return newScreenshotFolderPath;
	}

	public void takeScreenshots(WebDriver wd, String newScreenshotFolderPath, String filename) throws Exception {

		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		// Date date= new Date();
		TakesScreenshot ts = (TakesScreenshot) wd;
		File src = ts.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(src, new File(prop.getProperty("ScreenshotPath") + "\\" + filename + ".png"));
	}

	public void takeScreenshot(WebDriver driver, String fileName) throws Exception {
		try {
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(prop.getProperty("ScreenshotPath") + fileName + ".png"));

			// FileHandler.copy(file, new
			// File("C:\\Users\\ddillibai\\eclipse-workspace\\Vodafone_uk\\test-output"+fileName+".jpg"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void FailedTC() {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(
					System.getProperty("user.dir")+"/FailedShots"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void clickById(WebDriver driver, String id) {
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.id(id))).click().build().perform();
	}

	public void clickByXpath(WebDriver driver, String xpath) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath(xpath))).perform();
		act.moveToElement(driver.findElement(By.xpath(xpath))).click().build().perform();
	}

	public void selectDateByJS(WebDriver driver, WebElement element, String dateVal) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].setAttribute('value','" + dateVal + "');", element);

	}

	public static void selectCalenderDateJS(WebDriver driver, String id, String date) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + id + "').textContent='" + date + "';");

	}

	static String pageLoadStatus;
	static JavascriptExecutor js;

	public static void waitForPageLoad1() {

		do {

			js = (JavascriptExecutor) driver;

			pageLoadStatus = (String) js.executeScript("return document.readyState");

		} while (!pageLoadStatus.equals("complete"));

		System.out.println("Page Loaded.");

	}

	public void waitForPageLoad(WebDriver driver, int timeout) {
		wait = new WebDriverWait(driver, timeout);
		wait.until(new Function<WebDriver, Object>() {
			public Object apply(WebDriver webDriver) {
				return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
			}
		});
	}

	/*
	 * public void waitForPageLoad(WebDriver driver, int timeout) { wait = new
	 * WebDriverWait(driver, timeout); wait.until(webDriver -> ((JavascriptExecutor)
	 * webDriver).executeScript("return document.readyState") .equals("complete"));
	 * }
	 */

	public static void selectDateByJSDate(WebDriver driver, WebElement element, String dateVal) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].setAttribute('value','" + dateVal + "');", element);

	}

	public void waitForPageLoad2(WebDriver driver, int timeout) {

//	    Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		wait = new WebDriverWait(driver, timeout);
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
//	            System.out.println("Current Window State       : "
//	                + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}

	// Method to enter the values
	public void sendKeys(WebDriver driver, WebElement element, String value, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}

	// method to clear the text field
	public void clear(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.clear();
	}

	// method to click on element using explicit wait
	public void clickOn(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	// method to check visibility of an element
	public WebElement clickOn1(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public void clickOnAfterPageLoad(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.stalenessOf(element));
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.click();
	}

	// method to click using actions class
	public void clickOnActions(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
	}

	// method to click using js
	public void clickUsingJS(WebDriver driver, WebElement element, int timeout) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	// method to get text
	public String getText(WebElement element) {
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
		return element.getText();
	}

	// method to select the value
	public void selectValue(WebDriver driver, WebElement element, int timeout) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("return document.readyState", "complete");
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		executor.executeScript("arguments[0].click();", element);
	}

	public static void clickOnforStale(WebDriver driver, WebElement locator, int timeout) {
		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(locator));
		locator.click();
	}

	public void inVisibilityofEle(WebDriver driver, WebElement locator, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOf(locator));
	}
	
	public class ReadExcelConfig {
		XSSFWorkbook wb;
		XSSFSheet sheet1;

		public ReadExcelConfig(String excelpath) {
			try {
				File src = new File(filepath);
				FileInputStream fis = new FileInputStream(src);
				wb = new XSSFWorkbook(fis);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public String getData(int sheetNumber, int row, int column) {
			sheet1 = wb.getSheetAt(sheetNumber);
			String data = sheet1.getRow(row).getCell(column).getStringCellValue();
			return data;
		}

		public int getRowCount(int SheetIndex) {
			int row = wb.getSheetAt(SheetIndex).getLastRowNum();
			row = row + 1;
			return row;
		}
	}

	// This method is used to get current date(yyyy-mm-dd)
	public String getcurrentdate() {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date currentdate = new Date();
		String Date = formater.format(currentdate);
		System.out.println(Date);
		return Date;
	}

	// This method is used to add days for current date
	public String getaltereddate(int daystobeadded) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, daystobeadded);
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String Date = formater.format(cal.getTime());
		System.out.println("Taking 5+ days from current day for api validation " + Date);
		return Date;
	}

}
