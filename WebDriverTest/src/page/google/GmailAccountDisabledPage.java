package page.google;

import org.openqa.selenium.By;

import page.PageObject;

public class GmailAccountDisabledPage extends PageObject {
	By filFormLink = By.xpath("//*[@class='cc']/p[4]/a");

	public void clickFilFormLink() {
		click(filFormLink);
	}
}
