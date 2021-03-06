package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class shoppingPage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(linkText = "Start Shopping �")
	public WebElement shoppingButton;
	@FindBy(xpath = "//li[@id='product-1']")
	public WebElement firstProduct;
	@FindBy(xpath = "//a[@href='#/cart']")
	public WebElement cartLink;
	@FindBy(xpath = "//a[@href='#/checkout']")
	public WebElement checkOutLink;
	@FindBy(xpath = "//p[@class='cart-msg']")
	public WebElement nonEmptyCartMessage;
	
	public shoppingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);
	}
	
	/*Function : To click on any product
	 * Argument: Pass the Product Name as an argument
	 */
	public void clickOnProduct(String item) throws InterruptedException {
		this.driver.findElement(By.xpath("//li[contains(@id, 'product-')]//h4[contains(text(), '" + item + "')]//following::p//a[contains(@ng-click, 'add(item)')]")).click();
		Thread.sleep(2000);
		
	}

}
