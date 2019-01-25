package com.training.functional.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AdminSubscribeClassToCoursePOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AdminSubscribeClassToCourse_ELTC035 {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private AdminSubscribeClassToCoursePOM adminSubscribeClassToCoursePOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@Test(priority = 1)
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver);
		adminSubscribeClassToCoursePOM = new AdminSubscribeClassToCoursePOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	@Test(priority = 2)
	public void validLoginTest() {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
	}

	@Test(priority = 3)
	public void adminSubscribeClassToCourse() throws InterruptedException {
		adminSubscribeClassToCoursePOM.clickAdminTab();
		adminSubscribeClassToCoursePOM.clickClassesLink();
		Thread.sleep(3000);
		adminSubscribeClassToCoursePOM.selectMaximumFilterValue("All");
		Thread.sleep(3000);
		adminSubscribeClassToCoursePOM.clickSubscribeClassToCourse();
		adminSubscribeClassToCoursePOM.selectFirstLetterofCourse("S");
		adminSubscribeClassToCoursePOM.selectSelectedCourse("Selenium (BL)");
		adminSubscribeClassToCoursePOM.clickForwardButton();
		adminSubscribeClassToCoursePOM.clickSubscribeSubmitButton();
		Thread.sleep(3000);
		adminSubscribeClassToCoursePOM.selectMaximumFilterValue("All");
	}

	@Test(priority = 4)
	public void verifyCourcesCount() {
		String expected = "1";
		String actual = adminSubscribeClassToCoursePOM.verifyCourcesCount();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("CourseCountVerification_ELTC_035");
	}

	@Test(priority = 5)
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
