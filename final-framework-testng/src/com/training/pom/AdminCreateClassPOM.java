package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdminCreateClassPOM {
	private WebDriver driver;
	
	public AdminCreateClassPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@title='Administration']")
	private WebElement admin_administrationtab;

	@FindBy(xpath = "//a[contains(text(),'Classes')]")
	private WebElement admin_classeslink;

	@FindBy(xpath = "//img[@title='Add classes']")
	private WebElement admin_addclasseslink;

	@FindBy(id = "usergroup_name")
	private WebElement admin_classname;

	@FindBy(id = "usergroup_description")
	private WebElement admin_classdescription;

	@FindBy(id = "usergroup_visibility")
	private WebElement admin_classgrouppermission;

	@FindBy(id = "usergroup_submit")
	private WebElement admin_addclasslink;

	@FindBy(xpath = "//div[@class='alert alert-success']")
	private WebElement admin_verifysuccessmessage;

	@FindBy(xpath = "//select[@class='ui-pg-selbox']")
	private WebElement admin_maximizeclasseslist;

	@FindBy(xpath = "//td[@title='demobyramyapulleti']")
	private WebElement admin_verifyclassvalue;

	public void clickAdminTab() {
		this.admin_administrationtab.click();
	}

	public void clickClassesLink() {
		this.admin_classeslink.click();
	}

	public void clickAddClassesLink() {
		this.admin_addclasseslink.click();
	}

	public void sendClassName(String admin_classname) {
		this.admin_classname.clear();
		this.admin_classname.sendKeys(admin_classname);
	}

	public void sendClassDescription(String admin_classdescription) {
		this.admin_classdescription.clear();
		this.admin_classdescription.sendKeys(admin_classdescription);
	}

	public void selectClassGroupPermission(String admin_classgrouppermission) {
		Select dropdown = new Select(this.admin_classgrouppermission);
		dropdown.selectByVisibleText(admin_classgrouppermission);
	}

	public void clickSubmitClassLink() {
		this.admin_addclasslink.click();
	}

	public String verifySuccessMessage() {
		String str = this.admin_verifysuccessmessage.getText();
		return str;
	}

	public void selectMaximumFilterValue(String admin_maximizeclasseslist) {
		Select dropdown = new Select(this.admin_maximizeclasseslist);
		dropdown.selectByVisibleText(admin_maximizeclasseslist);
	}

	public String verifyClassValue() {
		String str1 = this.admin_verifyclassvalue.getText();
		return str1;
	}

}
