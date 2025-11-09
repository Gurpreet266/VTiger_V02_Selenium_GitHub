package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriver_Utils {
	
	// public void maximizeBrowser
	public void maxPage(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void waitForPageLoad(WebDriver driver, int sec) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));

	}

	public void moveSlider(WebDriver driver, WebElement sld, int x, int y) {
		Actions act = new Actions(driver);
		act.clickAndHold(sld).moveByOffset(x, y).perform();

	}

	public void waitForAlert(WebDriver driver, int sec) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));

		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void elementToBeClickable(WebDriver driver, WebElement element, int sec) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void elementToBeSelected(WebDriver driver, WebElement element, int sec) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.elementToBeSelected(element));
	}

	public void TitleContains(WebDriver driver, String title, int sec) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.titleContains(title));
	}

	// DropDown generic methods
	public void SelectByIndex(WebElement DropdownAddress, int index) {

		Select select = new Select(DropdownAddress);
		select.selectByIndex(index);
	}

	public void SelectByValue(WebElement DropdownAddress, String value) {
		Select select = new Select(DropdownAddress);
		select.selectByValue(value);

	}

	public void SelectByVisibletext(WebElement DropdownAddress, String text) {
		Select select = new Select(DropdownAddress);
		select.selectByVisibleText(text);
	}

	public void SelectByVisible(WebElement DropdownAddress, String text) {
		Select select = new Select(DropdownAddress);
		select.selectByContainsVisibleText(text);
		
	}

	// Action class methods
	public void MoveToElement(WebDriver driver, WebElement mainMenuElement) {
		Actions action = new Actions(driver);
		action.moveToElement(mainMenuElement).perform();

	}

	public void moveToElementAndClick(WebDriver driver, WebElement element) {
		elementToBeClickable(driver, element, 2);
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}

	public void clickElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.click(element).perform();
	}

	public void doubleClickElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	public void dragAndDrop(WebDriver driver, WebElement src, WebElement dest) {
		Actions action = new Actions(driver);
		action.dragAndDrop(src, dest).perform();
		;
	}

	public void ClickAndHold_MoveElement(WebDriver driver, WebElement targetElement, int x, int y) {
		/*
		 * Actions action=new Actions(driver);
		 * action.clickAndHold(targetElement).build().perform();
		 */
		Actions action = new Actions(driver);
		action.clickAndHold(targetElement).moveToLocation(x, y).build().perform();
	}

	public void ScrollToElement_AndClickOnElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.scrollToElement(element).click().build().perform();
	}

	public void ScrollByAmount(WebDriver driver, int x, int y) {
		Actions action = new Actions(driver);
		action.scrollByAmount(x, y).perform();
	}

	// Window
	public String GetParentWindowAddress(WebDriver driver) {

		// String parentWindow=driver.getWindowHandle();
		// return parentWindow;
		return driver.getWindowHandle();

	}

	public void switchToParentWindow(WebDriver driver, String parentWindowHandle) {
		// String parent=driver.getWindowHandle();
		driver.switchTo().window(parentWindowHandle);
	}

	public void SwitchToChildWindow(WebDriver driver) {
		String parent = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String windo : allWindows) {
			if (!windo.equals(parent)) {
				driver.switchTo().window(windo);
				break;
			}
		}

	}

	public void SwitchToParentWindowBycomparison(WebDriver driver, String parentWindow) {
		Set<String> AllWindowAddress = driver.getWindowHandles();
		{
			for (String windowAddress : AllWindowAddress) {
				if (windowAddress.equals(parentWindow)) {
					driver.switchTo().window(parentWindow);
					break;
				}
			}
		}
	}

	public void closeCurrentWindow(WebDriver driver) {
		driver.close();
	}

	public void closeAllWindows(WebDriver driver) {
		driver.quit();
	}

	// Frames
	public void SwitchToFrame(WebDriver driver, WebElement frameEle) {
		driver.switchTo().frame(frameEle);
	}

	public void SwitchToParentFrame(WebDriver driver) {
		driver.switchTo().parentFrame();
	}

	public void SwitchToMainFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	// PopUps
	public void AcceptAlertPopUp(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void DismissAlertPopUp(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void SendDataInAlertPopup(WebDriver driver, String text) {
		driver.switchTo().alert().sendKeys(text);
	}

	public void HandleChromeNotificationPopUP(WebDriver driver) {
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		driver = new ChromeDriver(opt);
		// return new chromeDriver();
	}

	public void HandleFirefoxNotificationPopup(WebDriver driver) {
		FirefoxOptions opt = new FirefoxOptions();
		opt.addPreference("dom.webnotifications.enabled", false);
		driver = new FirefoxDriver(opt);
		// return new FirefoxDriver();??
	}

	// screenshot

	public void TakeWebPageScreenShot(WebDriver driver, String filepath) throws IOException {
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File temp = screenShot.getScreenshotAs(OutputType.FILE);
		File perm = new File(filepath);
		FileHandler.copy(temp, perm);
		// driver.close(); //We should write this here or in script only??

	}

	public void TakeElementScreenShot(WebElement element, String elementpath) throws IOException {
		File temp = element.getScreenshotAs(OutputType.FILE);
		File perm = new File(elementpath);
		FileHandler.copy(temp, perm);
	}

	// javaScript
	public void sendDataToDisabledElementById(WebDriver driver, String id, String val) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + id + "').value='" + val + "'");
	}

	public void EnterValueToElement(WebDriver driver, String val, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + val + "'", element);

	}

	public void ScrollByCoordinates(WebDriver driver, int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(x,y)");
	}

	public void ScrollTillElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}

	public void ScrollToEndOfWindow(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void ZoomIn(WebDriver driver, int value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='" + value + "%'");
	}

}

