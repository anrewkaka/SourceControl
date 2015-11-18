package page.google;

import org.openqa.selenium.By;

import page.PageObject;

public class GmailRegisterPage extends PageObject {
	By birthDay = By.xpath("//*[@id='BirthDay']");
	By birthYear = By.xpath("//*[@id='BirthYear']");
	By firstName = By.xpath("//*[@id='FirstName']");
	By gmailAddress = By.xpath("//*[@id='GmailAddress']");
	By hiddenBirthMonth = By.xpath("//*[@id='HiddenBirthMonth']");
	By hiddenGender = By.xpath("//*[@id='HiddenGender']");
	By lastName = By.xpath("//*[@id='LastName']");
	By passwd = By.xpath("//*[@id='Passwd']");
	By passwdAgain = By.xpath("//*[@id='PasswdAgain']");
	By recoverEmailAddress = By.xpath("//*[@id='RecoveryEmailAddress']");
	By recoveryPhoneNumber = By.xpath("//*[@id='RecoveryPhoneNumber']");
	By skipCaptcha = By.xpath("//*[@id='SkipCaptcha']");
	By submitButton = By.xpath("//*[@id='submitbutton']");
	By termsOfService = By.xpath("//*[@id='TermsOfService']");

	public GmailRegisterPage clickSkipCaptcha() {
		click(skipCaptcha);

		return this;
	}

	public GmailUserSignUpIdvChallengePage clickSubmitButton() {
		click(submitButton);

		return new GmailUserSignUpIdvChallengePage();
	}

	public GmailRegisterPage clickTermsOfService() {
		click(termsOfService);

		return this;
	}

	public void load() {
		super.load("https://accounts.google.com/signup");
	}

	public GmailRegisterPage typeBirthDay(String text) {
		type(birthDay, text);

		return this;
	}

	public GmailRegisterPage typeBirthYear(String text) {
		type(birthYear, text);

		return this;
	}

	public GmailRegisterPage typeFirstName(String text) {
		type(firstName, text);

		return this;
	}

	public GmailRegisterPage typeGmailAddress(String text) {
		type(gmailAddress, text);

		return this;
	}

	public GmailRegisterPage typeHiddenBirthMonth(String text) {
		try {
			typeHiddenWithId(hiddenBirthMonth, text);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	public GmailRegisterPage typeHiddenGender(String text) {
		try {
			typeHiddenWithId(hiddenGender, text);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	public GmailRegisterPage typeLastName(String text) {
		type(lastName, text);

		return this;
	}

	public GmailRegisterPage typePasswd(String text) {
		type(passwd, text);

		return this;
	}

	public GmailRegisterPage typePasswdAgain(String text) {
		type(passwdAgain, text);

		return this;
	}

	public GmailRegisterPage typeRecoverEmailAddress(String text) {
		type(recoverEmailAddress, text);

		return this;
	}

	public GmailRegisterPage typeRecoveryPhoneNumber(String text) {
		type(recoveryPhoneNumber, text);

		return this;
	}
}
