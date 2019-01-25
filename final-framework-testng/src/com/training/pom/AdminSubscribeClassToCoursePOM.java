package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdminSubscribeClassToCoursePOM {

	private WebDriver driver;

	public AdminSubscribeClassToCoursePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@title='Administration']")
	private WebElement admin_administrationtab;

	@FindBy(xpath = "//a[contains(text(),'Classes')]")
	private WebElement admin_classeslink;

	@FindBy(xpath = "//select[@class='ui-pg-selbox']")
	private WebElement admin_maximizeclasseslist;

	@FindBy(xpath = "//a[@href='add_courses_to_usergroup.php?id=87']")
	private WebElement admin_subscribeclasstocourse;

	@FindBy(xpath = "//select[@name='firstLetterUser']")
	private WebElement admin_firstletterofcourse;

	@FindBy(id = "elements_not_in")
	private WebElement admin_selectedcourse;

	@FindBy(xpath = "//em[@class='fa fa-arrow-right']")
	private WebElement admin_forwardbutton;

	@FindBy(xpath = "//button[contains(text(),'Subscribe class to courses')]")
	private WebElement admin_subscribesubmitbutton;

	@FindBy(xpath = "//tr[@id='87']//td[3]")
	private WebElement admin_coursescount;

	public void clickAdminTab() {
		this.admin_administrationtab.click();
	}

	public void clickClassesLink() {
		this.admin_classeslink.click();
	}

	public void selectMaximumFilterValue(String admin_maximizeclasseslist) {
		Select dropdown = new Select(this.admin_maximizeclasseslist);
		dropdown.selectByVisibleText(admin_maximizeclasseslist);
	}

	public void clickSubscribeClassToCourse() {
		this.admin_subscribeclasstocourse.click();
	}

	public void selectFirstLetterofCourse(String admin_firstletterofcourse) {
		Select dropdown = new Select(this.admin_firstletterofcourse);
		dropdown.selectByVisibleText(admin_firstletterofcourse);
	}

	public void selectSelectedCourse(String admin_selectedcourse) {
		Select dropdown = new Select(this.admin_selectedcourse);
		dropdown.selectByVisibleText(admin_selectedcourse);
	}

	public void clickForwardButton() {
		this.admin_forwardbutton.click();
	}

	public void clickSubscribeSubmitButton() {
		this.admin_subscribesubmitbutton.click();
	}

	public String verifyCourcesCount() {
		String str1 = this.admin_coursescount.getText();
		return str1;
	}

}
