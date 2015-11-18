package testcase.google;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import page.google.GmailRegisterPage;
import testcase.AbstractMailRegister;
import utils.StringUtils;

import common.Constants;

public class GmailRegister extends AbstractMailRegister {

	public static void main(String[] agrs) {
		GmailRegister my = new GmailRegister();
		my.run();
	}

	@Override
	public void regAcc() {

		String randPostFix = RandomStringUtils.random(6, true, true);

		String ten = anyTen();
		String lot = anyLot();
		String ho = anyHo();

		GmailRegisterPage reg = new GmailRegisterPage();
		reg.load();

		reg.typeLastName(ten);
		reg.typeFirstName(ho + " " + lot);

		String mail = String.format("%s%s%s.%s", ho, lot, ten, randPostFix);
		mail = StringUtils.unAccent(mail.toLowerCase());

		try {
			Collection<String> lines = new ArrayList<String>();
			lines.add(mail);
			FileUtils.writeLines(new File(Constants.RESOURCE_FOLDER
					+ "mail_list.txt"), lines, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		reg.typeGmailAddress(mail);

		reg.typePasswd("DaylaP@ssword1");
		reg.typePasswdAgain("DaylaP@ssword1");

		String day = getRandomIntLtZero(28) + "";
		reg.typeBirthDay(day);

		String month = String.format("%02d", getRandomIntLtZero(12));
		reg.typeHiddenBirthMonth(month);

		reg.typeBirthYear("1988");

		String sex = "";
		if (getRandomBoolean()) {
			sex = "MALE";
		} else {
			sex = "FEMALE";
		}
		reg.typeHiddenGender(sex);

		String phone = "+84919"
				+ String.format("%06d", getRandomIntLtZero(999999));
		reg.typeRecoveryPhoneNumber(phone);
		reg.typeRecoverEmailAddress("nguyenthanhlan.la82bc@gmail.com");
		// reg.clickSkipCaptcha();
		reg.clickTermsOfService();
		//
		// GmailUserSignUpIdvChallengePage confirmPage =
		// reg.clickSubmitButton();
		//
		// GmailVerifyPhonePage gmailVerifyPhonePage =
		// confirmPage.clickNextButton();
		//
		// File secureCodeFile = new File(RESOURCE_FOLDER + "code.txt");
		// try {
		// String verifyPhone = "";
		// @SuppressWarnings("resource")
		// BufferedReader br = new BufferedReader(new java.io.FileReader(
		// secureCodeFile));
		// while ("".equals(verifyPhone = br.readLine())) {
		//
		// }
		//
		// gmailVerifyPhonePage.typeVerifyPhone(verifyPhone);
		// @SuppressWarnings("resource")
		// BufferedWriter bw = new BufferedWriter(new FileWriter(
		// secureCodeFile));
		// bw.write("");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// GmailWelcomePage gmailWelcomePage =
		// gmailVerifyPhonePage.clickSubmitButton();
		//
		// GmailLoginPage gmailLoginPage = gmailWelcomePage.clickSubmitButton();
		// gmailLoginPage.typePasswd("DaylaP@ssword1");
	}
}
