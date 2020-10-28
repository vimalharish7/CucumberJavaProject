package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class contactPage {
	
	WebDriver driver;

	@FindBy(id = "forename")
	WebElement forenameField;
	@FindBy(id = "surname")
	WebElement surnameField;
	
	public contactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterName() {
		forenameField.sendKeys("Hello");
	}

}
