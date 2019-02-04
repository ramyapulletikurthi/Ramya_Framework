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
import com.training.pom.TeacherGenerateStudentReportPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TeacherGenerateStudentReport_ELTC064 {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private TeacherGenerateStudentReportPOM teacherGenerateStudentReportPOM;
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
		teacherGenerateStudentReportPOM = new TeacherGenerateStudentReportPOM(driver);
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
	public void teacherGenerateStudentReport() throws InterruptedException {	
		teacherGenerateStudentReportPOM.clickReportingIcon();
		teacherGenerateStudentReportPOM.clickFollowedStudents();
		teacherGenerateStudentReportPOM.sendStudentSearchName("Ramya");
		teacherGenerateStudentReportPOM.clickSearchButton();
		teacherGenerateStudentReportPOM.clickStudentDetailsIcon();
		teacherGenerateStudentReportPOM.clickCourseDetailsIcon();
		teacherGenerateStudentReportPOM.clickTestQuizIcon();
		teacherGenerateStudentReportPOM.clickSendEmailCheckBox();
		teacherGenerateStudentReportPOM.clickCorrectTestButton();
		//verification of mail sent message
		String expected = "Message Sent";
		String actual = teacherGenerateStudentReportPOM.verifyMailSentMessage();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("MailSentMessage_ELTC_064");
		teacherGenerateStudentReportPOM.clickCourseNameLink();
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
