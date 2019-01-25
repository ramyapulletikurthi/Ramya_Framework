package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdminCreateCoursePOM {

	private WebDriver driver;
	private String str;

	public AdminCreateCoursePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@title='Administration']")
	private WebElement admin_administrationtab;

	@FindBy(xpath = "//a[contains(text(),'Create a course')]")
	private WebElement admin_createcourse;

	@FindBy(id = "update_course_title")
	private WebElement admin_updatecoursetitle;

	@FindBy(id = "visual_code")
	private WebElement admin_codetext;

	@FindBy(id = "course_teachers")
	private WebElement admin_courseteachers;

	@FindBy(id = "update_course_category_code")
	private WebElement admin_updatecoursecategory;

	@FindBy(id = "update_course_course_language")
	private WebElement admin_updatecourselanguage;

	@FindBy(id = "update_course_submit")
	private WebElement admin_updatecoursesubmit;

	@FindBy(id = "course-search-keyword")
	private WebElement admin_coursesearchkeyword;

	@FindBy(id = "search_simple_submit")
	private WebElement admin_searchsimplesubmit;
	
	@FindBy(xpath="//*[contains(text(),'TestingRamya')]")
	private WebElement admin_requiredcousretitle;
	
	public void clickAdminTab() {
		this.admin_administrationtab.click();
	}

	public void clickCreateCourse() {
		this.admin_createcourse.click();
	}

	public void sendCourseTitle(String admin_updatecoursetitle) {
		this.admin_updatecoursetitle.clear();
		this.admin_updatecoursetitle.sendKeys(admin_updatecoursetitle);
		this.str = admin_updatecoursetitle;
	}

	public void sendCourseCode(String admin_codetext) {
		this.admin_codetext.clear();
		this.admin_codetext.sendKeys(admin_codetext);
	}
	
	public void selectCourseTeacher(String admin_courseteachers) {
		Select dropdown = new Select(this.admin_courseteachers);
		dropdown.selectByVisibleText(admin_courseteachers);
	}

	public void selectCourseCategory(String admin_updatecoursecategory) {
		Select dropdown = new Select(this.admin_updatecoursecategory);
		dropdown.selectByVisibleText(admin_updatecoursecategory);
	}

	public void selectCourseLanguage(String admin_updatecourselanguage) {
		Select dropdown = new Select(this.admin_updatecourselanguage);
		dropdown.selectByVisibleText(admin_updatecourselanguage);
	}

	public void clickCourseSubmit() {
		this.admin_updatecoursesubmit.click();
	}

	public void sendCourseSearchCode() {
		this.admin_coursesearchkeyword.clear();
		this.admin_coursesearchkeyword.sendKeys(this.str);
	}

	public void clickSearchSimpleSubmit() {
		this.admin_searchsimplesubmit.click();
	}
	
	public String findCourseTitle() {
		String str1 = this.admin_requiredcousretitle.getText();
		return str1;
	}
	
	

}
