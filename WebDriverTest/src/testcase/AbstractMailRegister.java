package testcase;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import utils.JdbcExecute;
import utils.StringUtils;

import common.Constants;

public abstract class AbstractMailRegister {
	public static String RESOURCE_FOLDER = "H:\\WebSite\\EclipseWS\\WebDriverTest\\resource\\";

	private static ArrayList<String> ho = new ArrayList<String>();
	private static ArrayList<String> lot = new ArrayList<String>();
	private static Random randomGenerator = new Random();
	private static ArrayList<String> ten = new ArrayList<String>();

	protected String anyHo() {
		int index = randomGenerator.nextInt(ho.size());
		return ho.get(index);
	}

	protected String anyLot() {
		int index = randomGenerator.nextInt(lot.size());
		return lot.get(index);
	}

	protected String anyTen() {
		int index = randomGenerator.nextInt(ten.size());
		return ten.get(index);
	}

	private void getFileValue(int type, String fileName) {
		String filePath = RESOURCE_FOLDER + fileName;
		File secureCodeFile = new File(filePath);
		try {
			String text = "";
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new java.io.FileReader(
					secureCodeFile));
			while ((text = br.readLine()) != null) {
				if (type == 1) {
					ten.add(text);
				} else if (type == 2) {
					lot.add(text);
				} else if (type == 3) {
					ho.add(text);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	abstract protected void regAcc();

	public void run() {
		getFileValue(1, "ten_vn.txt");
		getFileValue(2, "ho_lot_vn.txt");
		getFileValue(3, "ho_vn.txt");

		regAcc();
	}

	public int getRandomIntLtZero(int bound) {
		int result = randomGenerator.nextInt(bound);
		if (result == 0) {
			return getRandomIntLtZero(bound);
		}
		return result;
	}

	public boolean getRandomBoolean() {
		return randomGenerator.nextBoolean();
	}

	public String getCaptcha() {
		String query = "select common_value from common_values cv where name='captcha';";
		String captcha = Constants.STR_EMPTY;

		while (!StringUtils.isStrValid(captcha)
				|| StringUtils.isStrEquals(captcha, Constants.STR_DB_NULL)) {
			captcha = JdbcExecute.executeScalar(query, "common_value");
		}
		String updQuery = "update common_values set common_value='' where name='captcha';";
		JdbcExecute.executeUpdate(updQuery);
		return captcha;
	}
}
