package page.google;

import org.openqa.selenium.By;

import page.PageObject;

public class GmailVerifyPhonePage extends PageObject {
	By verifyPhone = By.xpath("//*[@id='verify-phone-input']");
	By submitButton = By.xpath("//*[@id='next-button']");

	public GmailWelcomePage clickSubmitButton() {
		click(submitButton);

		return new GmailWelcomePage();
	}

	public GmailVerifyPhonePage typeVerifyPhone(String text) {
		type(verifyPhone, text);
		return this;
	}
}
