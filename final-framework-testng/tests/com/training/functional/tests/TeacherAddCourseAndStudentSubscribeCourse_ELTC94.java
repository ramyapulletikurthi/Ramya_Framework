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
import com.training.pom.AdminSubscribeCourseandViewStudentReportPOM;
import com.training.pom.LoginPOM;
import com.training.pom.TeacherAddDescriptionPOM;
import com.training.pom.TeacherAddNewCoursePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

/*
 * Author  					: Ramya Krishna Pulletikurthi
 * Test Case ID				: ELTC_094
 * Test Case Description 	: To verify whether application allows teacher to create a course with objective description & topics, student to get register for the course take the test & admin to report 
 * PreCondition				: User should have launched the application
 */

public class TeacherAddCourseAndStudentSubscribeCourse_ELTC94 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private TeacherAddNewCoursePOM teacherAddNewCoursePOM;
	private TeacherAddDescriptionPOM teacherAddDescriptionPOM;
	private AdminSubscribeCourseandViewStudentReportPOM adminSubscribeCourseandViewStudentReportPOM;
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
		adminSubscribeCourseandViewStudentReportPOM = new AdminSubscribeCourseandViewStudentReportPOM(driver);
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

	@Test(priority = 3)
	public void teacherAddNewCourse() {
		// TeacherAddNewCoursePOM.clickHomePage();
		teacherAddNewCoursePOM.clickCreateCourse();
		teacherAddNewCoursePOM.sendCourseTitle("selenium1989");
		teacherAddNewCoursePOM.clickAdvanceSettings();
		teacherAddNewCoursePOM.selectCourseCategory("(PROJ) Projects");
		teacherAddNewCoursePOM.sendCourseCode("seleramyanandu19");
		teacherAddNewCoursePOM.selectCourseLanguage("English");
		teacherAddNewCoursePOM.clickCourseSubmit();
		teacherAddNewCoursePOM.clickIntroductionButton();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		teacherAddNewCoursePOM.sendIntroductionText("this is an selenium course");
		teacherAddNewCoursePOM.clickSaveIntroText();

		// Verification Of IntroductionText
		String expected = "this is an selenium course";
		String actual = teacherAddNewCoursePOM.verifyIntroText();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("IntroductionTextVerification_ELTC_094");

		// TeacherAddCourseDescription
		teacherAddDescriptionPOM.clickCourseDescription();
		teacherAddDescriptionPOM.clickDescription();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		teacherAddDescriptionPOM.sendCourseDescriptionTitle("Description:selenium course for beginners");
		teacherAddDescriptionPOM.sendCourseContentText("Description:selenium course for beginners");
		teacherAddDescriptionPOM.clickCourseDescriptionSubmit();

		// Verification Of DescriptionUpdateText
		String expected1 = "The description has been updated";
		String actual1 = teacherAddDescriptionPOM.verifyCourseDescriptionUpdateMessage();
		assertEquals(actual1, expected1);
		screenShot.captureScreenShot("DescriptionUpdateVerification_ELTC_094");

		// TeacherAddCourseObjectives
		teacherAddDescriptionPOM.clickCourseObjectives();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		teacherAddDescriptionPOM.sendCourseObjectivesTitle("Objective:selenium course for beginners");
		teacherAddDescriptionPOM.sendCourseObjectivesContentText("Objective:selenium course for beginners");
		teacherAddDescriptionPOM.clickCourseObjectivesSubmit();

		// Verification Of ObjectivesUpdateText
		String expected2 = "The description has been updated";
		String actual2 = teacherAddDescriptionPOM.verifyCourseTopicsUpdateMessage();
		assertEquals(actual2, expected2);
		screenShot.captureScreenShot("ObjectivesUpdateVerification_ELTC_094");

		// Teacher Add CourseTopics
		teacherAddDescriptionPOM.clickCourseTopics();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		teacherAddDescriptionPOM.sendCourseTopicsTitle("Topics:selenium course for beginners");
		teacherAddDescriptionPOM.sendCourseTopicsContentText("Topics:selenium course for beginners");
		teacherAddDescriptionPOM.clickCourseTopicsSubmit();

		// Verification of TopicsUpdateText
		String expected3 = "The description has been updated";
		String actual3 = teacherAddDescriptionPOM.verifyCourseTopicsUpdateMessage();
		assertEquals(actual3, expected3);
		screenShot.captureScreenShot("TopicsUpdateVerification_ELTC_094");

		// Logout of Application as Teacher
		teacherAddDescriptionPOM.clickdropDownforLogout();
		teacherAddDescriptionPOM.clickLogoutButton();
	}

	@Test(priority = 4)
	public void validLoginTestforStudent() {
		loginPOM.sendUserName("ramya1107");
		loginPOM.sendPassword("ramya@1989");
		loginPOM.clickLoginBtn();
	}

	@Test(priority = 5)
	public void studentSubscribeCourseandTakeAssessment() throws InterruptedException {
		String coursename = "selenium1989";
		adminSubscribeCourseandViewStudentReportPOM.clickAdminHomePage();
		adminSubscribeCourseandViewStudentReportPOM.clickCourseCatlog();
		adminSubscribeCourseandViewStudentReportPOM.searchCourseName(coursename);
		adminSubscribeCourseandViewStudentReportPOM.clickSearchCourseSubmit();
		String subscribecourse = adminSubscribeCourseandViewStudentReportPOM.subscribeCourse(coursename);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// verification of success message of subscription
		if (subscribecourse == "Subscribe") {
			String expected = "You have been registered to course: " + coursename;
			String actual = adminSubscribeCourseandViewStudentReportPOM.verifyCourseSubscriptionMessage();
			assertEquals(actual, expected);
			screenShot.captureScreenShot("SubscriptionSuccessMessage_ELTC_094");
		}

		// Logout of Application as Student
		teacherAddDescriptionPOM.clickdropDownforLogout();
		teacherAddDescriptionPOM.clickLogoutButton();
	}

	@Test(priority = 6)
	public void validLoginTestforAdmin() {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
	}

	@Test(priority = 7)
	public void adminSubscribeCourseandTakeStudentReport() throws InterruptedException {
		String coursename = "selenium1989";
		adminSubscribeCourseandViewStudentReportPOM.clickAdminHomePage();
		adminSubscribeCourseandViewStudentReportPOM.clickCourseCatlog();
		adminSubscribeCourseandViewStudentReportPOM.searchCourseName(coursename);
		adminSubscribeCourseandViewStudentReportPOM.clickSearchCourseSubmit();
		String subscribecourse = adminSubscribeCourseandViewStudentReportPOM.subscribeCourse(coursename);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// verification of success message of subscription
		if (subscribecourse == "Subscribe") {
			String expected = "You have been registered to course: " + coursename;
			String actual = adminSubscribeCourseandViewStudentReportPOM.verifyCourseSubscriptionMessage();
			assertEquals(actual, expected);
			screenShot.captureScreenShot("SubscriptionSuccessMessage_ELTC_094");
		}
		adminSubscribeCourseandViewStudentReportPOM.clickReportingIcon();
		adminSubscribeCourseandViewStudentReportPOM.clickFollowedStudents();
		adminSubscribeCourseandViewStudentReportPOM.sendStudentSearchName("Ramya");
		adminSubscribeCourseandViewStudentReportPOM.clickSearchButton();
		adminSubscribeCourseandViewStudentReportPOM.clickStudentDetailsIcon();
		adminSubscribeCourseandViewStudentReportPOM.clickCourseDetailsIcon(coursename);
		screenShot.captureScreenShot("CourseDetailsforAStudent_ELTC_094");
	}

	@Test(priority = 11)
	public void tearDown() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.quit();
	}
}
