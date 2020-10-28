package StepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pages.*;

public class testSteps {
	
	WebDriver driver = null;
	contactPage contactPage;
	shoppingPage shoppingPage;
	WebDriverWait wait;
	String tempValue;
	
	@Before
	public void launchWebsite() {
		String sourcePath = System.getProperty("user.dir");
	    System.setProperty("webdriver.chrome.driver", sourcePath + "/src/test/resources/drivers/chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.get("http://jupiter.cloud.planittesting.com");
	    wait = new WebDriverWait(driver, 15);
		contactPage = new contactPage(driver);
		shoppingPage = new shoppingPage(driver);
	    wait.until(ExpectedConditions.visibilityOf(contactPage.contactLink));
	}
	
	@Given("the user launch the homepage url and navigate to contact page")
	public void the_user_launch_the_homepage_url_and_navigate_to_contact_page() {
	    contactPage.contactLink.click();
	    wait.until(ExpectedConditions.visibilityOf(contactPage.forenameField));
	}

	@When("the user click on submit button without entering details")
	public void the_user_click_on_submit_button_without_entering_details() throws InterruptedException {
		contactPage.submitButton.click();
		Thread.sleep(4000);
		
	}

	@Then("the error messages are displayed")
	public void the_error_messages_are_displayed() {
		
		contactPage.forenameError.isDisplayed();
		contactPage.emailError.isDisplayed();
		contactPage.messageBoxError.isDisplayed();
		
		Assert.assertEquals(contactPage.forenameError.getText(), "Forename is required");
		Assert.assertEquals(contactPage.emailError.getText(), "Email is required");
		Assert.assertEquals(contactPage.messageBoxError.getText(), "Message is required");
	
		String errorColor = "#b94a48";
		String actualforenameError = contactPage.forenameError.getCssValue("color");
		String actualEmailError = contactPage.emailError.getCssValue("color");
		String actualMessageBoxError = contactPage.messageBoxError.getCssValue("color");
		Assert.assertEquals(Color.fromString(actualforenameError).asHex(), errorColor);
		Assert.assertEquals(Color.fromString(actualEmailError).asHex(), errorColor);
		Assert.assertEquals(Color.fromString(actualMessageBoxError).asHex(), errorColor);
		
		String actualforenameBGColor = contactPage.forenameField.getCssValue("border-color");
		String actualEmailBGColor = contactPage.emailField.getCssValue("border-color");
		String actualMessageBoxBGColor = contactPage.messageField.getCssValue("border-color");
		Assert.assertEquals(Color.fromString(actualforenameBGColor).asHex(), "#e9322d");
		Assert.assertEquals(Color.fromString(actualEmailBGColor).asHex(), errorColor);
		Assert.assertEquals(Color.fromString(actualMessageBoxBGColor).asHex(), errorColor);
		
		String actualForeameLabelError = contactPage.forenameLabel.getCssValue("color");
		String actualEmailLabelError = contactPage.emailLabel.getCssValue("color");
		String actualMessageLabelError = contactPage.messageLabel.getCssValue("color");
		Assert.assertEquals(Color.fromString(actualForeameLabelError).asHex(), errorColor);
		Assert.assertEquals(Color.fromString(actualEmailLabelError).asHex(), errorColor);
		Assert.assertEquals(Color.fromString(actualMessageLabelError).asHex(), errorColor);

		Assert.assertEquals(contactPage.errorAlert.getText(), "We welcome your feedback - but we won't get it unless you complete the form correctly.");
		String alertError = contactPage.errorAlert.getCssValue("color");
		Assert.assertEquals(Color.fromString(alertError).asHex(), errorColor);
	}

	@When("the user enters the mandatory fields")
	public void the_user_enters_the_mandatory_fields() throws InterruptedException {
		contactPage.enterRequiredFields();
		tempValue = contactPage.forenameField.getAttribute("value");
		Thread.sleep(3000);
	
	}

	@Then("the error messages should not be displayed")
	public void the_error_messages_should_not_be_displayed() {

		try {
			Assert.assertFalse("Forename Error is Displayed", contactPage.forenameError.isDisplayed());
			Assert.assertFalse("Email Error is Displayed", contactPage.emailError.isDisplayed());
			Assert.assertFalse("Message Error is Displayed", contactPage.messageBoxError.isDisplayed());
		}catch(Exception e) {
			System.out.println("Element not displayed!");
		}
		
		String expectedColor = "#333333";
		String actualForeameLabel = contactPage.forenameLabel.getCssValue("color");
		String actualEmailLabel = contactPage.emailLabel.getCssValue("color");
		String actualMessageLabel = contactPage.messageLabel.getCssValue("color");
		Assert.assertEquals(Color.fromString(actualForeameLabel).asHex(), expectedColor);
		Assert.assertEquals(Color.fromString(actualEmailLabel).asHex(), expectedColor);
		Assert.assertEquals(Color.fromString(actualMessageLabel).asHex(), expectedColor);
	}

	@When("click on the submit button")
	public void click_on_the_submit_button() throws InterruptedException {
		contactPage.submitButton.click();
		wait.until(ExpectedConditions.visibilityOf(contactPage.successMessageAlert));	
	}

	@Then("the success message is displayed to the user")
	public void the_success_message_is_displayed_to_the_user() throws InterruptedException {
		
		Thread.sleep(3000);
		contactPage.successMessageAlert.isDisplayed();
		String successColor = "#468847";
		String actualColor = contactPage.successMessageAlert.getCssValue("color");
		Assert.assertEquals(Color.fromString(actualColor).asHex(), successColor);
		
		Assert.assertEquals(contactPage.successMessageAlert.getText(), "Thanks "+ tempValue + ", we appreciate your feedback.");
	}

	@When("the user enters invalid data on input fields")
	public void the_user_enters_invalid_data_on_input_fields() throws InterruptedException {
		contactPage.enterInvalidValues();
		Thread.sleep(3000);
		
	}

	@Then("the error messsages are displayed to the user")
	public void the_error_messsages_are_displayed_to_the_user() {
		
		contactPage.emailError.isDisplayed();
		Assert.assertEquals(contactPage.emailError.getText(), "Please enter a valid email");
		contactPage.telephoneError.isDisplayed();
		Assert.assertEquals(contactPage.telephoneError.getText(), "Please enter a valid telephone number");
		
		String errorColor = "#b94a48";
		String actualEmailError = contactPage.emailError.getCssValue("color");
		Assert.assertEquals(Color.fromString(actualEmailError).asHex(), errorColor);
		String actualTelephoneError = contactPage.telephoneError.getCssValue("color");
		Assert.assertEquals(Color.fromString(actualTelephoneError).asHex(), errorColor);
		
	}

	@Given("the user launch the homepage url and navigate to shop page")
	public void the_user_launch_the_homepage_url_and_navigate_to_shop_page() {

		shoppingPage.shoppingButton.click();
	    wait.until(ExpectedConditions.visibilityOf(shoppingPage.firstProduct));
	    String cartValue = shoppingPage.cartLink.getText();
	    Assert.assertEquals(cartValue, "Cart (0)");
	    
	}

	@When("the user adds {string} item multiple times to cart")
	public void the_user_adds_item_multiple_times_to_cart(String item) throws InterruptedException {
		shoppingPage.clickOnProduct(item);
		Assert.assertEquals(shoppingPage.cartLink.getText(), "Cart (1)");
		shoppingPage.clickOnProduct(item);
		Assert.assertEquals(shoppingPage.cartLink.getText(), "Cart (2)");
	}

	@When("the user adds {string} item to cart")
	public void the_user_adds_item_to_cart(String item) throws InterruptedException {
		shoppingPage.clickOnProduct(item);
		Assert.assertEquals(shoppingPage.cartLink.getText(), "Cart (3)");
	}

	@When("the user clicks on cart menu")
	public void the_user_clicks_on_cart_menu() {
		shoppingPage.cartLink.click();
		wait.until(ExpectedConditions.visibilityOf(shoppingPage.checkOutLink));
	}

	@Then("the items are present in the shopping cart")
	public void the_items_are_present_in_the_shopping_cart() throws InterruptedException {
		
		Thread.sleep(3000);
		String cartMsg = shoppingPage.nonEmptyCartMessage.getText();
		Assert.assertEquals(shoppingPage.cartLink.getText(), "Cart (3)");
		Assert.assertEquals(cartMsg, "There are 3 items in your cart, you can Checkout or carry on Shopping.");
		
		
		String funnyCowQuantity = driver.findElement(By.xpath("//tr[contains(@class, 'cart-item')]//td[contains(text(),'Funny Cow')]//following::input[@name='quantity']")).getAttribute("value");
		System.out.println("Funny cow Quantity: " + funnyCowQuantity);
		String fluffyBunnyQuantity = driver.findElement(By.xpath("//tr[contains(@class, 'cart-item')]//td[contains(text(),'Fluffy Bunny')]//following::input[@name='quantity']")).getAttribute("value");
		System.out.println("Fluffy Bunny Quantity: " + fluffyBunnyQuantity);
		Assert.assertEquals(funnyCowQuantity, "2");
		Assert.assertEquals(fluffyBunnyQuantity, "1");
	}
	
	@After
	public void closeExecution() {
		driver.close();
	    driver.quit();
	}

}
