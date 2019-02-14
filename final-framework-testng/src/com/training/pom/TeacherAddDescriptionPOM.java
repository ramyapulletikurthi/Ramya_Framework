package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeacherAddDescriptionPOM {
	private WebDriver driver;

	public TeacherAddDescriptionPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@title='Course description']")
	private WebElement teacher_coursedescription;

	@FindBy(xpath = "//img[@title='Description']")
	private WebElement teacher_description;

	@FindBy(id = "course_description_title")
	private WebElement teacher_coursedescriptiontitle;
	
	@FindBy(id = "course_description_submit")
	private WebElement teacher_coursedescriptionsubmit;
	
	@FindBy(xpath = "//div[@class='alert alert-info']")
	private WebElement teacher_courseupdatemessage;
	
	@FindBy(xpath = "//img[@title='Objectives']")
	private WebElement teacher_courseobjectives;
	
	@FindBy(xpath = "//img[@title='Topics']")
	private WebElement teacher_coursetopics;
	
	@FindBy(xpath="//a[@class='dropdown-toggle']")
	private WebElement teacher_logoutdropdown;
	
	@FindBy(id = "logout_button")
	private WebElement teacher_logoutbutton;

	public void clickCourseDescription() {
		this.teacher_coursedescription.click();
	}
	
	public void clickDescription() {
		this.teacher_description.click();
	}
	
	public void sendCourseDescriptionTitle(String teacher_coursedescriptiontitle) {
		this.teacher_coursedescriptiontitle.clear();
		this.teacher_coursedescriptiontitle.sendKeys(teacher_coursedescriptiontitle);
	}

	public void sendCourseContentText(String teacher_coursecontenttext) {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
		driver.findElement(By.cssSelector("body")).sendKeys(teacher_coursecontenttext);
		driver.switchTo().defaultContent();
	}
	
	public void clickCourseDescriptionSubmit() {
		this.teacher_coursedescriptionsubmit.click();
	}
	
	public String verifyCourseDescriptionUpdateMessage(){
		String str = this.teacher_courseupdatemessage.getText();
		return str;
	}
	
	public void clickCourseObjectives() {
		this.teacher_courseobjectives.click();
	}
	
	public void sendCourseObjectivesTitle(String teacher_coursedescriptiontitle) {
		this.teacher_coursedescriptiontitle.clear();
		this.teacher_coursedescriptiontitle.sendKeys(teacher_coursedescriptiontitle);
	}

	public void sendCourseObjectivesContentText(String teacher_coursecontenttext) {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
		driver.findElement(By.cssSelector("body")).sendKeys(teacher_coursecontenttext);
		driver.switchTo().defaultContent();
	}
	
	public void clickCourseObjectivesSubmit() {
		this.teacher_coursedescriptionsubmit.click();
	}
	
	public String verifyCourseObjectivesUpdateMessage(){
		String str1 = this.teacher_courseupdatemessage.getText();
		return str1;
	}
	
	public void clickCourseTopics() {
		this.teacher_coursetopics.click();
	}
	
	public void sendCourseTopicsTitle(String teacher_coursedescriptiontitle) {
		this.teacher_coursedescriptiontitle.clear();
		this.teacher_coursedescriptiontitle.sendKeys(teacher_coursedescriptiontitle);
	}

	public void sendCourseTopicsContentText(String teacher_coursecontenttext) {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
		driver.findElement(By.cssSelector("body")).sendKeys(teacher_coursecontenttext);
		driver.switchTo().defaultContent();
	}
	
	public void clickCourseTopicsSubmit() {
		this.teacher_coursedescriptionsubmit.click();
	}
	
	public String verifyCourseTopicsUpdateMessage(){
		String str2 = this.teacher_courseupdatemessage.getText();
		return str2;
	}
	
	public void clickdropDownforLogout() {
		this.teacher_logoutdropdown.click();
	}
	
	public void clickLogoutButton() {
		this.teacher_logoutbutton.click();
	}

}
