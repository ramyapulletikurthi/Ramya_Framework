package com.training.functional.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AdminCreateCoursePOM;
import com.training.pom.LoginPOM;
import com.training.pom.TeacherAddNewCoursePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AdminCreateCourse_ELTC033 {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private AdminCreateCoursePOM adminCreateCoursePOM;
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
		adminCreateCoursePOM = new AdminCreateCoursePOM(driver);
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
	public void adminCreateCourse() throws InterruptedException {
		adminCreateCoursePOM.clickAdminTab();
		adminCreateCoursePOM.clickCreateCourse();
		adminCreateCoursePOM.sendCourseTitle("TestingRamya");
		adminCreateCoursePOM.sendCourseCode("testing");
		adminCreateCoursePOM.selectCourseTeacher("manzoor mehadi");
		adminCreateCoursePOM.selectCourseCategory("(PROJ) Projects");
		adminCreateCoursePOM.selectCourseLanguage("English");
		adminCreateCoursePOM.clickCourseSubmit();
		Thread.sleep(3000);
		adminCreateCoursePOM.sendCourseSearchCode();
		adminCreateCoursePOM.clickSearchSimpleSubmit();
	}
	
	@Test(priority = 4)
	public void verifyCourseTitle() {
		String expected = "TestingRamya";
		String actual = adminCreateCoursePOM.findCourseTitle();
		assertEquals(actual,expected);
		screenShot.captureScreenShot("CourseTitleAdded_ELTC_033");
	}

	@Test(priority = 5)
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
