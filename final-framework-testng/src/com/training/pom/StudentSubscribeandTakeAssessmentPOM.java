package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StudentSubscribeandTakeAssessmentPOM {
	private WebDriver driver;

	public StudentSubscribeandTakeAssessmentPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(text(),'Course catalog')]")
	private WebElement student_coursecatlog;

	@FindBy(xpath = "//input[@name='search_term']")
	private WebElement student_searchcoursename;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement student_searchcoursesubmit;
	// Elements to find and click on the course subscription
	@FindBy(xpath = "//div[@class='grid-courses']//div[@class='col-xs-12 col-sm-6 col-md-4']")
	private List<WebElement> student_courses;

	private String beforeXpath = "//div[@class='row']//div[@class='row']//div[";
	private String afterXpath = "]//div[1]//div[2]//h4";

	private String subscribestatusBeforeXpath = "//div[@class='row']//div[";
	private String subscribestatusAfterXpath = "]//div[@class='btn-group']";

	private String courseBeforeXpath = "//div[@class='row']//div[@class='row']//div[";
	private String courseAfterXpath = "]//div[1]//div[2]//div[4]//a[1]";

	@FindBy(xpath = "//div[@class='alert alert-info']")
	private WebElement student_subscriptionmessage;

	@FindBy(xpath = "//a[@title='My courses']")
	private WebElement student_mycourses;

	private String coursetoclick;//declaring a variable to store the course name in order to use in My course tab to click on the course
	private String clickcourseBeforexpath = "//a[@class='thumbnail']/img[@title='";
	private String clickcourseAfterxpath = "']";
	
	@FindBy(xpath = "//img[@title='Assessments']")
	private WebElement student_assessmentsicon;

	@FindBy(xpath = "//a[contains(text(),'Selenium Test')]")
	private WebElement student_testname;

	@FindBy(xpath = "//a[@class='btn btn-success btn-large']")
	private WebElement student_starttest;

	@FindBy(xpath = "//label[1]/input")
	private WebElement student_testanswerchoiceone;

	@FindBy(xpath = "//button[@name='save_now']")
	private WebElement student_testnextquestion;

	@FindBy(xpath = "//label[2]/input")
	private WebElement student_testanswerchoicesecond;

	@FindBy(xpath = "//button[@name='save_now']")
	private WebElement student_testendtest;

	@FindBy(xpath = "//div[@class='alert alert-info']")
	private WebElement student_testquestionmessage;

	public void clickCourseCatlog() {
		this.student_coursecatlog.click();
	}

	public void searchCourseName(String student_searchcoursename) {
		this.student_searchcoursename.clear();
		this.student_searchcoursename.sendKeys(student_searchcoursename);
	}

	public void clickSearchCourseSubmit() {
		this.student_searchcoursesubmit.click();
	}

	// Method to find the required course and click on subscription
	public String subscribeCourse(String courseName) {
		boolean namePresent = false;
		List<WebElement> courserow = student_courses;
		int rowCount = courserow.size();

		int j = 0;
		for (int i = 1; i <= rowCount; i++) {
			String actualXpath = beforeXpath + i + afterXpath;// xpath of the course name
			WebElement element = driver.findElement(By.xpath(actualXpath));
			if (element.getText().equals(courseName)) {
				namePresent = true;
				j = i;
				break;
			}
		}
		coursetoclick = courseName;
		String subscribeStatusActualxpath = subscribestatusBeforeXpath + j + subscribestatusAfterXpath;
		String subscribeStatusValue = driver.findElement(By.xpath(subscribeStatusActualxpath)).getText();
		if (subscribeStatusValue.equals("Subscribe")) {
			String courseActualXpath = courseBeforeXpath + j + courseAfterXpath;
			driver.findElement(By.xpath(courseActualXpath)).click();
		} else {
			System.out.println("Course is aready subscribed");
		}
		return subscribeStatusValue;

	}

	// end of course selection and subscription

	public String verifyCourseSubscriptionMessage() {
		String str = this.student_subscriptionmessage.getText();
		return str;
	}

	public void clickMyCourses() {
		this.student_mycourses.click();
	}

	public void clickOpenCourse() {
		String courseclickActualXpath = clickcourseBeforexpath + coursetoclick + clickcourseAfterxpath;
		driver.findElement(By.xpath(courseclickActualXpath)).click();
	}

	public void clickAssessmentsIcon() {
		this.student_assessmentsicon.click();
	}

	public void clickTestName() {
		this.student_testname.click();
	}

	public void clickStartTest() {
		this.student_starttest.click();
	}

	public void clickAnswerChoiceOnce() {
		this.student_testanswerchoiceone.click();
	}

	public void clickNextQuestion() {
		this.student_testnextquestion.click();
	}

	public void clickAnswerChoiceSecond() {
		this.student_testanswerchoicesecond.click();
	}

	public void clickTestEndTest() {
		this.student_testendtest.click();
	}

	public String verifyTestSubmittedMessage() {
		String str = this.student_testquestionmessage.getText();
		return str;
	}

}
