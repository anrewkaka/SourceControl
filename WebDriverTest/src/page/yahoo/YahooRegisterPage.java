package page.yahoo;

import java.io.IOException;

import org.openqa.selenium.By;

import page.PageObject;
import testcase.outlook.WelcomePage;

public class YahooRegisterPage extends PageObject {
	By birthDay = By.xpath("//*[@id='day']");
	By birthMonth = By.xpath("//*[@id='month']");
	By birthYear = By.xpath("//*[@id='year']");
	By firstName = By.xpath("//*[@id='firstname']");
	By lastName = By.xpath("//*[@id='secondname']");
	By gmailAddress = By.xpath("//*[@id='yahooid']");
	By phoneNumber = By.xpath("//*[@id='mobileNumber']");
	By recoveryPhoneNumber = By.xpath("//*[@id='mobile-rec']");
	By gender = By.xpath("//*[@id='female']");
	By relationship = By.xpath("//*[@id='relationship']");
	By passwd = By.xpath("//*[@id='password']");

	By passwdAgain = By.xpath("//*[@id='RetypePassword']");

	By captcha = By.xpath("//*[@id='hipTemplateContainer']/div[3]/input");
	By imgCaptcha = By.xpath("//*[@id='hipTemplateContainer']/div/img");
	By submitButton = By.xpath("//*[@id='CredentialsAction']");
	By country = By.xpath("//*[@id='PhoneCountry']");

	public YahooRegisterPage focusCaptcha() {
		try {
			focusById(captcha);
		} catch (Exception e) {
		}

		return this;
	}

	public WelcomePage clickSubmitButton() {
		click(submitButton);

		return new WelcomePage();
	}

	public YahooRegisterPage selectCountry(String text) {
		selectByValue(country, text);

		return this;
	}

	public void load() {
		super.load("https://signup.live.com/signup");
	}

	public YahooRegisterPage selectBirthDay(String text) {
		selectByValue(birthDay, text);

		return this;
	}

	public YahooRegisterPage selectBirthYear(String text) {
		selectByValue(birthYear, text);

		return this;
	}

	public YahooRegisterPage getImgCaptchaScreenShot(String outPath) {
		try {
			getScreenShot(imgCaptcha, outPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return this;
	}

	public YahooRegisterPage typeCaptcha(String text) {
		type(captcha, text);

		return this;
	}

	public YahooRegisterPage typeFirstName(String text) {
		type(firstName, text);

		return this;
	}

	public YahooRegisterPage typeGmailAddress(String text) {
		type(gmailAddress, text);

		return this;
	}

	public YahooRegisterPage selectBirthMonth(String text) {
		selectByValue(birthMonth, text);

		return this;
	}

	public YahooRegisterPage selectGender(String text) {
		selectByValue(gender, text);

		return this;
	}

	public YahooRegisterPage typeLastName(String text) {
		type(lastName, text);

		return this;
	}

	public YahooRegisterPage focusPasswd() {
		try {
			focusById(passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	public YahooRegisterPage focusPasswdAgain() {
		try {
			focusById(passwdAgain);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	public YahooRegisterPage typePasswd(String text) {
		typeWait(passwd, text);

		return this;
	}

	public YahooRegisterPage typePasswdAgain(String text) {
		type(passwdAgain, text);

		return this;
	}

	public YahooRegisterPage typeRecoveryPhoneNumber(String text) {
		type(recoveryPhoneNumber, text);

		return this;
	}
}
