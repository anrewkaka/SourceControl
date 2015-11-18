package page.shopxuany.admin;

import org.openqa.selenium.By;

import page.PageObject;

public class ProductListPage extends PageObject {
	By addPrdBtn = By.xpath("//*[@id='content']/div[1]/div/div/a/i");

	public ProductRegisterPage clickAddPrdBtn() {
		click(addPrdBtn);

		return new ProductRegisterPage();
	}

	public LoginPage load() {
		super.load("/admin/index.php?route=catalog/product");
		return new LoginPage();
	}
}
