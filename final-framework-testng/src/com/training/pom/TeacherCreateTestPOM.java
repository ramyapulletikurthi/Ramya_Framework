package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeacherCreateTestPOM {
	
	private WebDriver driver;

	public TeacherCreateTestPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@title='selenium']")
	private WebElement teacher_coursename;
	
	@FindBy(xpath = "//img[@title='Tests']")
	private WebElement teacher_testslink;
	
	@FindBy(xpath = "//img[@title='Create a new test']")
	private WebElement teacher_createtest;
	
	@FindBy(id = "exercise_title")
	private WebElement teacher_exercisetestname;
	
	@FindBy(id = "advanced_params")
	private WebElement teacher_testadvancedsettings;
	
	@FindBy(id = "exerciseType_0")
	private WebElement teacher_testselectfeedback;
	
	@FindBy(xpath = "//input[@name='activate_start_date_check']")
	private WebElement teacher_testenablestarttime;
	
	@FindBy(id = "start_time_alt_text")
	private WebElement teacher_teststarttimealerttext;
	
	@FindBy(xpath = "//button[contains(text(),'Now')]")
	private WebElement teacher_setstarttime;
	
	@FindBy(xpath = "//button[contains(text(),'Done')]")
	private WebElement teacher_submitstarttime;
	
	@FindBy(id = "pass_percentage")
	private WebElement teacher_testpasspercentage;
	
	@FindBy(id = "exercise_admin_submitExercise")
	private WebElement teacher_testsubmitexercise;
	
	@FindBy(xpath = "//div[@class='alert alert-success']")
	private WebElement teacher_testcreatedmessage;
	
	@FindBy(xpath = "//img[@title='Multiple choice']")
	private WebElement teacher_testmultiplechoice;
	
	@FindBy(id = "question_admin_form_questionName")
	private WebElement teacher_testquestionname;
	
	@FindBy(xpath = "//input[@name='correct'][@value=1]")
	private WebElement teacher_testselectfirstradio;
	
	@FindBy(id = "submit-question")
	private WebElement teacher_testsubmitquestion;
	
	@FindBy(xpath = "//div[@class='alert alert-info']")
	private WebElement teacher_testquestionmessage;
	
	@FindBy(xpath = "//input[@name='correct'][@value=2]")
	private WebElement teacher_testselectsecondradio;
	
	@FindBy(xpath = "//img[@title='Preview']")
	private WebElement teacher_testpreview;
	
	@FindBy(xpath = "//a[@class='btn btn-success btn-large']")
	private WebElement teacher_starttest;
	
	@FindBy(xpath = "//label[1]/input")
	private WebElement teacher_testanswerchoiceone;
	
	@FindBy(xpath = "//button[@name='save_now']")
	private WebElement teacher_testnextquestion;
	
	@FindBy(xpath = "//label[2]/input")
	private WebElement teacher_testanswerchoicesecond;
	
	@FindBy(xpath = "//button[@name='save_now']")
	private WebElement teacher_testendtest;
	
	public void clickCourseName() {
		this.teacher_coursename.click();
	}
	
	public void clickTestsLink() {
		this.teacher_testslink.click();
	}
	
	public void clickCreateNewTest() {
		this.teacher_createtest.click();
	}
	
	public void sendTestName(String teacher_exercisetestname) {
		this.teacher_exercisetestname.clear();
		this.teacher_exercisetestname.sendKeys(teacher_exercisetestname);
	}
	
	public void clickAdvancedSettings() {
		this.teacher_testadvancedsettings.click();
	}
	
	public void sendCourseTestContext(String teacher_coursetestcontext) {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
		driver.findElement(By.cssSelector("body")).sendKeys(teacher_coursetestcontext);
		driver.switchTo().defaultContent();
	}
	
	public void clickTestFeedback() {
		this.teacher_testselectfeedback.click();
	}
	
	public void clickEnableStartTime() {
		this.teacher_testenablestarttime.click();
	}
	
	public void clickTestStartTimeText() {
		this.teacher_teststarttimealerttext.click();
	}
	
	public void clickSetStartTime() {
		this.teacher_setstarttime.click();
	}
	
	public void clickSubmitStartTime() {
		this.teacher_submitstarttime.click();
	}
	
	public void sendTestPassPercentage(String teacher_testpasspercentage) {
		this.teacher_testpasspercentage.clear();
		this.teacher_testpasspercentage.sendKeys(teacher_testpasspercentage);
	}
	
	public void clickTestSubmitExercise() {
		this.teacher_testsubmitexercise.click();
	}
	
	public String verifyTestCreatedMessage() {
		String str = this.teacher_testcreatedmessage.getText();
		return str;
	}
	
	public void clickTestMultipleChoice() {
		this.teacher_testmultiplechoice.click();
	}
	
	public void sendTestQuestionName(String teacher_testquestionname) {
		this.teacher_testquestionname.clear();
		this.teacher_testquestionname.sendKeys(teacher_testquestionname);
	}
	
	public void sendQuestionFirstOption(String teacher_questionfirstoption) {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset'][@title='Rich Text Editor, answer[1]']")));
		driver.findElement(By.cssSelector("body")).sendKeys(teacher_questionfirstoption);
		driver.switchTo().defaultContent();
	}
	
	public void sendQuestionSecondOption(String teacher_questionsecondoption) {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset'][@title='Rich Text Editor, answer[2]']")));
		driver.findElement(By.cssSelector("body")).sendKeys(teacher_questionsecondoption);
		driver.switchTo().defaultContent();
	}
	
	public void sendQuestionThirdOption(String teacher_questionthirdoption) {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset'][@title='Rich Text Editor, answer[3]']")));
		driver.findElement(By.cssSelector("body")).sendKeys(teacher_questionthirdoption);
		driver.switchTo().defaultContent();
	}
	
	public void sendQuestionFourthOption(String teacher_questionfourthoption) {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset'][@title='Rich Text Editor, answer[4]']")));
		driver.findElement(By.cssSelector("body")).sendKeys(teacher_questionfourthoption);
		driver.switchTo().defaultContent();
	}
	
	public void clickTestFirstRadioButton() {
		this.teacher_testselectfirstradio.click();
	}
	
	public void clickTestSubmitQuestion() {
		this.teacher_testsubmitquestion.click();
	}
	
	public String verifyTestQuestionAddedMessage() {
		String str = this.teacher_testquestionmessage.getText();
		return str;
	}
	
	public void clickTestSecondRadioButton() {
		this.teacher_testselectsecondradio.click();
	}
	
	public void clickTestPreview() {
		this.teacher_testpreview.click();
	}
	
	public void clickStartTest() {
		this.teacher_starttest.click();
	}
	
	public void clickAnswerChoiceOne() {
		this.teacher_testanswerchoiceone.click();
	}
	
	public void clickNextQuestion() {
		this.teacher_testnextquestion.click();
	}
	
	public void clickAnswerChoiceSecond() {
		this.teacher_testanswerchoicesecond.click();
	}
	
	public void clickTestEndTest() {
		this.teacher_testendtest.click();
	}
	
	public String verifyTestSubmittedMessage() {
		String str = this.teacher_testquestionmessage.getText();
		return str;
	}

}
