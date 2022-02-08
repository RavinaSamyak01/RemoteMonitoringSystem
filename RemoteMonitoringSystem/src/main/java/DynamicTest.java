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

public class DynamicTest extends BaseInit {

	public class CompanyMaster extends BaseInit {

		@Test(dataProvider = "getCompanyMasterData")
		public void companyMaster(String SiteName, String PanelName, String DeviceName, String MaxSlave,
				String Int_Timer, String HTTP_URL, String HTTP_Port, String MQTT_KeyID, String FTP_Username,
				String FTP_Password, String FTP_Filename, String TimeZone, String ClientName, String MQTTPort,
				String Username, String Password, String MeterName, String Description)
				throws InterruptedException, IOException {
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

			// ----Add Site
			highLight(isElementPresent("AddSite_id"), driver);
			isElementPresent("AddSite_id").click();
			System.out.println("Clicked on Add Site button");
			logs.info("Clicked on Add Site button");

			// ----scroll down
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			getScreenshot("CMAddSite_", "CompanyMaster", driver);

			// --Stored all the sites
			List<WebElement> sites = driver.findElements(By.xpath("//*[@formcontrolname=\"CompSiteName\"]"));
			System.out.println("Number of sites are==" + sites.size());
			logs.info("Number of sites are==" + sites.size());
			data.setData("CompanyMasterData", 1, 2, String.valueOf(sites.size()));
			// ---get the last site
			for (int count = sites.size() - 1; count < sites.size();) {
				System.out.println("ID of the last element is==" + sites.get(count).getAttribute("id"));
				logs.info("ID of the last element is==" + sites.get(count).getAttribute("id"));
				
				// --Enter value in new site
				highLight(sites.get(count), driver);
				sites.get(count).sendKeys(SiteName);
				sites.get(count).sendKeys(Keys.TAB);
				System.out.println("Entered SiteName");
				logs.info("Entered SiteName");

				//--Add panel
				
			}
		}
	}
}
