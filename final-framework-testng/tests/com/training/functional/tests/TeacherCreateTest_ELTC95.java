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

import com.training.dataproviders.RegistrationDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.TeacherCreateTestPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

/*
 * Author  					: Ramya Krishna Pulletikurthi
 * Test Case ID				: ELTC_095
 * Test Case Description 	: To verify whether application allows teacher to author test with multiple questions 
 * PreCondition				: 1. User should have launched the application
							  2. User should get logged in as Teacher
							  3. User should have created course
 */

public class TeacherCreateTest_ELTC95 {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private TeacherCreateTestPOM teacherCreateTestPOM;
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
		teacherCreateTestPOM = new TeacherCreateTestPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	// Logging to the application as Teacher
	@Test(priority = 2)
	public void validLoginTest() {
		loginPOM.sendUserName("ramya89");
		loginPOM.sendPassword("ramya@1989");
		loginPOM.clickLoginBtn();
	}

	// Creating a Test for the selected course
	@Test(priority = 3)
	public void teacherAddNewTest() throws InterruptedException {
		teacherCreateTestPOM.clickCourseName("selenium");
		teacherCreateTestPOM.clickTestsLink();
		teacherCreateTestPOM.clickCreateNewTest();
		teacherCreateTestPOM.sendTestName("daily test");
		teacherCreateTestPOM.clickAdvancedSettings();
		teacherCreateTestPOM.sendCourseTestContext("quiz");
		teacherCreateTestPOM.clickTestFeedback();
		teacherCreateTestPOM.clickEnableStartTime();
		teacherCreateTestPOM.clickTestStartTimeText();
		teacherCreateTestPOM.clickSetStartTime();
		teacherCreateTestPOM.clickSubmitStartTime();
		teacherCreateTestPOM.sendTestPassPercentage("50");
		teacherCreateTestPOM.clickTestSubmitExercise();
	}

	// Verification of Test created message
	@Test(priority = 4)
	public void verifyTestCreatedMessage() {
		String expected = "Exercise added";
		String actual = teacherCreateTestPOM.verifyTestCreatedMessage();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("TestCreatedMessage_ELTC_095");
	}

	// Adding multiple choice question from the excel sheet to the test created
	@Test(priority = 5, dataProvider = "TestData_ELTC095", dataProviderClass = RegistrationDataProviders.class)
	public void teacherAddQuestionToTest(String Question, String FirstChoice, String SecondChoice, String ThirdChoice,
			String FourthChoice) {
		// adding first question with options
		teacherCreateTestPOM.clickTestMultipleChoice();
		teacherCreateTestPOM.sendTestQuestionName(Question);
		teacherCreateTestPOM.sendQuestionFirstOption(FirstChoice);
		teacherCreateTestPOM.sendQuestionSecondOption(SecondChoice);
		teacherCreateTestPOM.sendQuestionThirdOption(ThirdChoice);
		teacherCreateTestPOM.sendQuestionFourthOption(FourthChoice);
		teacherCreateTestPOM.clickTestFirstRadioButton();
		teacherCreateTestPOM.clickTestSubmitQuestion();
	}

	// Verification of Question Added Message
	@Test(priority = 6)
	public void verifyTestQuestionAddedMessage() {
		String expected = "4 questions, for a total score (all questions) of 0.";
		String actual = teacherCreateTestPOM.verifyTestQuestionAddedMessage();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("TestFirstQuestionAddedMessage_ELTC_95");
	}

	// Teacher Preview the created test with multiple choices question
	@Test(priority = 7)
	public void teacherPreviewAddTest() throws InterruptedException {
		// preview the test
		teacherCreateTestPOM.clickTestPreview();
		teacherCreateTestPOM.clickStartTest();
		// answering first question
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		teacherCreateTestPOM.clickAnswerChoiceOne();
		teacherCreateTestPOM.clickNextQuestion();
		// answering second question
		teacherCreateTestPOM.clickAnswerChoiceOne();
		teacherCreateTestPOM.clickNextQuestion();
		// answering third question
		teacherCreateTestPOM.clickAnswerChoiceOne();
		teacherCreateTestPOM.clickNextQuestion();
		// answering fourth question
		teacherCreateTestPOM.clickAnswerChoiceOne();
		teacherCreateTestPOM.clickTestEndTest();
	}

	// Verification of Test Preview message
	@Test(priority = 8)
	public void verifyTestSubmittedMessage() {
		String expected = "Saved.";
		String actual = teacherCreateTestPOM.verifyTestSubmittedMessage();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("TestSubmittedMessage_ELTC_95");
	}

	// Closing of the application
	@AfterClass
	public void tearDown() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.quit();
	}

}
