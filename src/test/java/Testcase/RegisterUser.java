package Testcase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import BaseClass.Browser;
import BaseClass.Locators;
import BaseClass.SeleniumBase;

public class RegisterUser extends SeleniumBase {
	public static Properties property = new Properties();

	@BeforeClass
	public void LauchBrowser() {

		String filepath = System.getProperty("user.dir") + "\\src\\main\\RequriedValue.properties";
		try {
			FileInputStream file = new FileInputStream(filepath);
			property.load(file);

		} catch (IOException e) {
			System.out.println("Propertyfile Not Found");
		}

		setUp(Browser.CHROME, property.getProperty("url"));
		switchToWindow(0);
	}

	@Test
	public static void Title() {

		try {
			String Title = driver.getTitle();
			System.out.println(Title);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 1)
	void Singup() throws InterruptedException {
		element(Locators.xpath, "//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a").click();

		String Validate = element(Locators.xpath, "//*[@id=\"form\"]/div/div/div[3]/div/h2").getText();
		if (Validate.equalsIgnoreCase("New User Signup!")) {
			System.out.println(Validate + "" + " is visible");
		} else {
			System.out.println("Not visible");
		}

		type(element(Locators.xpath, "//input[@data-qa='signup-name']"), property.getProperty("user"));

		type(element(Locators.xpath, "//input[@data-qa='signup-email']"), property.getProperty("email"));

		element(Locators.xpath, "//*[@id=\"form\"]/div/div/div[3]/div/form/button").click();

	}

	@Test(priority = 2)
	private void accountInfo() {
		element(Locators.xpath, "//*[@id=\"id_gender1\"]").click();

		type(element(Locators.id, "password"), property.getProperty("password"));

		element(Locators.xpath, "//*[@id=\"days\"]/option[23]").click();

		element(Locators.xpath, "//*[@id=\"months\"]/option[7]").click();

		element(Locators.xpath, "//*[@id=\"years\"]/option[20]").click();

		element(Locators.id, "newsletter").click();

		element(Locators.xpath, "//*[@id='optin']").click();

		element(Locators.id, "first_name").sendKeys("null");

		element(Locators.id, "last_name").sendKeys("null");

		element(Locators.id, "address1").sendKeys("null");

		element(Locators.xpath, "//*[@id=\"country\"]/option[1]").click();

		element(Locators.id, "state").sendKeys("null");

		element(Locators.id, "city").sendKeys("null");

		element(Locators.id, "zipcode").sendKeys("null");

		element(Locators.id, "mobile_number").sendKeys("null");

		element(Locators.xpath, "//*[@id=\"form\"]/div/div/div/div[1]/form/button").click();

		String accCreated = element(Locators.xpath, "//*[@id=\"form\"]/div/div/div/h2/b").getText();
		System.out.println(accCreated);

		element(Locators.xpath, "//*[@id=\"form\"]/div/div/div/div/a").click();
		
		
	}

	@Test(priority = 3)
	void LoginPage() {

		String Validate = element(Locators.xpath, "//*[@id='form']/div/div/div[1]/div/h2").getText();
		if (Validate.equalsIgnoreCase("Login to your account")) {
			System.out.println(Validate + "" + " is visible");
		} else {
			System.out.println("Not visible");
		}

		type(element(Locators.xpath, "//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]"),
				property.getProperty("email"));

		type(element(Locators.xpath, "//input[@type='password']"), property.getProperty("password"));

		element(Locators.xpath, "//button[text()='Login']").click();

		String UserName = element(Locators.xpath, "//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a/b").getText();

		System.out.println(UserName);

	}

	@Test
	public void deleteacc() {

		element(Locators.xpath, "//*[@id='header']/div/div/div/div[2]/div/ul/li[5]/a").click();

		String accdelText = element(Locators.xpath, "//*[@id='form']/div/div/div/h2/b").getText();

		System.out.println(accdelText);

		element(Locators.xpath, "//*[@id=\"form\"]/div/div/div/div/a").click();

		close();
	}

}
