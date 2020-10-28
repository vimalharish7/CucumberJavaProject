package pages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class contactPage {
	
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(linkText = "Contact")
	public WebElement contactLink;
	@FindBy(id = "forename")
	public WebElement forenameField;
	@FindBy(id = "surname")
	public WebElement surnameField;
	@FindBy(id = "telephone")
	public WebElement telephoneField;
	@FindBy(linkText = "Submit")
	public WebElement submitButton;
	@FindBy(id = "forename-err")
	public WebElement forenameError;
	@FindBy(id = "email-err")
	public WebElement emailError;
	@FindBy(id = "message-err")
	public WebElement messageBoxError;
	@FindBy(id = "email")
	public WebElement emailField;
	@FindBy(id = "message")
	public WebElement messageField;
	@FindBy(xpath = "//label[@for='forename']")
	public WebElement forenameLabel;
	@FindBy(xpath = "//label[@for='email']")
	public WebElement emailLabel;
	@FindBy(xpath = "//label[@for='message']")
	public WebElement messageLabel;
	@FindBy(linkText = "« Back")
	public WebElement backButton;
	@FindBy(xpath = "//div[@class='alert alert-success']")
	public WebElement successMessageAlert;
	@FindBy(id = "telephone-err")
	public WebElement telephoneError;
	
	public contactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);
	}
	
	public void enterName() {
		forenameField.sendKeys("Hello");
	}
	
	public void assertObjectisDisplayed(String actual, String expected) {
		Assert.assertEquals(actual, expected);
	}
	
	public void enterInvalidValues() {
		this.emailField.clear();
		this.emailField.sendKeys("check");
		this.telephoneField.clear();
		this.telephoneField.sendKeys("AAAAA");
		this.messageField.sendKeys(Keys.TAB);
	}
	
	public void enterRequiredFields() {
		this.forenameField.clear();
		this.forenameField.sendKeys("Harry");
		this.emailField.clear();
		this.emailField.sendKeys("a@b.com");
		this.messageField.clear();
		this.messageField.sendKeys("Hello World");
		this.messageField.sendKeys(Keys.TAB);
	}

}
