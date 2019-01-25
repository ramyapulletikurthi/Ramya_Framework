package com.training.functional.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.TeacherAddNewCoursePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TeacherAddNewCourse_ELTC032 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private TeacherAddNewCoursePOM teacherAddNewCoursePOM;
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
		teacherAddNewCoursePOM = new TeacherAddNewCoursePOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	@Test(priority = 2)
	public void validLoginTest() {
		loginPOM.sendUserName("Ramya890");
		loginPOM.sendPassword("ramya@1989");
		loginPOM.clickLoginBtn();
	}

	@Test(priority = 3)
	public void teacherAddNewCourse() throws InterruptedException {
		// teacherAddNewCoursePOM.clickHomePage();
		teacherAddNewCoursePOM.clickCreateCourse();
		teacherAddNewCoursePOM.sendCourseTitle("selenium");
		teacherAddNewCoursePOM.clickAdvanceSettings();
		teacherAddNewCoursePOM.selectCourseCategory("(PROJ) Projects");
		teacherAddNewCoursePOM.sendCourseCode("seleramyakrishna");
		teacherAddNewCoursePOM.selectCourseLanguage("English");
		teacherAddNewCoursePOM.clickCourseSubmit();
		teacherAddNewCoursePOM.clickIntroductionButton();
		Thread.sleep(3000);
		teacherAddNewCoursePOM.sendIntroductionText("this is an selenium course");
		teacherAddNewCoursePOM.clickSaveIntroText();
	}
	
	@Test(priority = 4)
	public void verifyIntroductionText() {
		String expected = "this is an selenium course";
		String actual = teacherAddNewCoursePOM.verifyIntroText();
		assertEquals(actual,expected);
		screenShot.captureScreenShot("IntroductionTextVerification_ELTC_032");
	}

	@Test(priority = 5)
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
