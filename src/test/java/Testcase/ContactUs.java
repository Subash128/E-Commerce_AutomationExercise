package Testcase;

import org.testng.annotations.Test;

import BaseClass.Locators;
import BaseClass.SeleniumBase;

public class ContactUs extends SeleniumBase {

	@Test
	void Formfilling() {
		RegisterUser registerUser = new RegisterUser();
		registerUser.LauchBrowser();
		RegisterUser.Title();

		element(Locators.xpath, "//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[8]/a\r\n").click();

		String valid = element(Locators.xpath, "//h2[text()='Get In Touch']").getText();

		System.out.println(valid);

		element(Locators.xpath, "//input[@placeholder='Name']").sendKeys("dummy");

		element(Locators.xpath, "//input[@placeholder='Email']").sendKeys("Dummy123@gmail.com");

		element(Locators.xpath, "//input[@placeholder='Subject']").sendKeys("ad problems");

		element(Locators.xpath, "//textarea[@placeholder='Your Message Here']")
				.sendKeys("difficult to automate because of ads");

		element(Locators.xpath, "//input[@type='submit']").click();

		alertaccept(driver);

		String msg = element(Locators.xpath, "//*[@id=\"contact-page\"]/div[3]/div[1]/div/div[2]").getText();

		System.out.println(msg);

		element(Locators.xpath, "//*[@id=\"form-section\"]/a").click();
		
		System.out.println(getURL());

		//close();
	}

}
