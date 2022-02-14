package rms_Admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rms_BasePackage.BaseInit;
import rms_Utility.MyLibrary;

public class CompanyMaster extends BaseInit {

	@Test(dataProvider = "getCompanyMasterData")
	public void companyMaster(String SiteName, String PanelName, String DeviceName, String MaxSlave, String Int_Timer,
			String HTTP_URL, String HTTP_Port, String MQTT_KeyID, String FTP_Username, String FTP_Password,
			String FTP_Filename, String TimeZone, String ClientName, String MQTTPort, String Username, String Password,
			String MeterName, String Description, String SlaveID, String MaxRegister, String RegName, String RegNumber,
			String RegUnit, String RegThresold) throws InterruptedException, IOException {
		System.out.println("-----Testing CompanyMaster-------");
		logs.info("-----Testing CompanyMaster-------");

		// --Common classes
		WebDriverWait wait = new WebDriverWait(driver, 50);
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
			data.setData("CompanyMasterData", row + 1, 0, rows.get(row).getText());
			System.out.println("Write Column name in the Excel");
			logs.info("Write Column name in the Excel");
			System.out.println("Name of the Column is=" + rows.get(row).getText());
			logs.info("Name of the Column is=" + rows.get(row).getText());
		}
		List<WebElement> Cols = table.findElements(By.tagName("td"));
		System.out.println("No of values of Columns in the table are=" + Cols.size());
		logs.info("No of Columns in the table are=" + Cols.size());
		for (int col = 0; col < Cols.size(); col++) {
			data.setData("CompanyMasterData", col + 1, 1, Cols.get(col).getText());
			System.out.println("Value of the Column is=" + Cols.get(col).getText());
			logs.info("Value of the Column is=" + Cols.get(col).getText());
		}

		// ----Dynamically handle the new add site

