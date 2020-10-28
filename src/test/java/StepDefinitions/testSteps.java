package StepDefinitions;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.*;
import pages.*;

public class testSteps {
	
	WebDriver driver = null;
	contactPage contactPage;
	
	@Given("the user launch the homepage url and navigate to contact page")
	public void the_user_launch_the_homepage_url_and_navigate_to_contact_page() {
		String sourcePath = System.getProperty("user.dir");
	    System.setProperty("webdriver.chrome.driver",sourcePath + "/src/test/resources/drivers/chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    
	    driver.navigate().to("http://jupiter.cloud.planittesting.com");
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Contact")));
	    driver.findElement(By.linkText("Contact")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("forename")));
	    
	    contactPage = new contactPage(driver);
	    contactPage.enterName();
	    driver.close();
	    driver.quit();
	}

	@When("the user click on submit button without entering details")
	public void the_user_click_on_submit_button_without_entering_details() {
		
	}

	@Then("the error messages are displayed")
	public void the_error_messages_are_displayed() {
		System.out.println("Hello world");
	}

	@When("the user enters the mandatory fields")
	public void the_user_enters_the_mandatory_fields() {
		System.out.println("Hello world");
	}

	@Then("the error messages should not be displayed")
	public void the_error_messages_should_not_be_displayed() {
		System.out.println("Hello world");
	}

	@When("click on the submit button")
	public void click_on_the_submit_button() {
		System.out.println("Hello world");
	}

	@Then("the success message is displayed to the user")
	public void the_success_message_is_displayed_to_the_user() {
		System.out.println("Hello world");
	}

	@When("the user enters invalid data on inout fields")
	public void the_user_enters_invalid_data_on_inout_fields() {
		System.out.println("Hello world");
	}

	@Then("the error messsages are displayed to the user")
	public void the_error_messsages_are_displayed_to_the_user() {
		System.out.println("Hello world");
	}

	@Given("the user launch the homepage url and navigate to shop page")
	public void the_user_launch_the_homepage_url_and_navigate_to_shop_page() {
		System.out.println("Hello world");
	}

	@When("the user adds {string} item multiple times to cart")
	public void the_user_adds_item_multiple_times_to_cart(String string) {
		System.out.println("Hello world");
	}

	@Then("verify the number of items on cart")
	public void verify_the_number_of_items_on_cart() {
		System.out.println("Hello world");
	}

	@When("the user adds {string} item to cart")
	public void the_user_adds_item_to_cart(String string) {
		System.out.println("Hello world");
	}

	@When("the user clicks on cart menu")
	public void the_user_clicks_on_cart_menu() {
		System.out.println("Hello world");
	}

	@Then("the items are present in the shopping cart")
	public void the_items_are_present_in_the_shopping_cart() {
		System.out.println("Hello world");
	}

}
