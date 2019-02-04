package com.training.functional.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.TeacherCreateTestPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TeacherCreateTest_ELTC062 {
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

	@Test(priority = 2)
	public void validLoginTest() {
		loginPOM.sendUserName("ramya89");
		loginPOM.sendPassword("ramya@1989");
		loginPOM.clickLoginBtn();
	}

	@Test(priority = 3)
	public void teacherAddNewTest() throws InterruptedException {
		teacherCreateTestPOM.clickCourseName();
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

	@Test(priority = 4)
	public void verifyTestCreatedMessage() {
		String expected = "Exercise added";
		String actual = teacherCreateTestPOM.verifyTestCreatedMessage();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("TestCreatedMessage_ELTC_062");
	}

	@Test(priority = 5)
	public void teacherAddFirstQuestionToTest() throws InterruptedException {
		// adding first question with options
		teacherCreateTestPOM.clickTestMultipleChoice();
		teacherCreateTestPOM.sendTestQuestionName("which course your learning");
		teacherCreateTestPOM.sendQuestionFirstOption("selenium");
		teacherCreateTestPOM.sendQuestionSecondOption("java");
		teacherCreateTestPOM.sendQuestionThirdOption("c");
		teacherCreateTestPOM.sendQuestionFourthOption("c#");
		teacherCreateTestPOM.clickTestFirstRadioButton();
		teacherCreateTestPOM.clickTestSubmitQuestion();
	}

	@Test(priority = 6)
	public void verifyTestFirstQuestionAddedMessage() {
		String expected = "1 questions, for a total score (all questions) of 0.";
		String actual = teacherCreateTestPOM.verifyTestQuestionAddedMessage();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("TestFirstQuestionAddedMessage_ELTC_62");
	}

	@Test(priority = 7)
	public void teacherAddSecondQuestionToTest() throws InterruptedException {
		// adding second question with options
		teacherCreateTestPOM.clickTestMultipleChoice();
		teacherCreateTestPOM.sendTestQuestionName("which language are you using in selenium");
		teacherCreateTestPOM.sendQuestionFirstOption("python");
		teacherCreateTestPOM.sendQuestionSecondOption("java");
		teacherCreateTestPOM.sendQuestionThirdOption("c");
		teacherCreateTestPOM.sendQuestionFourthOption("c#");
		teacherCreateTestPOM.clickTestSecondRadioButton();
		teacherCreateTestPOM.clickTestSubmitQuestion();
	}

	@Test(priority = 8)
	public void verifyTestSecondQuestionAddedMessage() {
		String expected = "2 questions, for a total score (all questions) of 0.";
		String actual = teacherCreateTestPOM.verifyTestQuestionAddedMessage();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("TestSecondQuestionAddedMessage_ELTC_62");
	}

	@Test(priority = 9)
	public void teacherPreviewAddTest() throws InterruptedException {
		// preview the test
		teacherCreateTestPOM.clickTestPreview();
		teacherCreateTestPOM.clickStartTest();
		// answering first question
		Thread.sleep(2000);
		teacherCreateTestPOM.clickAnswerChoiceOne();
		teacherCreateTestPOM.clickNextQuestion();
		// answering second question
		teacherCreateTestPOM.clickAnswerChoiceSecond();
		teacherCreateTestPOM.clickTestEndTest();
	}
	
	@Test(priority = 10)
	public void verifyTestSubmittedMessage() {
		String expected = "Saved.";
		String actual = teacherCreateTestPOM.verifyTestSubmittedMessage();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("TestSubmittedMessage_ELTC_62");
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

}
