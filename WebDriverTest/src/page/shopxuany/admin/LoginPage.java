package page.shopxuany.admin;

import org.openqa.selenium.By;

import page.PageObject;

public class LoginPage extends PageObject {
	By username = By.xpath("//*[@id='input-username']");
	By password = By.xpath("//*[@id='input-password']");
	By loginBtn = By
			.xpath("//*[@id='content']/div/div/div/div/div[2]/form/div[3]/button");

	public LoginPage typeUsername(String text) {
		type(username, text);

		return this;
	}

	public LoginPage typePassword(String text) {
		type(password, text);

		return this;
	}

	public MainPage clickLoginBtn() {
		click(loginBtn);

		return new MainPage();
	}
}
