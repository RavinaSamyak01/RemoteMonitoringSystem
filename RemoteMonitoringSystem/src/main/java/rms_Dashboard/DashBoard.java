package rms_Dashboard;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import rms_BasePackage.BaseInit;

@Test
public class DashBoard extends BaseInit {

	public void dashboard() throws IOException, InterruptedException {
		System.out.println("-----Testing DashBoard-------");
		logs.info("-----Testing DashBoard-------");

		// --Common classes
		WebDriverWait wait = new WebDriverWait(driver, 30);

		waitForPageLoad();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("searchCompanyForm")));
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"banner banner-container cursor-pointer\"]")));
		getScreenshot("Dashboard_", "DashBoard", driver);

		// --Name of the Dashboard
		highLight(isElementPresent("DashBName_id"), driver);
		System.out.println("Name of the DashBoard is==" + isElementPresent("DashBName_id").getText());
		logs.info("Name of the DashBoard is==" + isElementPresent("DashBName_id").getText());

		// ---No of Meter in the Dashboard
		WebElement DashBoardDiv = isElementPresent("DashItem_xpath");
		List<WebElement> DashItems = DashBoardDiv
				.findElements(By.xpath("//*[@class=\"banner banner-container cursor-pointer\"]"));
		System.out.println("No of Meter in the Dashboard are== " + DashItems.size());
		logs.info("No of Meter in the Dashboard are== " + DashItems.size());

		// --If Meter is exist
		if(DashItems.size()>0) {
			for (int count = 0;count< DashItems.size(); count++) {
				// --Name of the meter
				highLight(isElementPresent("DMeterName_xpath"), driver);
				System.out.println("Name of the meter is==" + isElementPresent("DMeterName_xpath").getText());
				logs.info("Name of the meter is==" + isElementPresent("DMeterName_xpath").getText());

				// Reading of the meter
				highLight(isElementPresent("DMeterReading_xpath"), driver);
				System.out.println("Name of the meter is==" + isElementPresent("DMeterReading_xpath").getText());
				logs.info("Name of the meter is==" + isElementPresent("DMeterReading_xpath").getText());

				// Click on the meter
				highLight(isElementPresent("DMeterReading_xpath"), driver);
				isElementPresent("DMeterReading_xpath").click();
				System.out.println("Clicked on the meter");
				logs.info("Clicked on the meter");

				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("Rightsection-id")));
				getScreenshot("InstrumentChart_", "DashBoard", driver);
				
				//Instrument Chart
				
				//---Heading of the page
				highLight(isElementPresent("ICHeading_xpath"), driver);
				System.out.println("Heading of the page is==" + isElementPresent("ICHeading_xpath").getText());
				logs.info("Heading of the page is==" + isElementPresent("ICHeading_xpath").getText());

				//-----Meter Name
				highLight(isElementPresent("ICMeterName_xpath"), driver);
				System.out.println("Name of the meter is==" + isElementPresent("ICMeterName_xpath").getText());
				logs.info("Name of the meter is==" + isElementPresent("ICMeterName_xpath").getText());

				

			}
		}else {
			System.out.println("There is no meter on dashboard");
			logs.info("There is no meter on dashboard");
		}
		

	}
}
