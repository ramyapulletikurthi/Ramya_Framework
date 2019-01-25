package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TeacherAddNewCoursePOM {

	private WebDriver driver;

	public TeacherAddNewCoursePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(text(),'Create a course')]")
	private WebElement teacher_createcourse;

	@FindBy(id = "title")
	private WebElement teacher_coursetitle;

	@FindBy(id = "advanced_params")
	private WebElement teacher_courseadvancedsettings;

	@FindBy(id = "add_course_category_code")
	private WebElement teacher_coursecategory;

	@FindBy(id = "add_course_wanted_code")
	private WebElement teacher_coursecode;

	@FindBy(id = "add_course_course_language")
	private WebElement teacher_courselanguage;

	@FindBy(id = "add_course_submit")
	private WebElement teacher_coursesubmit;

	@FindBy(xpath = "//div[@class='btn-group pull-right']//a[@title='Add an introduction text']")
	private WebElement teacher_courseintrotextbutton;

	@FindBy(id = "introduction_text_intro_cmdUpdate")
	private WebElement teacher_coursesaveintrotext;
	
	@FindBy(xpath = "//div[@class='page-course']//p[2]")
	private WebElement teacher_verifyintrotext;

	public void clickCreateCourse() {
		this.teacher_createcourse.click();
	}

	public void sendCourseTitle(String teacher_coursetitle) {
		this.teacher_coursetitle.clear();
		this.teacher_coursetitle.sendKeys(teacher_coursetitle);
	}

	public void clickAdvanceSettings() {
		this.teacher_courseadvancedsettings.click();
	}

	public void selectCourseCategory(String teacher_coursecategory) {
		Select dropdown = new Select(this.teacher_coursecategory);
		dropdown.selectByVisibleText(teacher_coursecategory);
	}

	public void sendCourseCode(String teacher_coursecode) {
		this.teacher_coursecode.clear();
		this.teacher_coursecode.sendKeys(teacher_coursecode);
	}

	public void selectCourseLanguage(String teacher_courselanguage) {
		Select dropdown = new Select(this.teacher_courselanguage);
		dropdown.selectByVisibleText(teacher_courselanguage);
	}

	public void clickCourseSubmit() {
		this.teacher_coursesubmit.click();
	}

	public void clickIntroductionButton() {
		this.teacher_courseintrotextbutton.click();
	}

	public void sendIntroductionText(String teacher_courseintroductiontext) {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
		driver.findElement(By.cssSelector("body")).sendKeys(teacher_courseintroductiontext);
		driver.switchTo().defaultContent();
	}

	public void clickSaveIntroText() {
		this.teacher_coursesaveintrotext.click();
	}
	
	public String verifyIntroText() {
		String str = this.teacher_verifyintrotext.getText();
		return str;
	}

}
