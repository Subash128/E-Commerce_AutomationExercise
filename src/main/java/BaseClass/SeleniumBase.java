package BaseClass;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumBase implements SeleniumAPI {
	long timeOuts = 10; // 30
	Duration maxWaitTime = Duration.ofSeconds(timeOuts);

	public static WebDriver driver = null;
	WebDriverWait wait = null;

	public void setUp(String url) {
		ChromeOptions options = new ChromeOptions();
		//options.addExtensions(new File("Extention\\App.crx"));
		options.setBinary("D:\\AutomationTools\\chromedriver\\chrome-win64\\chrome.exe");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(timeOuts, TimeUnit.SECONDS);
		driver.get(url);
		wait = new WebDriverWait(driver, maxWaitTime);

	}

	public void setUp(Browser browserName, String url) {
		switch (browserName) {
		case CHROME:
			ChromeOptions options = new ChromeOptions();
			//options.addExtensions(new File("Extention\\App.crx"));

			options.setBinary("D:\\AutomationTools\\chromedriver\\chrome-win64\\chrome.exe");

			driver = new ChromeDriver(options);
			break;
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case EDGE:
			driver = new EdgeDriver();
			break;
		default:
			System.err.println("Driver is not defined");
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(timeOuts, TimeUnit.SECONDS);
		driver.get(url);
		wait = new WebDriverWait(driver, maxWaitTime);
	}

	public void close() {
		driver.close();
	}

	public void quit() {
		driver.quit();
	}

	public WebElement element(Locators type, String value) {
		try {
			switch (type) {
			case id:
				return driver.findElement(By.id(value));
			case name:
				return driver.findElement(By.name(value));
			case xpath:
				return driver.findElement(By.xpath(value));
			case link:
				return driver.findElement(By.linkText(value));
			case className:
				return driver.findElement(By.className(value));
			case css:
				return driver.findElement(By.cssSelector(value));
			default:
				break;
			}
		} catch (NoSuchElementException e) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
			System.err.println("Element not found => " + e.getMessage());
			String str ="no such element";
			
			if(str==e.getMessage()) {frames(driver);}
			throw new NoSuchElementException("Element not found");

		} catch (WebDriverException e) {
			System.err.println(e.getMessage());
			throw new WebDriverException("Some unknown webdriver error");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public void switchToWindow(int i) {
		Set<String> windowHandles = driver.getWindowHandles();
		ArrayList<String> list = new ArrayList<String>(windowHandles);
		driver.switchTo().window(list.get(i));
	}
	// TODO: function to check if the dropdown is selected ?

	public void selectValue(WebElement ele, String value) {
		WebElement element = isElementVisible(ele);
		new Select(element).selectByValue(value);
	}

	public void selectText(WebElement ele, String text) {
		WebElement element = isElementVisible(ele);
		new Select(element).selectByVisibleText(text);
	}

	public void selectIndex(WebElement ele, int position) {
		WebElement element = isElementVisible(ele);
		new Select(element).selectByIndex(position);
	}

	public void click(WebElement ele) {
		WebElement element = wait.withMessage("Element is not clickable")
				.until(ExpectedConditions.elementToBeClickable(ele));
		element.click();
	}

	public void type(WebElement ele, String testData) {
		try {
			WebElement element = isElementVisible(ele);
			element.clear();
			ele.sendKeys(testData);
		} catch (NullPointerException e) {
			System.out.println("Element might be null => " + e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private WebElement isElementVisible(WebElement ele) {
		WebElement element = wait.withMessage("Element is not visible").until(ExpectedConditions.visibilityOf(ele));
		return element;
	}

	public void type(WebElement ele, String testData, Keys keys) {
		WebElement element = isElementVisible(ele);
		element.clear();
		element.sendKeys(testData, keys);
	}

	public void appendText(WebElement ele, String testData) {
		WebElement element = isElementVisible(ele);
		element.sendKeys(testData);

	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getURL() {
		return driver.getCurrentUrl();
	}

	public boolean isDisplayed(WebElement ele) {
		return ele.isDisplayed();
	}

	public void alertdismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
		driver.switchTo().defaultContent();
	}

	public void alertaccept(WebDriver driver) {
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
	}

	public void frames(WebDriver driver) {
	
		 for (WebElement iframe : driver.findElements(By.tagName("iframe"))) {
	            try {
	                driver.switchTo().frame(iframe);
	                // Identify and interact with the close button or element within the iframe
	                WebElement closeButton = element(Locators.xpath,"//*[@id=\"dismiss-button\"]");
	                closeButton.click(); // Click on the close button
	                // Once closed, switch back to default content
	                driver.switchTo().defaultContent();
	            } catch (Exception e) {
	                // Handle exceptions if the close button is not found or cannot be interacted with
	                System.out.println("Failed to close the ad iframe: " + e.getMessage());
	            }
	        }

	}

}
