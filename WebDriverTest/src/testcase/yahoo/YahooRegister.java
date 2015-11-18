package testcase.yahoo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import page.outlook.OutlookRegisterPage;
import testcase.AbstractMailRegister;
import utils.StringUtils;

import common.Constants;

public class YahooRegister extends AbstractMailRegister {

	public static void main(String[] agrs) {
		YahooRegister my = new YahooRegister();
		my.run();
	}

	@Override
	public void regAcc() {

		String randPostFix = RandomStringUtils.random(6, true, true);

		String ten = anyTen();
		String lot = anyLot();
		String ho = anyHo();
		String mail = String.format("%s%s%s.%s@outlook.com", ho, ".", ten,
				randPostFix);
		mail = StringUtils.unAccent(mail.toLowerCase());
		Collection<String> lines = new ArrayList<String>();
		lines.add(mail);
		try {
			FileUtils.writeLines(new File(Constants.RESOURCE_FOLDER
					+ "mail_list.txt"), lines, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		OutlookRegisterPage reg = null;

		// while (true) {
		// try {
		reg = inputForm(ten, ho, lot, mail, randPostFix);
		// reg.waitForTransition(20);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
	}

	public OutlookRegisterPage inputForm(String ten, String ho, String lot,
			String mail, String randPostFix) {
		OutlookRegisterPage reg = new OutlookRegisterPage();
		reg.setProxy("197.159.142.97:8080");
		reg.load();

		reg.typeLastName(ten);
		reg.typeFirstName(ho + " " + lot);

		reg.typeGmailAddress(mail);
		reg.focusPasswd();
		reg.waitForTransition(1);

		reg.typePasswd("DaylaP@ssword1");
		reg.focusPasswdAgain();
		reg.waitForTransition(1);

		reg.typePasswdAgain("DaylaP@ssword1");

		String day = getRandomIntLtZero(28) + "";
		reg.selectBirthDay(day);

		String month = getRandomIntLtZero(12) + "";
		reg.selectBirthMonth(month);

		reg.selectBirthYear("1988");

		String sex = "";
		if (getRandomBoolean()) {
			sex = "m";
		} else {
			sex = "f";
		}
		reg.selectGender(sex);
		reg.selectCountry("VN");

		String phone = "+84919"
				+ String.format("%06d", getRandomIntLtZero(999999));
		reg.typeRecoveryPhoneNumber(phone);

		reg.focusCaptcha();
		// try {
		// FileUtils.cleanDirectory(new File(RESOURCE_FOLDER + "\\captcha"));
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// reg.getImgCaptchaScreenShot(RESOURCE_FOLDER + "\\captcha\\img.jpg");
		// String captcha = getCaptcha();
		// System.err.println("==================" + captcha
		// + "==================");
		// reg.typeCaptcha(captcha);
		// reg.waitForTransition(1);
		return reg;
	}
}
