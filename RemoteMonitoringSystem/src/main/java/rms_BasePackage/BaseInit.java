package rms_BasePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseInit {

	public static Properties storage = null;
	public static WebDriver driver;
	public static Logger logs;
	public static ExtentTest test;
	public static ExtentReports report;

	@BeforeSuite
	public void startUp() throws Exception {
		startTest();
		if (driver == null) {
			// Initialize Logs
			logs = Logger.getLogger("devpinoyLogger");
			logs.info("initialization of the log is done");

			// Initialization and Load Properties File
			logs.info("initialization of the properties file");

			storage = new Properties();
			FileInputStream fi = new FileInputStream(
					".\\src\\main\\resources\\samyak_PropertiesData\\ObjectStorage.properties");
			storage.load(fi);
			logs.info("initialization of the properties file is done");

			// Launch the browser
			logs.info("Launching the browser");
			String browserkey = storage.getProperty("browser");
			if (browserkey.equalsIgnoreCase("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				logs.info("Firefox Browser is launched");

			}
			if (browserkey.equalsIgnoreCase("firefox Headless")) {

				FirefoxOptions options = new FirefoxOptions();
				options.setHeadless(true);

				// pass the options parameter in the Firefox driver declaration
				driver = new FirefoxDriver(options);
				logs.info("Firefox Browser is launched");

			}
			if (browserkey.equalsIgnoreCase("chrome headless")) {

				DesiredCapabilities capabilities = new DesiredCapabilities();
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("headless");
				options.addArguments("--incognito");
				options.addArguments("--test-type");
				options.addArguments("--no-proxy-server");
				options.addArguments("--proxy-bypass-list=*");
				options.addArguments("--disable-extensions");
				options.addArguments("--no-sandbox");
				options.addArguments("--headless");
				options.addArguments("window-size=1366x768");
				capabilities.setPlatform(Platform.ANY);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver(options);

			} else if (browserkey.equalsIgnoreCase("chrome")) {

				WebDriverManager.chromedriver().setup();
				ChromeOptions options1 = new ChromeOptions();
				options1.addArguments("--incognito");
				options1.addArguments("--test-type");
				options1.addArguments("--disable-extensions");
				options1.addArguments("window-size=1920,1080");
				driver = new ChromeDriver(options1);
				logs.info("Chrome Browser is launched");
				System.out.println("Chrome Browser is launched");
			} else {
				System.out.println("Browser is not defined");
				logs.info("Browser is not defined");
				System.out.println("Browser is not defined");
			}

			// define timeout
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			logs.info("timeout is defined");

			// delete the cookies
			driver.manage().deleteAllCookies();
			logs.info("Cookies are deleted");

			// Object of the ExcelFileReader Class
			/*
			 * data = new ExcelFileReader();
			 * logs.info("initialization of the excelfilereader Class is done");
			 */
		}

	}

	@BeforeTest
	public void login() throws InterruptedException {
		storage.getProperty("url");
		waitForPageLoad();
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@formcontrolname=\"email\"]")));
		highLight(isElementPresent("Email_xpath"), driver);
		isElementPresent("Email_xpath").sendKeys("Superadmin");
		highLight(isElementPresent("Next_xpath"), driver);
		isElementPresent("Next_xpath").click();
		highLight(isElementPresent("Password_xpath"), driver);
		isElementPresent("Password_xpath").sendKeys("Welcome@1234");
		highLight(isElementPresent("subbmit_xpath"), driver);
		isElementPresent("subbmit_xpath").click();
		logs.info("Login done");
		waitForPageLoad();

	}

	@AfterTest
	public void logOut() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"client-img\"]")));
		highLight(isElementPresent("logOut_xpath"), driver);
		isElementPresent("logOut_xpath").click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//dx-button[@aria-label=\"Logout\"]")));
		highLight(isElementPresent("logOutbtn_xpath"), driver);
		isElementPresent("logOutbtn_xpath").click();
		Thread.sleep(2000);

	}

	@AfterSuite
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(5000);

		// try {
		driver.close();

		System.out.println("Browser closed");
		logs.info("Browser closed");

		report.flush();

		// catch won't be executed
		/*
		 * catch (NullPointerException e) { System.out.println(e); }
		 */
		// executed regardless of exception occurred or not
		/*
		 * finally { driver.close();
		 * 
		 * System.out.println("Browser closed"); logs.info("Browser closed");
		 * 
		 * report.flush(); }
		 */
	}

	@BeforeMethod
	public void testMethodName(Method method) {

		String testName = method.getName();
		test = report.startTest(testName);

	}

	public static void startTest() {
		report = new ExtentReports("./ExtentReport/ExtentReportResults.html", true);
		// test = report.startTest();
	}

	public static void endTest() {
		report.endTest(test);
		report.flush();
	}

	public static String getFailScreenshot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			// test.log(LogStatus.FAIL, "Test Case Failed is " +
			// result.getThrowable().getMessage());
			test.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			// To capture screenshot path and store the path of the screenshot in the string
			// "screenshotPath"
			// We do pass the path captured by this mehtod in to the extent reports using
			// "logger.addScreenCapture" method.
			String screenshotPath = BaseInit.getFailScreenshot(driver, result.getName());
			// To add it in the extent report
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, "Test Case Pass is " + result.getName());

		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
	}

	public void waitForPageLoad() {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		});
	}

	public WebElement isElementPresent(String propkey) {

		try {

			if (propkey.contains("xpath")) {

				return driver.findElement(By.xpath(storage.getProperty(propkey)));

			} else if (propkey.contains("id")) {

				return driver.findElement(By.id(storage.getProperty(propkey)));

			} else if (propkey.contains("name")) {

				return driver.findElement(By.name(storage.getProperty(propkey)));

			} else if (propkey.contains("linkText")) {

				return driver.findElement(By.linkText(storage.getProperty(propkey)));

			} else if (propkey.contains("className")) {

				return driver.findElement(By.className(storage.getProperty(propkey)));

			}
			if (propkey.contains("cssSelector")) {

				return driver.findElement(By.cssSelector(storage.getProperty(propkey)));
			}

			else {

				System.out.println("propkey is not defined");

				logs.info("prop key is not defined");
			}

		} catch (Exception e) {

		}
		return null;

	}

	public static void highLight(WebElement element, WebDriver driver) {
		// for (int i = 0; i < 2; i++) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: black; border: 4px solid red;");
			Thread.sleep(500);
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// }

	}

	public void wait(String parameter) {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(parameter)));
	}

	public void action(WebElement parameter) {
		Actions act = new Actions(driver);
		act.moveToElement(parameter).click().perform();
	}

	public void javaScrExec(WebElement parameter) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", parameter);
	}

	public void getScreenshot(String imagename, String MName, WebDriver driver) throws IOException {

		try {

			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

			File scrFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

			String logFileName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());

			if (MName.equals("Airport")) {
				FileUtils.copyFile(scrFile, new File("./src/main/resources/screenshots/Airport/" + imagename
						+ logFileName + System.currentTimeMillis() + ".png"));

			} else {
				System.out.println("Module is not defined");
				logs.info("Module is not defined");
			}

			System.out.println(
					"Printing screen shot taken for className " + imagename + logFileName + System.currentTimeMillis());
			logs.info(
					"Printing screen shot taken for className " + imagename + logFileName + System.currentTimeMillis());

		} catch (Exception e) {
			System.out.println("Exception while taking screenshot " + e.getMessage());
			logs.info("Exception while taking screenshot " + e.getMessage());
		}

		/*
		 * @DataProvider public Object[][] gettestData() { return
		 * MyLibrary.getTestData(data, "Contact"); }
		 */

	}
}