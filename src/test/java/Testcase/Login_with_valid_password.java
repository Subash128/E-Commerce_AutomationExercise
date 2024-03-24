package Testcase;

import java.util.Properties;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import BaseClass.Locators;
import BaseClass.SeleniumBase;

public class Login_with_valid_password extends SeleniumBase {
	public static Properties property = new Properties();
	RegisterUser registerUser = new RegisterUser();

	@Test
	void LoginPage() {

		registerUser.LauchBrowser();
		Login_with_valid_password.property = RegisterUser.property;
		RegisterUser.Title();

		element(Locators.xpath, "//a[contains(.,'Signup / Login')]").click();

		String Validate = element(Locators.xpath, "//*[@id='form']/div/div/div[1]/div/h2").getText();
		if (Validate.equalsIgnoreCase("Login to your account")) {
			System.out.println(Validate + "" + " is visible");
		} else {
			System.out.println("Not visible");
		}

		element(Locators.xpath, "//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]")
				.sendKeys(property.getProperty("email"));

		element(Locators.xpath, "//*[@id=\"form\"]/div/div/div[1]/div/form/input[3]")
				.sendKeys(property.getProperty("password"));

		element(Locators.xpath, "//button[text()='Login']").click();

		String UserName = element(Locators.xpath, "//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a/b").getText();

		System.out.println(UserName);

	}

	@AfterClass
	void Teardown() {

		registerUser.deleteacc();
		close();
	}

}