		// --Upload image
		String imagePath = System.getProperty("user.dir") + "\\src\\main\\resources\\rms_TestData\\QA_Logo.jpg";
		WebElement upload = isElementPresent("UploadImg_id");
		highLight(upload, driver);
		upload.click();
		System.out.println("Clicked on Upload button");
		logs.info("Clicked on Upload button");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"Uploadsection\"]")));

		// --select file
		WebElement SelectFile = isElementPresent("SelectFile_xpath");
		highLight(SelectFile, driver);
		SelectFile.sendKeys(imagePath);
		System.out.println("Send Image path");
		logs.info("Send Image path");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"Uploadsection\"]//button")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"Uploadsection\"]//button")));

		// ----Upload dialogue Details
		WebElement UploadDiv = isElementPresent("UploadDiv_xpath");
		rows = UploadDiv.findElements(By.tagName("th"));
		System.out.println("No of Columns in the table are=" + rows.size());
		logs.info("No of Columns in the table are=" + rows.size());
		for (int row = 0; row < rows.size(); row++) {
			data.setData("CompanyMasterData", row + 1, 12, rows.get(row).getText());
			System.out.println("Write Column name in the Excel");
			logs.info("Write Column name in the Excel");
			System.out.println("Name of the Column is=" + rows.get(row).getText());
			logs.info("Name of the Column is=" + rows.get(row).getText());
		}
		Cols = UploadDiv.findElements(By.tagName("td"));
		System.out.println("No of values of Columns in the table are=" + Cols.size());
		logs.info("No of Columns in the table are=" + Cols.size());
		for (int col = 0; col < Cols.size(); col++) {
			data.setData("CompanyMasterData", col + 1, 13, Cols.get(col).getText());
			System.out.println("Value of the Column is=" + Cols.get(col).getText());
			logs.info("Value of the Column is=" + Cols.get(col).getText());
		}
		// --Clicked on Upload button
		WebElement UploadBTN = isElementPresent("UploadBTN_xpath");
		UploadBTN.click();
		System.out.println("Clicked on Upload button");
		logs.info("Clicked on Upload button");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'sucessmsg')]")));
		String ActualUploadMsg = isElementPresent("RegSuccessMsg_xpath").getText();
		String ExpectedUploadMsg = "Upload successfully";
		Assert.assertEquals(ActualUploadMsg, ExpectedUploadMsg);

		// --Close button
		isElementPresent("Close_xpath").click();
		System.out.println("Clicked on Close button");
		logs.info("Clicked on Close button");

		// ----Add Site
		highLight(isElementPresent("AddSite_id"), driver);
		isElementPresent("AddSite_id").click();
		System.out.println("Clicked on Add Site button");
		logs.info("Clicked on Add Site button");

		// ----scroll down
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		getScreenshot("CMAddSite_", "CompanyMaster", driver);

		// --Stored all the sites
		WebElement SiteTable = isElementPresent("SiteTable_xpath");
		List<WebElement> sites = SiteTable.findElements(By.xpath("//*[@formcontrolname=\"CompSiteName\"]"));
		System.out.println("Number of sites are==" + sites.size());
		logs.info("Number of sites are==" + sites.size());
		data.setData("CompanyMasterData", 1, 2, String.valueOf(sites.size()));
		// ---get the last site
		for (int count = sites.size() - 1; count < sites.size();) {
			System.out.println("ID of the last element is==" + sites.get(count).getAttribute("id"));

			// --Enter value in new site
			highLight(sites.get(count), driver);
			sites.get(count).sendKeys(SiteName);
			sites.get(count).sendKeys(Keys.TAB);
			System.out.println("Entered SiteName");
			logs.info("Entered SiteName");

			// --Add Panel
			List<WebElement> AddIcons = driver.findElements(
					By.xpath("//td[contains(@class, 'td-main3')]//img[@src=\"/assets/images/add-icon.svg\"]"));
			System.out.println("Number of Add Icons for sites are==" + AddIcons.size());
			logs.info("Number of Add Icons for sites are==" + AddIcons.size());

			// ---get the last Add Icon of the site
			for (int AddPanel = AddIcons.size() - 1; AddPanel < AddIcons.size();) {
				System.out.println("ID of the last element is==" + AddIcons.get(AddPanel).getAttribute("id"));

				// --Click on the AddPanel
				highLight(AddIcons.get(AddPanel), driver);
				AddIcons.get(AddPanel).click();
				System.out.println("Clicked on Add Panel");
				logs.info("Clicked on Add Panel");
				js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				getScreenshot("CMAddPanel_", "CompanyMaster", driver);

				// --Stored all the panels
				List<WebElement> SitePanels = SiteTable.findElements(By.xpath("//*[@formcontrolname=\"PanelName\"]"));
				System.out.println("Number of Panels are==" + SitePanels.size());
				logs.info("Number of Panels are==" + SitePanels.size());
				data.setData("CompanyMasterData", 1, 3, String.valueOf(SitePanels.size()));

				// ---get the last Panel
				for (int Panels = SitePanels.size() - 1; Panels < SitePanels.size();) {
					System.out.println("ID of the last element is==" + SitePanels.get(Panels).getAttribute("id"));

					// --Enter value in new Panel
					highLight(SitePanels.get(Panels), driver);
					SitePanels.get(Panels).sendKeys(PanelName);
					SitePanels.get(Panels).sendKeys(Keys.TAB);
					System.out.println("Entered Panel Name");
					logs.info("Entered Panel Name");

					// --Add Device
					List<WebElement> AddDevice = driver
							.findElements(By.xpath("//a[contains(@id,'btnadminaddsiteAdd')]"));
					System.out.println("Number of Add Icons for are==" + AddDevice.size());
					logs.info("Number of Add Icons  are==" + AddDevice.size());

					// ---get the last Add Icon of the Panel
					for (int Device = AddDevice.size() - 1; Device < AddDevice.size();) {
						System.out.println("ID of the last element is==" + AddDevice.get(Device).getAttribute("id"));

						// --Click on the AddDevice
						highLight(AddDevice.get(Device), driver);
						AddDevice.get(Device).click();
						System.out.println("Clicked on Add Device");
						logs.info("Clicked on Add Device");
						js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
						getScreenshot("CMAddDevice_", "CompanyMaster", driver);

						// --Stored all the Devices
						List<WebElement> SiteDevices = SiteTable
								.findElements(By.xpath("//*[@formcontrolname=\"DeviceName\"]"));
						System.out.println("Number of Devices are==" + SiteDevices.size());
						logs.info("Number of Devices are==" + SiteDevices.size());
						data.setData("CompanyMasterData", 1, 4, String.valueOf(SiteDevices.size()));

						// ---get the last Device
						for (int Devices = SiteDevices.size() - 1; Devices < SiteDevices.size();) {
							System.out.println(
									"ID of the last element is==" + SiteDevices.get(Devices).getAttribute("id"));

							// --Enter value in new Device
							highLight(SiteDevices.get(Devices), driver);
							SiteDevices.get(Devices).sendKeys(DeviceName);
							SiteDevices.get(Devices).sendKeys(Keys.TAB);
							System.out.println("Entered Device Name");
							logs.info("Entered Device Name");

							// --Stored all the Configurations Buttons
							List<WebElement> DeviceConfigButtons = SiteTable
									.findElements(By.xpath("//*[@class=\"config\"]"));
							System.out.println("Number of Config buttons are==" + DeviceConfigButtons.size());
							logs.info("Number of Config buttons are==" + DeviceConfigButtons.size());

							// ---get the last Config button
							for (int DConfigBtn = DeviceConfigButtons.size() - 1; DConfigBtn < DeviceConfigButtons
									.size();) {
								System.out.println("ID of the last element is=="
										+ DeviceConfigButtons.get(DConfigBtn).getAttribute("id"));

								// --Clicked on last Config button
								highLight(DeviceConfigButtons.get(DConfigBtn), driver);
								DeviceConfigButtons.get(DConfigBtn).click();
								System.out.println("Clicked on Configuration button for device");
								logs.info("Clicked on Configuration button for device");

								wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
										By.xpath("//*[contains(@class, 'mat-dialog-container')]")));
								getScreenshot("CMDeviceConfiguration_", "CompanyMaster", driver);

								// ---GSM Device Configuration
								highLight(isElementPresent("ConfigHeader1_xpath"), driver);
								System.out.println(
										"Header Of the tab is==" + isElementPresent("ConfigHeader1_xpath").getText());
								logs.info("Header Of the tab is==" + isElementPresent("ConfigHeader1_xpath").getText());

								// --Clicked on save without enter/select any values
								highLight(isElementPresent("ConfigSave_id"), driver);
								isElementPresent("ConfigSave_id").click();
								System.out.println("Clicked on Save button");
								logs.info("Clicked on Save button");

								// --Checked all the validations of GSM Device Configuration
								List<WebElement> ErrorMsgs = driver
										.findElements(By.xpath("//*[contains(@class, 'error_msg')]"));
								System.out.println("Number of Error Messages are==" + ErrorMsgs.size());
								logs.info("Number of Error Messages are==" + ErrorMsgs.size());
								int ErrMsg;
								// ---get the last Config button
								for (ErrMsg = 0; ErrMsg < ErrorMsgs.size(); ErrMsg++) {
									String actualErrorMsg = ErrorMsgs.get(ErrMsg).getText();
									data.setData("CompanyMasterData", ErrMsg + 1, 5, actualErrorMsg);
									System.out.println("Error Message is==" + actualErrorMsg);
									logs.info("Error Message is==" + actualErrorMsg);

								}

								// --Clicked on Cancel button
								highLight(isElementPresent("ConfigCancel_id"), driver);
								isElementPresent("ConfigCancel_id").click();
								System.out.println("Clicked on Cancel button");
								logs.info("Clicked on Cancel button");

								// --Clicked on last Config button
								highLight(DeviceConfigButtons.get(DConfigBtn), driver);
								DeviceConfigButtons.get(DConfigBtn).click();
								System.out.println("Clicked on Configuration button for device");
								logs.info("Clicked on Configuration button for device");

								wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
										By.xpath("//*[contains(@class, 'mat-dialog-container')]")));
								getScreenshot("CMDeviceConfiguration_", "CompanyMaster", driver);

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
								isElementPresent("MaxSlave_id").sendKeys(MaxSlave);
								System.out.println("Entered Maximum Slave=1000");
								logs.info("Entered Maximum Slave=1000");

								WebElement ErrorMsg = isElementPresent("ConfiErrorMsg_xpath");
								boolean ErrMessage = ErrorMsg.isDisplayed();
								Assert.assertEquals(ErrMessage, true);
								if (ErrorMsg.isDisplayed()) {
									String ExpectedMaxSlavVMsg = "Slave Should Be Greater Than 0 And Less Than 16";
									String ActualMaxSlavvMsg = ErrorMsg.getText();
									System.out.println("Error Message is=" + ActualMaxSlavvMsg);
									logs.info("Error Message is=" + ActualMaxSlavvMsg);
									Assert.assertEquals(ActualMaxSlavvMsg, ExpectedMaxSlavVMsg);
								} else {
									System.out.println("Error message is not displayed");
									logs.info("Error message is not displayed");
								}
								isElementPresent("MaxSlave_id").clear();
								isElementPresent("MaxSlave_id").sendKeys("5");
								System.out.println("Entered Maximum Slave=5");
								logs.info("Entered Maximum Slave=5");

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
								isElementPresent("INtervalTimer_id").sendKeys(Int_Timer);
								System.out.println("Entered Interval Timer=5");
								logs.info("Entered Interval Timer=5");
								getScreenshot("GSMDConfigPart1_", "CompanyMaster", driver);

								ErrorMsg = isElementPresent("ConfiErrorMsg_xpath");
								ErrMessage = ErrorMsg.isDisplayed();
								Assert.assertEquals(ErrMessage, true);
								if (ErrorMsg.isDisplayed()) {
									String ExpectedMaxSlavVMsg = "Interval Time Should Be Greater Than 30 And Less Than 65535";
									String ActualMaxSlavvMsg = ErrorMsg.getText();
									System.out.println("Error Message is=" + ActualMaxSlavvMsg);
									logs.info("Error Message is=" + ActualMaxSlavvMsg);
									Assert.assertEquals(ActualMaxSlavvMsg, ExpectedMaxSlavVMsg);
								} else {
									System.out.println("Error message is not displayed");
									logs.info("Error message is not displayed");
								}
								isElementPresent("INtervalTimer_id").clear();
								isElementPresent("INtervalTimer_id").sendKeys("31");
								System.out.println("Entered Interval Timer=31");
								logs.info("Entered Interval Timer=31");

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
								isElementPresent("HTTPURL_id").sendKeys(HTTP_URL);
								System.out.println("Entered HTTP URL");
								logs.info("Entered HTTP URL");

								// --HTTP Port
								highLight(isElementPresent("HTTPPOrt_id"), driver);
								isElementPresent("HTTPPOrt_id").sendKeys(HTTP_Port);
								System.out.println("Entered HTTP Port");
								logs.info("Entered HTTP Port");

								// --MQTT KeyID
								highLight(isElementPresent("MQTTKID_id"), driver);
								isElementPresent("MQTTKID_id").sendKeys(MQTT_KeyID);
								System.out.println("Entered MQTT KeyID");
								logs.info("Entered MQTT KeyID");
								getScreenshot("GSMDConfigPart2_", "CompanyMaster", driver);

								// --FTP Parameters Header
								highLight(isElementPresent("ConfigHeader2_xpath"), driver);
								System.out.println("Label Of the FTP Section is=="
										+ isElementPresent("ConfigHeader2_xpath").getText());
								logs.info("Label Of the FTP Section is=="
										+ isElementPresent("ConfigHeader2_xpath").getText());

								// --Scroll down to page
								WebElement SDPassword = isElementPresent("FPassword_id");
								js.executeScript("arguments[0].scrollIntoView();", SDPassword);
								System.out.println("Scroll down to end");
								logs.info("Scroll down to end");

								// --FTP USerName
								highLight(isElementPresent("FUserName_id"), driver);
								isElementPresent("FUserName_id").sendKeys(FTP_Username);
								System.out.println("Entered FTP UserName");
								logs.info("Entered FTP UserName");

								// --FTP Password
								highLight(isElementPresent("FPassword_id"), driver);
								isElementPresent("FPassword_id").sendKeys(FTP_Password);
								System.out.println("Entered FTP Passowrd");
								logs.info("Entered FTP Password");

								// --FTP CSVFile (Code issue-so need to correct after resolve)
								highLight(isElementPresent("FFileName_id"), driver);
								isElementPresent("FFileName_id").sendKeys(FTP_Filename);
								System.out.println("Entered FTP FileName");
								logs.info("Entered FTP FileName");
								getScreenshot("GSMDConfigPart3_", "CompanyMaster", driver);

								// --Clicked on save
								highLight(isElementPresent("ConfigSave_id"), driver);
								isElementPresent("ConfigSave_id").click();
								System.out.println("Clicked on Save button");
								logs.info("Clicked on Save button");

								// --Clicked on last Config button
								highLight(DeviceConfigButtons.get(DConfigBtn), driver);
								DeviceConfigButtons.get(DConfigBtn).click();
								System.out.println("Clicked on Configuration button for device");
								logs.info("Clicked on Configuration button for device");

								wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
										By.xpath("//*[contains(@class, 'mat-dialog-container')]")));
								getScreenshot("CMDeviceConfiguration_", "CompanyMaster", driver);

								// ---Clicked on RTC tab
								highLight(isElementPresent("RTCTab_xpath"), driver);
								isElementPresent("RTCTab_xpath").click();
								System.out.println("Clicked on RTC Tab");
								logs.info("Clicked on RTC Tab");
								getScreenshot("ConfigRTCTab_", "CompanyMaster", driver);

								// --Clicked on save without enter/select any values
								Thread.sleep(2000);
								wait.until(ExpectedConditions
										.visibilityOfElementLocated(By.xpath("//*[@id=\"btngsmconfigSave\"]")));
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("//*[@id=\"btngsmconfigSave\"]")));
								highLight(isElementPresent("ConfigSave_id"), driver);
								isElementPresent("ConfigSave_id").click();
								System.out.println("Clicked on Save button");
								logs.info("Clicked on Save button");

								// --Checked all the validations of GSM Device Configuration
								ErrorMsgs = driver.findElements(By.xpath("//*[contains(@class, 'error_msg')]"));
								System.out.println("Number of Error Messages are==" + ErrorMsgs.size());
								logs.info("Number of Error Messages are==" + ErrorMsgs.size());

								// ---get the last Config button
								for (ErrMsg = 0; ErrMsg < ErrorMsgs.size(); ErrMsg++) {
									String actualErrorMsg = ErrorMsgs.get(ErrMsg).getText();
									data.setData("CompanyMasterData", ErrMsg + 1, 6, actualErrorMsg);
									System.out.println("Error Message is==" + actualErrorMsg);
									logs.info("Error Message is==" + actualErrorMsg);

								}

								// ---Enter TimeZone
								highLight(isElementPresent("RTCTimeZone_id"), driver);
								isElementPresent("RTCTimeZone_id").sendKeys(TimeZone);
								System.out.println("Entered TimeZone");
								logs.info("Entered TimeZone");

								// ---Choose Date&Time
								highLight(isElementPresent("RTCDateTime_xpath"), driver);
								DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
								Calendar cal = Calendar.getInstance();
								cal.setTime(new Date());
								cal.add(Calendar.DATE, 10);
								String newDate = dateFormat.format(cal.getTime());
								isElementPresent("RTCDateTime_xpath").sendKeys(newDate);
								System.out.println("Selected Date,10 days priorior from current Date");
								logs.info("Selected Date,10 days priorior from current Date");

								// --Get the date
								highLight(isElementPresent("RTCGet_id"), driver);
								isElementPresent("RTCGet_id").click();
								System.out.println("Clicked on Get button");
								logs.info("Clicked on Get button");

								getScreenshot("ConfigRTCTabwithData_", "CompanyMaster", driver);

								// --Clicked on save
								highLight(isElementPresent("ConfigSave_id"), driver);
								isElementPresent("ConfigSave_id").click();
								System.out.println("Clicked on Save button");
								logs.info("Clicked on Save button");

								// --Clicked on last Config button
								highLight(DeviceConfigButtons.get(DConfigBtn), driver);
								DeviceConfigButtons.get(DConfigBtn).click();
								System.out.println("Clicked on Configuration button for device");
								logs.info("Clicked on Configuration button for device");

								wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
										By.xpath("//*[contains(@class, 'mat-dialog-container')]")));
								getScreenshot("CMDeviceConfiguration_", "CompanyMaster", driver);

								// ---Clicked on Settings tab
								highLight(isElementPresent("SettingsTab_xpath"), driver);
								isElementPresent("SettingsTab_xpath").click();
								System.out.println("Clicked on Settings Tab");
								logs.info("Clicked on Settings Tab");
								getScreenshot("ConfigSettingsTab_", "CompanyMaster", driver);

								// --Clicked on save without enter/select any values
								Thread.sleep(2000);
								wait.until(ExpectedConditions
										.visibilityOfElementLocated(By.xpath("//*[@id=\"btngsmconfigSave\"]")));
								wait.until(ExpectedConditions
										.elementToBeClickable(By.xpath("//*[@id=\"btngsmconfigSave\"]")));
								highLight(isElementPresent("ConfigSave_id"), driver);
								isElementPresent("ConfigSave_id").click();
								System.out.println("Clicked on Save button");
								logs.info("Clicked on Save button");

								// --Checked all the validations of GSM Device Configuration
								ErrorMsgs = driver.findElements(By.xpath("//*[contains(@class, 'error_msg')]"));
								System.out.println("Number of Error Messages are==" + ErrorMsgs.size());
								logs.info("Number of Error Messages are==" + ErrorMsgs.size());

								// ---get the last Config button
								for (ErrMsg = 0; ErrMsg < ErrorMsgs.size(); ErrMsg++) {
									String actualErrorMsg = ErrorMsgs.get(ErrMsg).getText();
									data.setData("CompanyMasterData", ErrMsg + 1, 7, actualErrorMsg);
									System.out.println("Error Message is==" + actualErrorMsg);
									logs.info("Error Message is==" + actualErrorMsg);

								}

								// ---Select Protocol
								highLight(isElementPresent("SetProtocol_id"), driver);
								isElementPresent("SetProtocol_id").click();
								getScreenshot("ProtocolDrop_", "CompanyMaster", driver);
								isElementPresent("SetProtocol_id").sendKeys(Keys.DOWN);
								isElementPresent("SetProtocol_id").sendKeys(Keys.DOWN);
								isElementPresent("SetProtocol_id").sendKeys(Keys.ENTER);
								System.out.println("Selected Protocol");
								logs.info("Selected Protocol");

								// --Client Name
								highLight(isElementPresent("SetClientName_id"), driver);
								isElementPresent("SetClientName_id").sendKeys(ClientName);
								System.out.println("Entered Client Name");
								logs.info("Entered Client Name");

								// --MQTT Port
								highLight(isElementPresent("SetMQTTPort_id"), driver);
								isElementPresent("SetMQTTPort_id").sendKeys(MQTTPort);
								System.out.println("Entered MQTT Port");
								logs.info("Entered MQTT Port");

								// --USerName
								highLight(isElementPresent("SetUserName_id"), driver);
								isElementPresent("SetUserName_id").sendKeys(Username);
								System.out.println("Entered UserName");
								logs.info("Entered UserName");

								// --Password
								highLight(isElementPresent("SetPassword_id"), driver);
								isElementPresent("SetPassword_id").sendKeys(Password);
								System.out.println("Entered Passowrd");
								logs.info("Entered Password");

								getScreenshot("SettingsTabwithData_", "CompanyMaster", driver);

								// --Clicked on save
								highLight(isElementPresent("ConfigSave_id"), driver);
								isElementPresent("ConfigSave_id").click();
								System.out.println("Clicked on Save button");
								logs.info("Clicked on Save button");

								// --Add Meter
								List<WebElement> AddMeter = driver
										.findElements(By.xpath("//a[contains(@id, 'btnadminaddsiteAddicon')]/img"));
								System.out.println("Number of Add Icons for Device are==" + AddMeter.size());
								logs.info("Number of Add Icons for Device are==" + AddMeter.size());

								// ---get the last Add Icon of the Device
								for (int Meter = AddMeter.size() - 1; Meter < AddMeter.size();) {
									System.out.println(
											"ID of the last element is==" + AddMeter.get(Meter).getAttribute("id"));

									// --Click on the AddDevice
									highLight(AddMeter.get(Meter), driver);
									AddMeter.get(Meter).click();
									System.out.println("Clicked on Add Meter");
									logs.info("Clicked on Add Meter");
									js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
									getScreenshot("CMAddMeter_", "CompanyMaster", driver);

									// --Stored all the EnergyMeter Rows
									List<WebElement> EnMeterRows = driver
											.findElements(By.xpath("//*[@class=\"tble-categories\"]"));
									System.out.println("Number of EnergyMeters Rows are==" + EnMeterRows.size());
									logs.info("Number of EnergyMeters Rows are==" + EnMeterRows.size());
									data.setData("CompanyMasterData", 1, 9, String.valueOf(EnMeterRows.size()));

									// ---get the last EnergyMeter Row
									for (int EMRow = EnMeterRows.size() - 1; EMRow < EnMeterRows.size();) {
										System.out.println("value of EMRow is==" + EMRow);

										js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

										// --Meter index
										highLight(EnMeterRows.get(EMRow).findElement(
												By.xpath("//td[contains(@class,'padding-top-15')]")), driver);
										String MeterIndex = driver
												.findElement(
														By.xpath("(//td[contains(@class,'padding-top-15')])[EMRow]"))
												.getText();
										System.out.println("Index of the meter is==" + MeterIndex);
										logs.info("Index of the meter is==" + MeterIndex);

										// --Enter MeterName
										WebElement MName = EnMeterRows.get(EMRow).findElement(
												By.xpath("//input[contains(@formcontrolname,'EnergymeterID')]"));
										highLight(MName, driver);
										MName.sendKeys(MeterName);
										System.out.println("Entered value in MeterName");
										logs.info("Entered value in MeterName");

										// --Select DeviceType
										WebElement DType = EnMeterRows.get(EMRow).findElement(
												By.xpath("//mat-select[contains(@formcontrolname,'DeviceTypeId')]"));
										highLight(DType, driver);
										DType.click();
										System.out.println("Clicked on Device Type dropdown");
										logs.info("Clicked on Device Type dropdown");
										getScreenshot("CMDeviceTypeDPValue_", "CompanyMaster", driver);
										DType.sendKeys(Keys.DOWN);
										DType.sendKeys(Keys.ENTER);
										System.out.println("Selected values from devicetype dropdown");
										logs.info("Selected values from devicetype dropdown");

										// --Enter ValidTo Date
										WebElement VToDate = EnMeterRows.get(EMRow).findElement(
												By.xpath("//input[contains(@formcontrolname,'ValidToDate')]"));
										highLight(VToDate, driver);
										dateFormat = new SimpleDateFormat("MM/dd/yyyy");
										cal = Calendar.getInstance();
										cal.setTime(new Date());
										cal.add(Calendar.DATE, 10);
										newDate = dateFormat.format(cal.getTime());
										VToDate.sendKeys(newDate);
										System.out.println("Selected Date,10 days priorior from current Date");
										logs.info("Selected Date,10 days priorior from current Date");

										// --Enter Description
										WebElement MDescription = EnMeterRows.get(EMRow).findElement(
												By.xpath("//input[contains(@formcontrolname,'EnergymeterDesc')]"));
										highLight(MDescription, driver);
										MDescription.sendKeys(Description);
										System.out.println("Entered value in Description");
										logs.info("Entered value in Description");

										// --Is Display Checkbox
										WebElement IsDisplay = EnMeterRows.get(EMRow).findElement(
												By.xpath("//input[contains(@formcontrolname,'IsDisplay')]"));
										highLight(IsDisplay, driver);
										IsDisplay.click();
										boolean IsDisplayed = IsDisplay.isSelected();
										Assert.assertEquals(IsDisplayed, true);
										System.out.println("Selected Is Displayed checkbox");
										logs.info("Selected Is Displayed checkbox");

										// --Clicked on Configuration
										WebElement ConfigBTN = EnMeterRows.get(EMRow)
												.findElement(By.xpath("//td[@class=\"config\"]"));
										highLight(ConfigBTN, driver);
										ConfigBTN.click();
										System.out.println("Clicked on Config button");
										logs.info("Clicked on Config button");

										// --waiting for dialoguebox
										wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
												By.xpath("//*[@id=\"mat-dialog-0\"]")));

										// --Header of the dialoguebox is
										WebElement MDIaloguebox = isElementPresent("MDialogueH_xpath");
										highLight(MDIaloguebox, driver);
										System.out.println("Name of the dialoguebox is==" + MDIaloguebox.getText());
										logs.info("Name of the dialoguebox is==" + MDIaloguebox.getText());

										// --SlaveID//Max Register error message
										highLight(isElementPresent("MDSlaveID_id"), driver);
										isElementPresent("MDSlaveID_id").clear();
										highLight(isElementPresent("MDMaxRegister_id"), driver);
										isElementPresent("MDMaxRegister_id").clear();
										for (ErrMsg = 0; ErrMsg < ErrorMsgs.size(); ErrMsg++) {
											String actualErrorMsg = ErrorMsgs.get(ErrMsg).getText();
											data.setData("CompanyMasterData", ErrMsg + 1, 10, actualErrorMsg);
											System.out.println("Error Message is==" + actualErrorMsg);
											logs.info("Error Message is==" + actualErrorMsg);

										}

										// --SlaveID
										highLight(isElementPresent("MDSlaveID_id"), driver);
										isElementPresent("MDSlaveID_id").sendKeys(SlaveID);
										System.out.println("Entered value in SlaveID");
										logs.info("Entered value in SlaveID");

										// --Maximum Register
										highLight(isElementPresent("MDMaxRegister_id"), driver);
										isElementPresent("MDMaxRegister_id").sendKeys(MaxRegister);
										System.out.println("Entered value in Maximum register=" + MaxRegister);
										logs.info("Entered value in Maximum register=" + MaxRegister);

										ErrorMsg = isElementPresent("ConfiErrorMsg_xpath");
										ErrMessage = ErrorMsg.isDisplayed();
										Assert.assertEquals(ErrMessage, true);
										if (ErrorMsg.isDisplayed()) {
											String ExpectedMaxSlavVMsg = "Slave Should Be Greater Than 0 And Less Than 16";
											String ActualMaxSlavvMsg = ErrorMsg.getText();
											System.out.println("Error Message is=" + ActualMaxSlavvMsg);
											logs.info("Error Message is=" + ActualMaxSlavvMsg);
											Assert.assertEquals(ActualMaxSlavvMsg, ExpectedMaxSlavVMsg);
										} else {
											System.out.println("Error message is not displayed");
											logs.info("Error message is not displayed");
										}
										isElementPresent("MDMaxRegister_id").clear();
										isElementPresent("MDMaxRegister_id").sendKeys("5");
										System.out.println("Entered value in Maximum register=10");
										logs.info("Entered value in Maximum register=10");

										// --Show Button
										highLight(isElementPresent("MDShowBTN_id"), driver);
										isElementPresent("MDShowBTN_id").click();
										System.out.println("Clicked on Show button");
										logs.info("Clicked on Show button");

										// ----Register Table Details
										WebElement Registertable = isElementPresent("RegTable_xpath");
										List<WebElement> Regrows = Registertable.findElements(By.tagName("th"));
										System.out.println("No of Columns in the table are=" + Regrows.size());
										logs.info("No of Columns in the table are=" + Regrows.size());
										for (int row = 0; row < Regrows.size(); row++) {
											data.setData("CompanyMasterData", row + 1, 11, Regrows.get(row).getText());
											System.out.println("Write Column name in the Excel");
											logs.info("Write Column name in the Excel");
											System.out.println("Name of the Column is=" + Regrows.get(row).getText());
											logs.info("Name of the Column is=" + Regrows.get(row).getText());
										}
										// --No.of registers
										Registertable = isElementPresent("RegTabBody_xpath");
										Regrows = Registertable.findElements(By.tagName("tr"));
										int ExpectedRegisters = 5;
										int Registers = Regrows.size();
										System.out.println("No of registers in the table are=" + Registers);
										logs.info("No of registers in the table are=" + Registers);
										Assert.assertEquals(Registers, ExpectedRegisters);

										for (int Reg = 0; Reg < Regrows.size(); Reg++) {

											// --Register Name
											WebElement RegisterName = Regrows.get(Reg).findElement(By.id("rname"));
											highLight(RegisterName, driver);
											RegisterName.sendKeys(RegName);
											System.out.println("Entered Register Name");
											logs.info("Entered Register Name");

											// --Register Number
											WebElement RegisterNumber = Regrows.get(Reg).findElement(By.id("rnumber"));
											highLight(RegisterNumber, driver);
											RegisterNumber.sendKeys(RegNumber);
											System.out.println("Entered Register Number");
											logs.info("Entered Register Number");

											// --Register Unit
											WebElement RegisterUnit = Regrows.get(Reg).findElement(By.id("unit"));
											highLight(RegisterUnit, driver);
											RegisterUnit.sendKeys(RegUnit);
											System.out.println("Entered Register Unit");
											logs.info("Entered Register Unit");

											// --Register DataType
											WebElement RegDataType = Regrows.get(Reg).findElement(By.id("datatype"));
											highLight(RegDataType, driver);
											RegDataType.sendKeys(Keys.DOWN);
											RegDataType.sendKeys(Keys.ENTER);
											System.out.println("Selected Register datatype");
											logs.info("Selected Register datatype");

											// --Register Function Code
											WebElement RegFunCode = Regrows.get(Reg).findElement(By.id("fcode"));
											highLight(RegFunCode, driver);
											RegFunCode.sendKeys(Keys.DOWN);
											RegFunCode.sendKeys(Keys.ENTER);
											System.out.println("Selected Register Function Code");
											logs.info("Selected Register Function Code");

											// --Register Decimal Point
											WebElement RegDecPoint = Regrows.get(Reg).findElement(By.id("dpoint"));
											highLight(RegDecPoint, driver);
											RegDecPoint.sendKeys(Keys.DOWN);
											RegDecPoint.sendKeys(Keys.ENTER);
											System.out.println("Selected Register Decimal Point");
											logs.info("Selected Register Decimal Point");

											// --Register Action
											WebElement RegAction = Regrows.get(Reg)
													.findElement(By.id("ReadWriteAction"));
											highLight(RegAction, driver);
											RegAction.sendKeys(Keys.DOWN);
											RegAction.sendKeys(Keys.DOWN);
											RegAction.sendKeys(Keys.DOWN);
											RegAction.sendKeys(Keys.ENTER);
											System.out.println("Selected Register Action");
											logs.info("Selected Register Action");

											// --Is Display Checkbox
											WebElement RegIsDisplay = Regrows.get(Reg)
													.findElement(By.xpath("//*[@formcontrolname=\"IsDisplayGraph\"]"));
											highLight(RegIsDisplay, driver);
											RegIsDisplay.click();
											boolean RegIsDisplayed = RegIsDisplay.isSelected();
											Assert.assertEquals(RegIsDisplayed, true);
											System.out.println("Selected Is Displayed checkbox");
											logs.info("Selected Is Displayed checkbox");

											// --Register Thresold
											WebElement RegisterThresold = Regrows.get(Reg)
													.findElement(By.id("rnumber1"));
											highLight(RegisterThresold, driver);
											RegisterThresold.sendKeys(RegThresold);
											System.out.println("Entered Register Thresold value");
											logs.info("Entered Register Thresold value");

										}

										break;
									}

									break;

								}

								break;
							}

							break;
						}

						break;
					}

					break;
				}

				break;
			}
			break;
		}

	}

	@DataProvider
	public Object[][] getCompanyMasterData() {
		return MyLibrary.getTestData(data, "CompanyMaster");
	}
}
