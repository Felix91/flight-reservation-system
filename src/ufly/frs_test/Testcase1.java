package ufly.frs_test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Testcase1 {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8888/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testCase1() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.name("oneWayOrReturn")).click();
		driver.findElement(By.cssSelector("label.radio")).click();
		driver.findElement(By.id("in_from")).clear();
		driver.findElement(By.id("in_from")).sendKeys("YVR");
		driver.findElement(By.id("in_to")).clear();
		driver.findElement(By.id("in_to")).sendKeys("AAF");
		driver.findElement(By.id("in_depart")).clear();
		driver.findElement(By.id("in_depart")).sendKeys("11-22-2012");
		
		driver.findElement(By.id("in_psgr")).clear();
		driver.findElement(By.id("in_psgr")).sendKeys("1");
		driver.findElement(By.cssSelector("button.btn")).click();
		
		try {
			assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Departure Flight Option[\\s\\S]*$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		
		
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
