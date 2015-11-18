package page.shopxuany.admin;

import org.openqa.selenium.By;

import page.PageObject;

import common.Constants;

public class ProductListPage extends PageObject {
	By addPrdBtn = By.xpath("//*[@id='content']/div[1]/div/div/a/i");
	By addLink = By.xpath("//*[@id='content']/div[1]/div/div/a");

	public ProductRegisterPage clickAddPrdBtn() {
		click(addPrdBtn);

		return new ProductRegisterPage();
	}

	public String getToken() {
		String url = getAttribute(addLink, Constants.ATTR_HREF);
		String[] tmp = url.split("token=");

		return tmp[1];
	}

	public LoginPage load() {
		super.load("/admin/index.php?route=catalog/product");
		return new LoginPage();
	}

}
