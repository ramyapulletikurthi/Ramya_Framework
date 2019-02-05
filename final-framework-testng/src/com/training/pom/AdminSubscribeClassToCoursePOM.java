package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
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

	@FindBy(xpath = "//table[@id='usergroups']/tbody/tr")
	private List<WebElement> admin_subscribeclasstocourse;

	private String coursebeforeXpath = "//table[@id='usergroups']/tbody/tr[";
	private String courseafterXpath = "]/td[1]";

	private String subscribebeforeXpath = "//table[@id='usergroups']/tbody/tr[";
	private String subscribeafterXpath = "]/td[6]/a[2]";

	private int j = 0;

	@FindBy(xpath = "//select[@name='firstLetterUser']")
	private WebElement admin_firstletterofcourse;

	@FindBy(id = "elements_not_in")
	private WebElement admin_selectedcourse;

	@FindBy(xpath = "//em[@class='fa fa-arrow-right']")
	private WebElement admin_forwardbutton;

	@FindBy(xpath = "//button[contains(text(),'Subscribe class to courses')]")
	private WebElement admin_subscribesubmitbutton;

	private String coursecountbeforeXpath = "//table[@id='usergroups']/tbody/tr[";
	private String coursecountafterXpath = "]/td[3]";

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

	// Method to find the required course and click on subscription
	public void subscribeClassToCourse(String courseName) {
		boolean namePresent = false;
		List<WebElement> courserow = admin_subscribeclasstocourse;
		int rowCount = courserow.size();
		for (int i = 1; i <= rowCount; i++) {
			String courseActualXpath = coursebeforeXpath + i + courseafterXpath;// xpath of the course name
			WebElement element = driver.findElement(By.xpath(courseActualXpath));
			if (element.getText().equals(courseName)) {
				namePresent = true;
				j = i;
				break;
			}
		}
		System.out.println(j);
		String subscribeActualXpath = subscribebeforeXpath + j + subscribeafterXpath;
		driver.findElement(By.xpath(subscribeActualXpath)).click();

	}

	// end of course selection and subscription

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
		String coursecountActualXpath = coursecountbeforeXpath + j + coursecountafterXpath;
		String str1 = driver.findElement(By.xpath(coursecountActualXpath)).getText();
		return str1;
	}

}
