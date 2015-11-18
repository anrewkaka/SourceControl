package page.outlook;

import java.io.IOException;

import org.openqa.selenium.By;

import page.PageObject;
import testcase.outlook.WelcomePage;

public class OutlookRegisterPage extends PageObject {
	By birthDay = By.xpath("//*[@id='BirthDay']");
	By birthYear = By.xpath("//*[@id='BirthYear']");
	By firstName = By.xpath("//*[@id='FirstName']");
	By gmailAddress = By.xpath("//*[@id='MemberName']");
	By birthMonth = By.xpath("//*[@id='BirthMonth']");
	By gender = By.xpath("//*[@id='Gender']");
	By lastName = By.xpath("//*[@id='LastName']");
	By passwd = By.xpath("//*[@id='Password']");
	By passwdAgain = By.xpath("//*[@id='RetypePassword']");
	By recoverEmailAddress = By.xpath("//*[@id='RecoveryEmailAddress']");
	By recoveryPhoneNumber = By.xpath("//*[@id='PhoneNumber']");
	By captcha = By.xpath("//*[@id='hipTemplateContainer']/div[3]/input");
	By imgCaptcha = By.xpath("//*[@id='hipTemplateContainer']/div/img");
	By submitButton = By.xpath("//*[@id='CredentialsAction']");
	By country = By.xpath("//*[@id='PhoneCountry']");

	public OutlookRegisterPage focusCaptcha() {
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

	public OutlookRegisterPage selectCountry(String text) {
		selectByValue(country, text);

		return this;
	}

	public void load() {
		super.load("https://signup.live.com/signup");
	}

	public OutlookRegisterPage selectBirthDay(String text) {
		selectByValue(birthDay, text);

		return this;
	}

	public OutlookRegisterPage selectBirthYear(String text) {
		selectByValue(birthYear, text);

		return this;
	}

	public OutlookRegisterPage getImgCaptchaScreenShot(String outPath) {
		try {
			getScreenShot(imgCaptcha, outPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return this;
	}

	public OutlookRegisterPage typeCaptcha(String text) {
		type(captcha, text);

		return this;
	}

	public OutlookRegisterPage typeFirstName(String text) {
		type(firstName, text);

		return this;
	}

	public OutlookRegisterPage typeGmailAddress(String text) {
		type(gmailAddress, text);

		return this;
	}

	public OutlookRegisterPage selectBirthMonth(String text) {
		selectByValue(birthMonth, text);

		return this;
	}

	public OutlookRegisterPage selectGender(String text) {
		selectByValue(gender, text);

		return this;
	}

	public OutlookRegisterPage typeLastName(String text) {
		type(lastName, text);

		return this;
	}

	public OutlookRegisterPage focusPasswd() {
		try {
			focusById(passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	public OutlookRegisterPage focusPasswdAgain() {
		try {
			focusById(passwdAgain);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	public OutlookRegisterPage typePasswd(String text) {
		typeWait(passwd, text);

		return this;
	}

	public OutlookRegisterPage typePasswdAgain(String text) {
		type(passwdAgain, text);

		return this;
	}

	public OutlookRegisterPage typeRecoverEmailAddress(String text) {
		type(recoverEmailAddress, text);

		return this;
	}

	public OutlookRegisterPage typeRecoveryPhoneNumber(String text) {
		type(recoveryPhoneNumber, text);

		return this;
	}
}
