package page;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.ui.Select;

import utils.StringUtils;

public class PageObject {
	/** http://192.168.1.222 */
	protected static String BASE_URI = "http://192.168.1.222";
	DesiredCapabilities cap = new DesiredCapabilities();

	// Create a new instance of Firefox Browser
	protected static WebDriver driver;
	protected Proxy proxy = new Proxy();

	public PageObject() {
		if (driver == null) {
			driver = new FirefoxDriver();
		}
	}

	protected void click(By element) {
		driver.findElement(element).click();
	}

	protected void focusById(By element) throws Exception {
		String id = driver.findElement(element).getAttribute("id");
		if (!StringUtils.isStrValid(id)) {
			throw new Exception("element khong ton tai thuoc tinh id");
		}

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("document.getElementById('" + id + "').focus();");
	}

	public String getAttribute(By element, String attr) {

		return driver.findElement(element).getAttribute(attr);
	}

	public void getScreenShot(By element, String outPath) throws IOException {
		WebElement ele = driver.findElement(element);
		// Get entire page screenshot
		File screenshot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = ImageIO.read(screenshot);
		// Get the location of element on the page
		Point point = ele.getLocation();
		// Get width and height of the element
		int eleWidth = ele.getSize().getWidth();
		int eleHeight = ele.getSize().getHeight();
		// Crop the entire page screenshot to get only element screenshot
		BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(),
				point.getY(), eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);
		// Copy the element screenshot to disk
		FileUtils.copyFile(screenshot, new File(outPath));
	}

	public boolean isElementVisible(By element) {
		boolean result = false;
		try {
			if (driver.findElements(element).size() > 0) {
				return true;
			}
			result = false;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public void load(String url) {
		// Open the URL in firefox browser
		driver.get(BASE_URI + url);
	}

	protected void selectByValue(By element, String text) {
		Select select = new Select(driver.findElement(element));
		select.selectByValue(text);
	}

	protected void selectByVisibleText(By element, String text) {
		Select select = new Select(driver.findElement(element));
		select.selectByVisibleText(text);
	}

	public void setProxy(String httpProxy) {
		proxy.setProxyType(ProxyType.MANUAL);
		proxy.setHttpProxy(httpProxy);
		cap.setCapability(CapabilityType.PROXY, proxy);
		driver = new FirefoxDriver(cap);
	}

	// Type text to element
	protected void type(By element, String text) {
		driver.findElement(element).clear();
		driver.findElement(element).sendKeys(text);
	}

	protected void typeHiddenWithId(By element, String text) throws Exception {
		String id = driver.findElement(element).getAttribute("id");
		if (!StringUtils.isStrValid(id)) {
			throw new Exception("element khong ton tai thuoc tinh id");
		}

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("document.getElementById('" + id
				+ "').setAttribute('type', 'text');");

		type(element, text);
	}

	protected void typeWait(By element, String text) {
		driver.findElement(element).clear();
		waitForTransition(1);
		driver.findElement(element).sendKeys(text);
	}

	public void waitForTransition(long timeout) {
		timeout *= 1000;
		synchronized (driver) {
			try {
				driver.wait(timeout);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected void selectUploadFile(By element, String path) {
		((FirefoxDriver) driver).setFileDetector(new LocalFileDetector());
		WebElement upload = driver.findElement(element);
		upload.sendKeys(path);
	}

	protected void selectFirstListItem(By element) throws Exception {
		List<WebElement> li = driver.findElement(element).findElements(
				By.xpath("li/a"));

		if (li.size() == 0) {
			throw new Exception("khong ton tai list item");
		}

		li.get(0).click();
	}
}
