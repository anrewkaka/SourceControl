package page.google;

import org.openqa.selenium.By;

import page.PageObject;

public class GmailLoginPage extends PageObject {
	By passwd = By.xpath("//*[@id='Passwd']");

	public GmailLoginPage typePasswd(String text) {
		type(passwd, text);

		return this;
	}
}
