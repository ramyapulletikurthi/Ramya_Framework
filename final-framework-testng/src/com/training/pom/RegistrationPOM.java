package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPOM {
	private WebDriver driver;

	public RegistrationPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "registration_firstname")
	private WebElement registration_firstname;

	@FindBy(id = "registration_lastname")
	private WebElement registration_lastname;

	@FindBy(id = "registration_email")
	private WebElement registration_email;

	@FindBy(id = "username")
	private WebElement registration_username;

	@FindBy(id = "pass1")
	private WebElement registration_password;

	@FindBy(id = "pass2")
	private WebElement registration_confirmpassword;

	@FindBy(id = "registration_phone")
	private WebElement registration_phone;

	@FindBy(id="registration_language")
	private WebElement registration_language;

	@FindBy(xpath="(//input[@name='status'])[1]")
	private WebElement registration_profile;

	@FindBy(id = "registration_submit")
	private WebElement registration_submit;
	
	@FindBy(xpath="//p[contains(text(),'An email has been sent to help you remember your l')]")
	private WebElement registration_messageverification;

	public void sendFirstName(String registration_firstname) {
		this.registration_firstname.clear();
		this.registration_firstname.sendKeys(registration_firstname);
	}

	public void sendLastName(String registration_lastname) {
		this.registration_lastname.clear();
		this.registration_lastname.sendKeys(registration_lastname);
	}

	public void sendEmail(String registration_email) {
		this.registration_email.clear();
		this.registration_email.sendKeys(registration_email);
	}

	public void sendUserName(String registration_username) {
		this.registration_username.clear();
		this.registration_username.sendKeys(registration_username);
	}

	public void sendPassword(String registration_password) {
		this.registration_password.clear();
		this.registration_password.sendKeys(registration_password);
	}

	public void sendConfirmPassword(String registration_confirmpassword) {
		this.registration_confirmpassword.clear();
		this.registration_confirmpassword.sendKeys(registration_confirmpassword);
	}

	public void sendPhoneNumber(String registration_phone) {
		this.registration_phone.clear();
		this.registration_phone.sendKeys(registration_phone);
	}

	public void selectLanguage(String registration_language) {
			Select dropdown = new Select(this.registration_language);
			dropdown.selectByVisibleText(registration_language);
	}

	public void clickProfile() {
		this.registration_profile.click();
	}

	public void clickRegistrationButton() {
		this.registration_submit.click();
	}
	
	public String messageVerification() {
		String str = this.registration_messageverification.getText();
		//System.out.println(str);
		return str;
	}

}
