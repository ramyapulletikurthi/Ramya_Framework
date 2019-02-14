package com.training.functional.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.dataproviders.RegistrationDataProviders;
import com.training.generics.GenericMethods;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.RegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

/*
 * Author  					: Ramya Krishna Pulletikurthi
 * Test Case ID				: ELTC_093
 * Test Case Description 	: TO verify whether registered student details get stored in database
 * PreCondition				: User should have launched the application
 */

public class StudentRegistrationUsingDataBase_ELTC93 {

	private static WebDriver driver;
	private static String baseUrl;
	private static LoginPOM loginPOM;
	private static RegistrationPOM registrationPOM;
	private static Properties properties;
	private static ScreenShot screenShot;
	private GenericMethods genericMethods;

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
		genericMethods = new GenericMethods(driver);
		// open the browser
		driver.get(baseUrl);
	}

	// Registering into the application as student and verifying the same data wuith
	// the data available in the database
	@Test(dataProvider = "db-inputs", dataProviderClass = RegistrationDataProviders.class)
	public void studentRegistration(String firstName, String lastName, String eMail, String userName, String password,
			String confirmPassword, String phoneNumber, String language) {
		loginPOM.clickSignUpBtn();
		registrationPOM.sendFirstName("Ramya1");
		String FirstName = registrationPOM.returnFirstName();
		registrationPOM.sendLastName("Krishna1");
		String LastName = registrationPOM.returnLastName();
		registrationPOM.sendEmail("ramyaraj1107@abc.com");
		String EMail = registrationPOM.returnEmail();
		registrationPOM.sendUserName("ramya9090");
		String UserName = registrationPOM.returnUserName();
		registrationPOM.sendPassword("ramya1");
		String Password = registrationPOM.returnPassword();
		registrationPOM.sendConfirmPassword("ramya1");
		String ConfirmPassword = registrationPOM.returnConfirmPassword();
		registrationPOM.sendPhoneNumber("1234567801");
		String PhoneNumber = registrationPOM.returnPhoneNumber();
		registrationPOM.selectLanguage("English");
		String Language = registrationPOM.returnLanguage();
		registrationPOM.clickProfile();
		registrationPOM.clickRegistrationButton();
		// Message Verification
		String expected = "An email has been sent to help you remember your login and password.";
		String actual = registrationPOM.messageVerification();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("RegistrationSucessful_ELTC_093");

		// Verification of application values w.r.t db values using assertion.As the db
		// is not connected to the application the above registration values will not be
		// inserted into the db.Hence the assertion will fail.
		assertEquals(FirstName, firstName);
		assertEquals(LastName, lastName);
		assertEquals(EMail, eMail);
		assertEquals(UserName, userName);
		assertEquals(Password, password);
		assertEquals(ConfirmPassword, confirmPassword);
		assertEquals(PhoneNumber, phoneNumber);
		assertEquals(Language, language);

	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.quit();
	}

}
