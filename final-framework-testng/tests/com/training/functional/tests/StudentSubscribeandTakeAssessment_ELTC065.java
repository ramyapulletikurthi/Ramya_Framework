package com.training.functional.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.StudentSubscribeandTakeAssessmentPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class StudentSubscribeandTakeAssessment_ELTC065 {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private StudentSubscribeandTakeAssessmentPOM studentSubscribeandTakeAssessmentPOM;
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
		studentSubscribeandTakeAssessmentPOM = new StudentSubscribeandTakeAssessmentPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	@Test(priority = 2)
	public void validLoginTest() {
		loginPOM.sendUserName("ramya1107");
		loginPOM.sendPassword("ramya@1989");
		loginPOM.clickLoginBtn();
	}

	@Test(priority = 3)
	public void studentSubscribeCourseandTakeAssessment() throws InterruptedException {
		String coursename = "selenium123";
		studentSubscribeandTakeAssessmentPOM.clickCourseCatlog();
		studentSubscribeandTakeAssessmentPOM.searchCourseName("selenium");
		studentSubscribeandTakeAssessmentPOM.clickSearchCourseSubmit();
		String subscribecourse = studentSubscribeandTakeAssessmentPOM.subscribeCourse(coursename);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//verification of success message of subscription
		if(subscribecourse == "Subscribe") {
		String expected = "You have been registered to course: "+ coursename;
		String actual = studentSubscribeandTakeAssessmentPOM.verifyCourseSubscriptionMessage();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("SubscriptionSuccessMessage_ELTC_0651");
		}
		//Click on My Courses tab
		studentSubscribeandTakeAssessmentPOM.clickMyCourses();
		//Open Course
		studentSubscribeandTakeAssessmentPOM.clickOpenCourse();
		//Open assessments
		studentSubscribeandTakeAssessmentPOM.clickAssessmentsIcon();
		//Click on Test Name
		studentSubscribeandTakeAssessmentPOM.clickTestName();
		//Start Test
		studentSubscribeandTakeAssessmentPOM.clickStartTest();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Answering first test question
		studentSubscribeandTakeAssessmentPOM.clickAnswerChoiceOnce();
		studentSubscribeandTakeAssessmentPOM.clickNextQuestion();
		//Answering second test question
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		studentSubscribeandTakeAssessmentPOM.clickAnswerChoiceSecond();
		studentSubscribeandTakeAssessmentPOM.clickTestEndTest();
		//Verification of test submission and taking screenshot of results
		String expected1 = "Saved.";
		String actual1 = studentSubscribeandTakeAssessmentPOM.verifyTestSubmittedMessage();
		assertEquals(actual1, expected1);
		screenShot.captureScreenShot("TestSubmittedMessage_ELTC_65");
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
