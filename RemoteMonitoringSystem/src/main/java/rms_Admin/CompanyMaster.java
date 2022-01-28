package rms_Admin;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
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
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		for (WebElement row : rows) {
			List<WebElement> td = row.findElements(By.tagName("td"));
			// -Getting Columns Name
			String ColName = row.getText();
			System.out.println("Name of the column is==" + ColName);
			// ---Getting columns Values
			if (td.size() > 0) {
				for (WebElement col : td)
					System.out.println("Value of the column is==" + col.getText());

			}

		}
		
		//Add Site
		highLight(isElementPresent("AddSite_id"), driver);
		isElementPresent("AddSite_id").click();
		System.out.println("Clicked on Add Site button");
		logs.info("Clicked on Add Site button");
		
		
		
		

	}
}
