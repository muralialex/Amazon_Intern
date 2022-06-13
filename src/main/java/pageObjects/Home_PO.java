package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.BaseClass;



public class Home_PO extends BaseClass{
	
	public Home_PO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@aria-label='Amazon']")
	private WebElement amazon_logo;
	
	@FindBy(id = "nav-global-location-popover-link")
	private WebElement location_text;
	
	@FindBy(id = "nav-search-dropdown-card")
	private WebElement all_dropdown;
	
	@FindBy(name = "field-keywords")
	private WebElement search_textbox;
	
	@FindBy(xpath = "//input[@id='nav-search-submit-button']")
	private WebElement search_button;
	
	@FindBy(xpath = "//a[contains(@aria-label,'Choose a language')]")
	private WebElement language_dropdown;
	
	@FindBy(xpath = "//a[@data-nav-role='signin' and @id='nav-link-accountList']")
	private WebElement signIn_dropdown;
	
	@FindBy(id = "nav-orders")
	private WebElement orders_link;
	
	@FindBy(id = "nav-cart")
	private WebElement cart_link;
	
	@FindBy(xpath = "//a[@aria-label='Open Menu']")
	private WebElement openMenu_drop;
	
	@FindBy(xpath = "//div[@id='nav-xshop-container']//div[@class='nav-progressive-content']//a")
	private List<WebElement> allCategories_link;
	
	@FindBy(id = "nav-swmslot")
	private WebElement ad_on_Category_link;
	
	public WebElement getAmazon_logo() {
		return amazon_logo;
	}

	public WebElement getLocation_text() {
		return location_text;
	}

	public WebElement getAll_dropdown() {
		return all_dropdown;
	}

	public WebElement getSearch_textbox() {
		return search_textbox;
	}

	public WebElement getSearch_button() {
		return search_button;
	}

	public WebElement getLanguage_dropdown() {
		return language_dropdown;
	}

	public WebElement getSignIn_dropdown() {
		return signIn_dropdown;
	}

	public WebElement getOrders_link() {
		return orders_link;
	}

	public WebElement getCart_link() {
		return cart_link;
	}

	public WebElement getOpenMenu_drop() {
		return openMenu_drop;
	}

	public List<WebElement> getAllCategories_link() {
		return allCategories_link;
	}

	public WebElement getAd_on_Category_link() {
		return ad_on_Category_link;
	}
	
	
}
