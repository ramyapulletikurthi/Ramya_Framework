package com.training.functional.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AdminCreateClassPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AdminCreateClass_ELTC034 {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private AdminCreateClassPOM adminCreateClassPOM;
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
		adminCreateClassPOM = new AdminCreateClassPOM(driver);
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
	public void adminCreateClass() {
		adminCreateClassPOM.clickAdminTab();
		adminCreateClassPOM.clickClassesLink();
		adminCreateClassPOM.clickAddClassesLink();
		adminCreateClassPOM.sendClassName("demobyramyapulleti");
		adminCreateClassPOM.sendClassDescription("demobyramyapulleti");
		adminCreateClassPOM.selectClassGroupPermission("Open");
		adminCreateClassPOM.clickSubmitClassLink();
	}

	@Test(priority = 4)
	public void verificationOfSuccessMessage() throws InterruptedException {
		String expected = "Item added";
		String actual = adminCreateClassPOM.verifySuccessMessage();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("ClassAddedMessage_ELTC_034");
		Thread.sleep(3000);
		adminCreateClassPOM.selectMaximumFilterValue("All");
		String expected1 = "demobyramyapulleti";
		String actual1 = adminCreateClassPOM.verifyClassValue();
		assertEquals(actual1,expected1);
		screenShot.captureScreenShot("ClassValueVerification_ELTC_034");
	}

	@Test(priority = 5)
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
