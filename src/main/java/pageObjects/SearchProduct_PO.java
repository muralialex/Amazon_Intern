package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.BaseClass;

public class SearchProduct_PO extends BaseClass{
	
	public SearchProduct_PO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "input#twotabsearchtextbox")
	private WebElement search_TextBox;
	
	@FindBy(xpath = "(//div[@class='autocomplete-results-container']//div[contains(@class,'s-suggestion') and @role='button'])[1]")
	private WebElement first_AutoSuggest_Link;
	
	@FindBy(xpath = "//div[@class='autocomplete-results-container']//div[contains(@class,'s-suggestion') and @role='button']")
	private List<WebElement> all_AutoSuggest_Link;
		
	@FindBy(xpath = "//span[text()='RESULTS']")
	private WebElement results_Header;
	
	@FindBy(xpath = "(//div[@data-component-type='s-search-result'])[1]//h2//a")
	private WebElement firstProduct_result_Link;

	@FindBy(xpath = "(//div[@data-component-type='s-search-result'])[1]//h2//span")
	private WebElement firstProduct_result_Text;
	
	@FindBy(xpath = "(//div[@data-component-type='s-search-result'])[1]//span[@class='a-price-whole']")
	private WebElement firstProductPrice_result_Text;

	public WebElement getSearch_TextBox() {
		return search_TextBox;
	}

	public WebElement getFirst_AutoSuggest_Link() {
		return first_AutoSuggest_Link;
	}

	public List<WebElement> getAll_AutoSuggest_Link() {
		return all_AutoSuggest_Link;
	}

	public WebElement getResults_Header() {
		return results_Header;
	}

	public WebElement getFirstProduct_result_Link() {
		return firstProduct_result_Link;
	}

	public WebElement getFirstProduct_result_Text() {
		return firstProduct_result_Text;
	}

	public WebElement getFirstProductPrice_result_Text() {
		return firstProductPrice_result_Text;
	}

	
	
}
