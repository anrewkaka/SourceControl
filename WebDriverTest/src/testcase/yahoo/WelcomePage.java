package testcase.yahoo;

import org.openqa.selenium.By;

import page.PageObject;

public class WelcomePage extends PageObject {
	By greetingMessage = By.xpath("//*[@id='greeting-message']");

	public boolean isGreatingMessageVisible() {
		return isElementVisible(greetingMessage);
	}
}
