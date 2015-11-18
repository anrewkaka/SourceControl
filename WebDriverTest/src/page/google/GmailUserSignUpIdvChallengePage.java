package page.google;

import org.openqa.selenium.By;

import page.PageObject;

public class GmailUserSignUpIdvChallengePage extends PageObject {
	By nextButton = By.xpath("//*[@id='next-button']");

	public GmailVerifyPhonePage clickNextButton() {
		click(nextButton);

		return new GmailVerifyPhonePage();
	}
}
