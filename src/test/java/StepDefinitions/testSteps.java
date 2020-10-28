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
		//Dynamic Chrome driver path
		System.setProperty("webdriver.chrome.driver", sourcePath + "/src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("http://jupiter.cloud.planittesting.com");
		//Explicit Wait object - Waiting for element to be visible
		wait = new WebDriverWait(driver, 15);
		
		//Objects for POM Classes
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

		//Verify Error Message displayed in front-end
		contactPage.forenameError.isDisplayed();
		contactPage.emailError.isDisplayed();
		contactPage.messageBoxError.isDisplayed();

		//Verify valid error message is displayed
		Assert.assertEquals(contactPage.forenameError.getText(), "Forename is required");
		Assert.assertEquals(contactPage.emailError.getText(), "Email is required");
		Assert.assertEquals(contactPage.messageBoxError.getText(), "Message is required");

		//Verify Error Message displayed in dark red
		//Assumption: The color of all error messages are consistent  
		String errorColor = "#b94a48";
		String actualforenameError = contactPage.forenameError.getCssValue("color");
		String actualEmailError = contactPage.emailError.getCssValue("color");
		String actualMessageBoxError = contactPage.messageBoxError.getCssValue("color");
		Assert.assertEquals(Color.fromString(actualforenameError).asHex(), errorColor);
		Assert.assertEquals(Color.fromString(actualEmailError).asHex(), errorColor);
		Assert.assertEquals(Color.fromString(actualMessageBoxError).asHex(), errorColor);

		//Verify text fields are highlighted in red
		String actualforenameBGColor = contactPage.forenameField.getCssValue("border-color");
		String actualEmailBGColor = contactPage.emailField.getCssValue("border-color");
		String actualMessageBoxBGColor = contactPage.messageField.getCssValue("border-color");
		Assert.assertEquals(Color.fromString(actualforenameBGColor).asHex(), "#e9322d");
		Assert.assertEquals(Color.fromString(actualEmailBGColor).asHex(), errorColor);
		Assert.assertEquals(Color.fromString(actualMessageBoxBGColor).asHex(), errorColor);

		//Verify text labels are highlighted in red
		String actualForeameLabelError = contactPage.forenameLabel.getCssValue("color");
		String actualEmailLabelError = contactPage.emailLabel.getCssValue("color");
		String actualMessageLabelError = contactPage.messageLabel.getCssValue("color");
		Assert.assertEquals(Color.fromString(actualForeameLabelError).asHex(), errorColor);
		Assert.assertEquals(Color.fromString(actualEmailLabelError).asHex(), errorColor);
		Assert.assertEquals(Color.fromString(actualMessageLabelError).asHex(), errorColor);

		//Verify top error message
		Assert.assertEquals(contactPage.errorAlert.getText(), "We welcome your feedback - but we won't get it unless you complete the form correctly.");
		String alertError = contactPage.errorAlert.getCssValue("color");
		Assert.assertEquals(Color.fromString(alertError).asHex(), errorColor);
	}

	@When("the user enters the mandatory fields")
	public void the_user_enters_the_mandatory_fields() throws InterruptedException {
		contactPage.enterRequiredFields();
		//Storing forename in a global variable
		tempValue = contactPage.forenameField.getAttribute("value");
		Thread.sleep(3000);

	}

	@Then("the error messages should not be displayed")
	public void the_error_messages_should_not_be_displayed() {

		try {
			//Verify error message are not displayed in front-end
			Assert.assertFalse("Forename Error is Displayed", contactPage.forenameError.isDisplayed());
			Assert.assertFalse("Email Error is Displayed", contactPage.emailError.isDisplayed());
			Assert.assertFalse("Message Error is Displayed", contactPage.messageBoxError.isDisplayed());
		}catch(Exception e) {
			System.out.println("Element not displayed!");
		}

		//Verify text labels are changed to default color
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
		//Verify success alert is displayed
		contactPage.successMessageAlert.isDisplayed();
		
		//Verify success alert is colored green
		String successColor = "#468847";
		String actualColor = contactPage.successMessageAlert.getCssValue("color");
		Assert.assertEquals(Color.fromString(actualColor).asHex(), successColor);

		//Verify success alert is personalized message
		Assert.assertEquals(contactPage.successMessageAlert.getText(), "Thanks "+ tempValue + ", we appreciate your feedback.");
	}

	@When("the user enters invalid data on input fields")
	public void the_user_enters_invalid_data_on_input_fields() throws InterruptedException {
		contactPage.enterInvalidValues();
		Thread.sleep(3000);

	}

	@Then("the error messsages are displayed to the user")
	public void the_error_messsages_are_displayed_to_the_user() {

		//Verify Invalid entries error message is displayed
		contactPage.emailError.isDisplayed();
		Assert.assertEquals(contactPage.emailError.getText(), "Please enter a valid email");
		contactPage.telephoneError.isDisplayed();
		Assert.assertEquals(contactPage.telephoneError.getText(), "Please enter a valid telephone number");

		//Verify error message color is red
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
		//Verify Cart count is initially zero
		Assert.assertEquals(cartValue, "Cart (0)");

	}

	@When("the user adds {string} item multiple times to cart")
	public void the_user_adds_item_multiple_times_to_cart(String item) throws InterruptedException {
		//Dynamically passing the item as argument
		shoppingPage.clickOnProduct(item);
		Assert.assertEquals(shoppingPage.cartLink.getText(), "Cart (1)");
		shoppingPage.clickOnProduct(item);
		Assert.assertEquals(shoppingPage.cartLink.getText(), "Cart (2)");
	}

	@When("the user adds {string} item to cart")
	public void the_user_adds_item_to_cart(String item) throws InterruptedException {
		//Dynamically passing the item as argument
		shoppingPage.clickOnProduct(item);
		Assert.assertEquals(shoppingPage.cartLink.getText(), "Cart (3)");
	}

	@When("the user clicks on cart menu")
	public void the_user_clicks_on_cart_menu() {
		shoppingPage.cartLink.click();
		//Verify checkout page
		wait.until(ExpectedConditions.visibilityOf(shoppingPage.checkOutLink));
	}

	@Then("the items are present in the shopping cart")
	public void the_items_are_present_in_the_shopping_cart() throws InterruptedException {

		Thread.sleep(3000);
		//Verify Cart items alert message
		String cartMsg = shoppingPage.nonEmptyCartMessage.getText();
		Assert.assertEquals(shoppingPage.cartLink.getText(), "Cart (3)");
		Assert.assertEquals(cartMsg, "There are 3 items in your cart, you can Checkout or carry on Shopping.");


		//Verify only expected items are present in cart and exact quantity
		String funnyCowQuantity = driver.findElement(By.xpath("//tr[contains(@class, 'cart-item')]//td[contains(text(),'Funny Cow')]//following::input[@name='quantity']")).getAttribute("value");
		String fluffyBunnyQuantity = driver.findElement(By.xpath("//tr[contains(@class, 'cart-item')]//td[contains(text(),'Fluffy Bunny')]//following::input[@name='quantity']")).getAttribute("value");
		Assert.assertEquals(funnyCowQuantity, "2");
		Assert.assertEquals(fluffyBunnyQuantity, "1");
	}

	@After
	public void closeExecution() {
		driver.close();
		driver.quit();
	}

}
