package com.training.functional.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.RegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class StudentRegistration_ELTC031 {
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

	@Test(priority = 1)
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		registrationPOM = new RegistrationPOM(driver);
		loginPOM = new LoginPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	@Test(priority = 2)
	public void studentRegistration() throws InterruptedException {
		loginPOM.clickSignUpBtn();
		// Thread.sleep(3000);
		registrationPOM.sendFirstName("Ramya");
		registrationPOM.sendLastName("Krishna");
		registrationPOM.sendEmail("ramya567@abc.com");
		registrationPOM.sendUserName("ramya1989");
		registrationPOM.sendPassword("abc@123");
		registrationPOM.sendConfirmPassword("abc@123");
		registrationPOM.sendPhoneNumber("1234567891");
		registrationPOM.selectLanguage("English");
		registrationPOM.clickProfile();
		registrationPOM.clickRegistrationButton();

	}
	
	@Test(priority = 3)
	public void messageVerification() {
		String expected = "An email has been sent to help you remember your login and password.";
		String actual = registrationPOM.messageVerification();
		assertEquals(actual,expected);
		screenShot.captureScreenShot("RegistrationSucessful_ELTC_031");
	}
	
	@Test(priority = 4)
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
