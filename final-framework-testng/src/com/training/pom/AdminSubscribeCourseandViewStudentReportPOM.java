package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminSubscribeCourseandViewStudentReportPOM {

	private WebDriver driver;

	public AdminSubscribeCourseandViewStudentReportPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@title='Homepage']")
	private WebElement admin_homepage;

	@FindBy(xpath = "//a[contains(text(),'Course catalog')]")
	private WebElement admin_coursecatlog;

	@FindBy(xpath = "//input[@name='search_term']")
	private WebElement admin_searchcoursename;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement admin_searchcoursesubmit;
	// Elements to find and click on the course subscription
	@FindBy(xpath = "//div[@class='grid-courses']//div[@class='col-xs-12 col-sm-6 col-md-4']")
	private List<WebElement> admin_courses;

	private String beforeXpath = "//div[@class='row']//div[@class='row']//div[";
	private String afterXpath = "]//div[1]//div[2]//h4";

	private String subscribestatusBeforeXpath = "//div[@class='row']//div[";
	private String subscribestatusAfterXpath = "]//div[@class='btn-group']";

	private String courseBeforeXpath = "//div[@class='row']//div[@class='row']//div[";
	private String courseAfterXpath = "]//div[1]//div[2]//div[4]//a[1]";

	@FindBy(xpath = "//div[@class='alert alert-info']")
	private WebElement admin_subscriptionmessage;

	@FindBy(xpath = "//a[@title='Reporting']")
	private WebElement admin_reportingicon;

	@FindBy(xpath = "//a[contains(text(),'Followed students')]")
	private WebElement admin_followedstudents;

	@FindBy(id = "search_user_keyword")
	private WebElement admin_searchstudentname;

	@FindBy(id = "search_user_submit")
	private WebElement admin_clicksearchbutton;

	@FindBy(xpath = "//tr[@class='row_even']//td[5]/a[2]")
	private WebElement admin_studentdetailsicon;

	/*
	 * @FindBy(xpath = "//tr//td[7]/a") private WebElement admin_coursedetailsicon;
	 */

	@FindBy(xpath = "//div//table[@class='table table-striped table-hover courses-tracking']/tbody/tr")
	private List<WebElement> admin_coursesinreportpage;

	private String coursenamebeforeXpath = "//table[@class='table table-striped table-hover courses-tracking']//tbody//tr[";
	private String coursenameafterXpath = "]//td[1]";

	private String detailsselectionBeforeXpath = "//tbody//tr[";
	private String detailsselectionAfterXpath = "]//td[7]";

	public void clickAdminHomePage() {
		this.admin_homepage.click();
	}

	public void clickCourseCatlog() {
		this.admin_coursecatlog.click();
	}

	public void searchCourseName(String student_searchcoursename) {
		this.admin_searchcoursename.clear();
		this.admin_searchcoursename.sendKeys(student_searchcoursename);
	}

	public void clickSearchCourseSubmit() {
		this.admin_searchcoursesubmit.click();
	}

	// Method to find the required course and click on subscription
	public String subscribeCourse(String courseName) {
		boolean namePresent = false;
		List<WebElement> courserow = admin_courses;
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
		String str = this.admin_subscriptionmessage.getText();
		return str;
	}

	public void clickReportingIcon() {
		this.admin_reportingicon.click();
	}

	public void clickFollowedStudents() {
		this.admin_followedstudents.click();
	}

	public void sendStudentSearchName(String admin_searchstudentname) {
		this.admin_searchstudentname.clear();
		this.admin_searchstudentname.sendKeys(admin_searchstudentname);
	}

	public void clickSearchButton() {
		this.admin_clicksearchbutton.click();
	}

	public void clickStudentDetailsIcon() {
		this.admin_studentdetailsicon.click();
	}

	public void clickCourseDetailsIcon(String courseName) {
		boolean namePresent = false;
		List<WebElement> courserow = admin_coursesinreportpage;
		int rowCount = courserow.size();

		int j = 0;
		for (int i = 1; i <= rowCount; i++) {
			String actualXpath = coursenamebeforeXpath + i + coursenameafterXpath;// xpath of the course name
			WebElement element = driver.findElement(By.xpath(actualXpath));
			if (element.getText().equals(courseName)) {
				namePresent = true;
				j = i;
				break;
			}
		}
		System.out.println(j);
		String detailsSelectionActualxpath = detailsselectionBeforeXpath + j + detailsselectionAfterXpath;
		driver.findElement(By.xpath(detailsSelectionActualxpath)).click();
		;
	}

}
