package Testcase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.Browser;
import BaseClass.Locators;
import BaseClass.SeleniumBase;

public class Login_with_invalid_password extends SeleniumBase {
	public static Properties property = new Properties();

	@BeforeClass
	void LauchBrowser() {

		String filepath = System.getProperty("user.dir") + "\\src\\main\\RequriedValue.properties";
		try {
			FileInputStream file = new FileInputStream(filepath);
			property.load(file);

		} catch (IOException e) {
			System.out.println("Propertyfile Not Found");
		}
		// setUp("url");
		setUp(Browser.CHROME, property.getProperty("url"));
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

	@Test
	void LoginPage() {
		element(Locators.xpath, "//a[contains(.,'Signup / Login')]").click();

		String Validate = element(Locators.xpath, "//*[@id='form']/div/div/div[1]/div/h2").getText();
		if (Validate.equalsIgnoreCase("Login to your account")) {
			System.out.println(Validate + "" + " is visible");
		} else {
			System.out.println("Not visible");
		}

		type(element(Locators.xpath, "//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]"),
				property.getProperty("email"));

		type(element(Locators.xpath, "//input[@type='password']"), "wrong-password");

		element(Locators.xpath, "//button[text()='Login']").click();
		
		String isvisible = element(Locators.xpath, "//*[@id=\"form\"]/div/div/div[1]/div/form/p").getText();
         
		System.out.println(isvisible);
		
		close();

	}
}
