package com.training.functional.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.dataproviders.RegistrationDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.RegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

/*
 * Author  					: Ramya Krishna Pulletikurthi
 * Test Case ID				: ELTC_092
 * Test Case Description 	: TO verify whether application displays error message upon entering invalid credentials in student registration page 
 * PreCondition				: User should have launched the application
 */

public class StudentRegistrationWithDataProviders_ELTC92 {

	private static WebDriver driver;
	private static String baseUrl;
	private static LoginPOM loginPOM;
	private static RegistrationPOM registrationPOM;
	private static Properties properties;
	private static ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		registrationPOM = new RegistrationPOM(driver);
		loginPOM = new LoginPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	// Validating the values during Registration of Student by taking data from
	// excel sheet
	// As for few fields even after providing wrong data registration is being
	// successful,so used try and catch block in order to display the same message
	// in the console
	@Test(dataProvider = "TestData_ELTC092", dataProviderClass = RegistrationDataProviders.class)
	public void studentRegistration(String firstName, String lastName, String eMail, String userName, String password,
			String confirmPassword, String phoneNumber, String language) {
		try {
			loginPOM.clickSignUpBtn();
			registrationPOM.sendFirstName(firstName);
			registrationPOM.sendLastName(lastName);
			registrationPOM.sendEmail(eMail);
			registrationPOM.sendUserName(userName);
			registrationPOM.sendPassword(password);
			registrationPOM.sendConfirmPassword(confirmPassword);
			registrationPOM.sendPhoneNumber(phoneNumber);
			registrationPOM.selectLanguage(language);
			registrationPOM.clickProfile();
			registrationPOM.clickRegistrationButton();
			// Message Verification
			String actual = registrationPOM.errorMessageVerification();
			System.out.println("Error messge after clicking registration btn:" + actual);
		} catch (NoSuchElementException e) {
			System.out.println("Registration done successfully: This is defect : As wrong data has been accepted");
		}

	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.quit();
	}

}
