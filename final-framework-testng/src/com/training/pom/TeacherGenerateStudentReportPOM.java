package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeacherGenerateStudentReportPOM {
	
	private WebDriver driver;
	
	public TeacherGenerateStudentReportPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@title='Reporting']")
	private WebElement teacher_reportingicon;
	
	@FindBy(xpath = "//a[contains(text(),'Followed students')]")
	private WebElement teacher_followedstudents;
	
	@FindBy(id = "search_user_keyword")
	private WebElement teacher_searchstudentname;
	
	@FindBy(id = "search_user_submit")
	private WebElement teacher_clicksearchbutton;
	
	@FindBy(xpath = "//tr[@class='row_even']//td[5]/a")
	private WebElement teacher_studentdetailsicon;
	
	@FindBy(xpath = "//tr//td[7]/a")
	private WebElement teacher_coursedetailsicon;
	
	@FindBy(xpath = "//tr[@class='row_even']//td[5]/a")
	private WebElement teacher_testquizicon;
	
	@FindBy(xpath = "//input[@name='send_notification']")
	private WebElement teacher_sendemail;
	
	@FindBy(id = "myform_submit")
	private WebElement teacher_clickcorrecttest;
	
	@FindBy(xpath = "//div[@class='alert alert-info']")
	private WebElement teacher_mailsentverification;
	
	@FindBy(xpath = "//a[contains(text(),'selenium123')]")
	private WebElement teacher_coursenamelink;
	
	public void clickReportingIcon() {
		this.teacher_reportingicon.click();
	}
	
	public void clickFollowedStudents() {
		this.teacher_followedstudents.click();
	}
	
	public void sendStudentSearchName(String teacher_searchstudentname) {
		this.teacher_searchstudentname.clear();
		this.teacher_searchstudentname.sendKeys(teacher_searchstudentname);
	}
	
	public void clickSearchButton() {
		this.teacher_clicksearchbutton.click();
	}
	
	public void clickStudentDetailsIcon() {
		this.teacher_studentdetailsicon.click();
	}
	
	public void clickCourseDetailsIcon() {
		this.teacher_coursedetailsicon.click();
	}
	
	public void clickTestQuizIcon() {
		this.teacher_testquizicon.click();
	}
	
	public void clickSendEmailCheckBox() {
		this.teacher_sendemail.click();
	}
	
	public void clickCorrectTestButton() {
		this.teacher_clickcorrecttest.click();
	}
	
	public String verifyMailSentMessage() {
		String str = this.teacher_mailsentverification.getText();
		return str;
	}
	
	public void clickCourseNameLink() {
		this.teacher_coursenamelink.click();
	}

}
