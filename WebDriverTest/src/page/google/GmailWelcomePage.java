package page.google;

import org.openqa.selenium.By;

import page.PageObject;

public class GmailWelcomePage extends PageObject {
	By submitButton = By.xpath("//*[@id='submitbutton']");

	public GmailLoginPage clickSubmitButton() {
		click(submitButton);

		return new GmailLoginPage();
	}
}
