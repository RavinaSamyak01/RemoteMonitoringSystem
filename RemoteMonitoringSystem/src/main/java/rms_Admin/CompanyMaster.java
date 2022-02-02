package rms_Admin;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import rms_BasePackage.BaseInit;

public class CompanyMaster extends BaseInit {

	@Test
	public void companyMaster() throws InterruptedException, IOException {
		System.out.println("-----Testing CompanyMaster-------");
		logs.info("-----Testing CompanyMaster-------");

		// --Common classes
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Actions act = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		waitForPageLoad();
		Thread.sleep(5000);

		// ----Clicked on Admin Menu
		highLight(isElementPresent("Admin_xpath"), driver);
		System.out.println("Name of the Menu is==" + isElementPresent("Admin_xpath").getText());
		WebElement Admin = isElementPresent("Admin_xpath");
		act.moveToElement(Admin).build().perform();
		System.out.println("Clicked on Admin menu");
		logs.info("Clicked on Admin menu");

		// -----Clicked on CompanyMaster
		highLight(isElementPresent("CompanyMaster_xpath"), driver);
		isElementPresent("CompanyMaster_xpath").click();
		System.out.println("Clicked on Company Master");
		logs.info("Clicked on Company Master");

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("companytable")));
		getScreenshot("CompanyMasterScreen_", "CompanyMaster", driver);

		// ----Company Table Details
		WebElement table = isElementPresent("CompHeader_id");
		List<WebElement> rows = table.findElements(By.tagName("th"));
		System.out.println("No of Columns in the table are=" + rows.size());
		logs.info("No of Columns in the table are=" + rows.size());
		for (int row = 0; row < rows.size(); row++) {
			System.out.println("Name of the Column is=" + rows.get(row).getText());
			logs.info("Name of the Column is=" + rows.get(row).getText());
		}
		List<WebElement> Cols = table.findElements(By.tagName("td"));
		System.out.println("No of values of Columns in the table are=" + Cols.size());
		logs.info("No of Columns in the table are=" + Cols.size());
		for (int col = 0; col < Cols.size(); col++) {
			System.out.println("Value of the Column is=" + Cols.get(col).getText());
			logs.info("Value of the Column is=" + Cols.get(col).getText());
		}

		// ----Add Site
		highLight(isElementPresent("AddSite_id"), driver);
		isElementPresent("AddSite_id").click();
		System.out.println("Clicked on Add Site button");
		logs.info("Clicked on Add Site button");

		// ----scroll down
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		getScreenshot("CMAddSite_", "CompanyMaster", driver);

		// --Enter SiteName
		highLight(isElementPresent("SiteName_id"), driver);
		isElementPresent("SiteName_id").sendKeys("AutomationTestingSite");
		System.out.println("Entered SiteName");
		logs.info("Entered SiteName");
		// --AUtoSave
		isElementPresent("SiteName_id").sendKeys(Keys.TAB);
		getScreenshot("CMAddSiteName_", "CompanyMaster", driver);

		// ---Add Panel
		highLight(isElementPresent("Add_xpath"), driver);
		isElementPresent("Add_xpath").click();
		System.out.println("Clicked on + icon for adding Panel");
		logs.info("Clicked on + icon for adding Panel");
		getScreenshot("CMAddPanel_", "CompanyMaster", driver);

		// --Entered PanelName
		highLight(isElementPresent("PanelName_id"), driver);
		isElementPresent("PanelName_id").sendKeys("AutomationTestingPanel");
		System.out.println("Entered PanelName");
		logs.info("Entered PanelName");
		// --AUtoSave
		isElementPresent("PanelName_id").sendKeys(Keys.TAB);
		getScreenshot("CMAddPanelName_", "CompanyMaster", driver);

		// --Add Device
		highLight(isElementPresent("AddDevice_xpath"), driver);
		isElementPresent("AddDevice_xpath").click();
		System.out.println("Clicked on + icon for adding Device");
		logs.info("Clicked on + icon for adding Device");
		getScreenshot("CMAddDevice_", "CompanyMaster", driver);

		// --Entered DeviceName
		highLight(isElementPresent("DeviceName_xpath"), driver);
		isElementPresent("DeviceName_xpath").sendKeys("AutomationTestingDevice");
		System.out.println("Entered DeviceName");
		logs.info("Entered DeviceName");
		// --AUtoSave
		isElementPresent("DeviceName_xpath").sendKeys(Keys.TAB);
		getScreenshot("CMAddDeviceName_", "CompanyMaster", driver);

		// ---CLicked on Configuration of Device
		highLight(isElementPresent("Configuration_id"), driver);
		isElementPresent("Configuration_id").click();
		System.out.println("Clicked on Configuration button for device");
		logs.info("Clicked on Configuration button for device");

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("mat-dialog-1")));
		getScreenshot("CMDeviceConfiguration_", "CompanyMaster", driver);

		// ---GSM Device Configuration
		highLight(isElementPresent("ConfigHeader1_xpath"), driver);
		System.out.println("Header Of the tab is==" + isElementPresent("ConfigHeader1_xpath").getText());
		logs.info("Header Of the tab is==" + isElementPresent("ConfigHeader1_xpath").getText());

		// --Select Device Type
		highLight(isElementPresent("DeviceType_id"), driver);
		isElementPresent("DeviceType_id").click();
		getScreenshot("DeviceTypeDrop_", "CompanyMaster", driver);
		isElementPresent("DeviceType_id").sendKeys(Keys.DOWN);
		isElementPresent("DeviceType_id").sendKeys(Keys.ENTER);
		System.out.println("Selected Device Type");
		logs.info("Selected Device Type");

		// --Maximum Slave
		highLight(isElementPresent("MaxSlave_id"), driver);
		isElementPresent("MaxSlave_id").sendKeys("5");
		System.out.println("Entered Maximum Slave");
		logs.info("Entered Maximum Slave");

		// --Select Baudrate
		highLight(isElementPresent("Baudrate_id"), driver);
		isElementPresent("Baudrate_id").click();
		getScreenshot("BaudrateDrop_", "CompanyMaster", driver);
		isElementPresent("Baudrate_id").sendKeys(Keys.DOWN);
		isElementPresent("Baudrate_id").sendKeys(Keys.ENTER);
		System.out.println("Selected Baudrate");
		logs.info("Selected Baudrate");

		// --Select Parity
		highLight(isElementPresent("Parity_id"), driver);
		isElementPresent("Parity_id").click();
		getScreenshot("ParityDrop_", "CompanyMaster", driver);
		isElementPresent("Parity_id").sendKeys(Keys.DOWN);
		isElementPresent("Parity_id").sendKeys(Keys.ENTER);
		System.out.println("Selected Parity");
		logs.info("Selected Parity");

		// --IntervalTimer(sec)
		highLight(isElementPresent("INtervalTimer_id"), driver);
		isElementPresent("INtervalTimer_id").sendKeys("5");
		System.out.println("Entered Interval Timer");
		logs.info("Entered Interval Timer");
		getScreenshot("GSMDConfigPart1_", "CompanyMaster", driver);

		// --scroll down to mQTT
		WebElement MQTT = isElementPresent("MQTTKID_id");
		js.executeScript("arguments[0].scrollIntoView();", MQTT);
		System.out.println("Scroll down to MQTT");
		logs.info("Scroll down to MQTT");

		// ---Select APN
		highLight(isElementPresent("APN_id"), driver);
		isElementPresent("APN_id").click();
		getScreenshot("APNDrop_", "CompanyMaster", driver);
		isElementPresent("APN_id").sendKeys(Keys.DOWN);
		isElementPresent("APN_id").sendKeys(Keys.ENTER);
		System.out.println("Selected APN");
		logs.info("Selected APN");

		// --HTTP URL
		highLight(isElementPresent("HTTPURL_id"), driver);
		isElementPresent("HTTPURL_id").sendKeys("https");
		System.out.println("Entered HTTP URL");
		logs.info("Entered HTTP URL");

		// --HTTP Port
		highLight(isElementPresent("HTTPPOrt_id"), driver);
		isElementPresent("HTTPPOrt_id").sendKeys("11");
		System.out.println("Entered HTTP Port");
		logs.info("Entered HTTP Port");

		// --MQTT KeyID
		highLight(isElementPresent("MQTTKID_id"), driver);
		isElementPresent("MQTTKID_id").sendKeys("SIPL1000000005000001");
		System.out.println("Entered MQTT KeyID");
		logs.info("Entered MQTT KeyID");
		getScreenshot("GSMDConfigPart2_", "CompanyMaster", driver);

		// --FTP Parameters Header
		highLight(isElementPresent("ConfigHeader2_xpath"), driver);
		System.out.println("Label Of the FTP Section is==" + isElementPresent("ConfigHeader2_xpath").getText());
		logs.info("Label Of the FTP Section is==" + isElementPresent("ConfigHeader2_xpath").getText());

		// --Scroll down to page
		js.executeScript("windows.scrollBy(0,document.body.scrollHeight");
		System.out.println("Scroll down to end");
		logs.info("Scroll down to end");

		// --FTP USerName
		highLight(isElementPresent("FUserName_id"), driver);
		isElementPresent("FUserName_id").sendKeys("Swetasdsf");
		System.out.println("Entered FTP UserName");
		logs.info("Entered FTP UserName");

		// --FTP Password
		highLight(isElementPresent("FPassword_id"), driver);
		isElementPresent("FPassword_id").sendKeys("Sjsipl45");
		System.out.println("Entered FTP Passowrd");
		logs.info("Entered FTP Password");

		// --FTP CSVFile
		highLight(isElementPresent("FFileName_id"), driver);
		isElementPresent("FFileName_id").sendKeys("test.txt");
		System.out.println("Entered FTP FileName");
		logs.info("Entered FTP FileName");
		getScreenshot("GSMDConfigPart3_", "CompanyMaster", driver);

	}
}
