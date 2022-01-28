package rms_Admin;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import rms_BasePackage.BaseInit;

public class DashboardConfig extends BaseInit {

	@Test
	public void dashBoardConfig() throws InterruptedException, IOException {
		System.out.println("-----Testing DashBoardConfig-------");
		logs.info("-----Testing DashBoardConfig-------");

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

		// -----Clicked on DashBoardConfig
		highLight(isElementPresent("DashBConfig_xpath"), driver);
		isElementPresent("DashBConfig_xpath").click();
		System.out.println("Clicked on DashBoardConfig");
		logs.info("Clicked on DashBoardConfig");

		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class, 'dashconfig-main')]")));
		getScreenshot("DashBoardConfig_", "DashBoardConfig", driver);

		// ----Title of the dashboard
		highLight(isElementPresent("DashBTitle_xpath"), driver);
		System.out.println("Title is==" + isElementPresent("DashBTitle_xpath"));
		logs.info("Title is==" + isElementPresent("DashBTitle_xpath"));

	}
}
