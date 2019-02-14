package com.training.functional.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.TeacherAddDescriptionPOM;
import com.training.pom.TeacherAddNewCoursePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

/*
 * Author  					: Ramya Krishna Pulletikurthi
 * Test Case ID				: ELTC_061
 * Test Case Description 	: To verify whether application allows teacher to create a course, add description, objective & Topics 
 * PreCondition				: 1. User should have launched the application
							  2. User should get logged in as Teacher
 */
public class TeacherAddDescription_ELTC061 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private TeacherAddNewCoursePOM teacherAddNewCoursePOM;
	private TeacherAddDescriptionPOM teacherAddDescriptionPOM;
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
		teacherAddDescriptionPOM = new TeacherAddDescriptionPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	// Logged into application as Teacher
	@Test(priority = 2)
	public void validLoginTest() {
		loginPOM.sendUserName("ramya89");
		loginPOM.sendPassword("ramya@1989");
		loginPOM.clickLoginBtn();
	}

	// Teacher adding course
	@Test(priority = 3)
	public void teacherAddNewCourse() {
		// teacherAddNewCoursePOM.clickHomePage();
		teacherAddNewCoursePOM.clickCreateCourse();
		teacherAddNewCoursePOM.sendCourseTitle("selenium345");
		teacherAddNewCoursePOM.clickAdvanceSettings();
		teacherAddNewCoursePOM.selectCourseCategory("(PROJ) Projects");
		teacherAddNewCoursePOM.sendCourseCode("seleramyanandu23");
		teacherAddNewCoursePOM.selectCourseLanguage("English");
		teacherAddNewCoursePOM.clickCourseSubmit();
		teacherAddNewCoursePOM.clickIntroductionButton();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		teacherAddNewCoursePOM.sendIntroductionText("this is an selenium course");
		teacherAddNewCoursePOM.clickSaveIntroText();
	}

	// Verification of Course added message
	@Test(priority = 4)
	public void verifyIntroductionText() {
		String expected = "this is an selenium course";
		String actual = teacherAddNewCoursePOM.verifyIntroText();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("IntroductionTextVerification_ELTC_061");
	}

	// Adding course description
	@Test(priority = 5)
	public void teacherAddCourseDescription() throws InterruptedException {
		teacherAddDescriptionPOM.clickCourseDescription();
		teacherAddDescriptionPOM.clickDescription();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		teacherAddDescriptionPOM.sendCourseDescriptionTitle("selenium course for beginners");
		teacherAddDescriptionPOM.sendCourseContentText("selenium course for beginners");
		teacherAddDescriptionPOM.clickCourseDescriptionSubmit();
	}

	// Verification of course description added message
	@Test(priority = 6)
	public void verifyDescriptionUpdateText() {
		String expected = "The description has been updated";
		String actual = teacherAddDescriptionPOM.verifyCourseDescriptionUpdateMessage();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("DescriptionUpdateVerification_ELTC_061");
	}

	// Add Course objective
	@Test(priority = 7)
	public void teacherAddCourseObjectives() throws InterruptedException {
		teacherAddDescriptionPOM.clickCourseObjectives();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		teacherAddDescriptionPOM.sendCourseObjectivesTitle("selenium course for beginners");
		teacherAddDescriptionPOM.sendCourseObjectivesContentText("selenium course for beginners");
		teacherAddDescriptionPOM.clickCourseObjectivesSubmit();
	}

	// Verification of course objective added message
	@Test(priority = 8)
	public void verifyObjectivesUpdateText() {
		String expected = "The description has been updated";
		String actual = teacherAddDescriptionPOM.verifyCourseTopicsUpdateMessage();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("ObjectivesUpdateVerification_ELTC_061");
	}

	// Adding of course topics
	@Test(priority = 9)
	public void teacherAddCourseTopics() throws InterruptedException {
		teacherAddDescriptionPOM.clickCourseTopics();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		teacherAddDescriptionPOM.sendCourseTopicsTitle("selenium course for beginners");
		teacherAddDescriptionPOM.sendCourseTopicsContentText("selenium course for beginners");
		teacherAddDescriptionPOM.clickCourseTopicsSubmit();
	}

	//// Verification of course topics added message
	@Test(priority = 10)
	public void verifyTopicsUpdateText() {
		String expected = "The description has been updated";
		String actual = teacherAddDescriptionPOM.verifyCourseTopicsUpdateMessage();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("TopicsUpdateVerification_ELTC_061");
	}

	@Test(priority = 11)
	public void tearDown() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.quit();
	}
}
