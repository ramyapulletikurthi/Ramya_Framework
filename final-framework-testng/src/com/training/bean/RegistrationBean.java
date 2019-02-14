package com.training.bean;

public class RegistrationBean {
	private String firstName;
	private String lastName;
	private String eMail;
	private String userName;
	private String password;
	private String confirmPassword;
	private String phoneNumber;
	private String language;

	public RegistrationBean() {

	}

	public RegistrationBean(String firstName, String lastName, String eMail, String userName, String password,
			String confirmPassword, String phoneNumber, String language) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = eMail;
		this.userName = userName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.phoneNumber = phoneNumber;
		this.language = language;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	/*@Override
	public String toString() {
		return "RegistrationBean [firstName=" + firstName + ", lastName=" + lastName + ", eMail=" + eMail +", userName=" + userName +", password=" + password +", confirmPassword=" + confirmPassword +", phoneNumber=" + phoneNumber +", language=" + language +"]";
	}*/

}
