package page.shopxuany.admin;

import org.openqa.selenium.By;

import page.PageObject;

public class ProductRegisterPage extends PageObject {
	By prdName = By.xpath("//*[@id='input-name2']");

	public ProductRegisterPage typePrdName(String text) {
		type(prdName, text);

		return this;
	}

	By prdDesc = By.xpath("//*[@id='language2']/div[2]/div/div/div[6]");

	public ProductRegisterPage typePrdDesc(String text) {
		type(prdDesc, text);

		return this;
	}

	By dataTab = By.xpath("//*[@id='form-product']/ul/li[2]/a");

	public ProductRegisterPage clickDataTab() {
		click(dataTab);

		return this;
	}

	By thumbImg = By.xpath("//*[@id='thumb-image']/img");

	public ProductRegisterPage clickThumbImg() {
		click(thumbImg);

		return this;
	}

	By editThumb = By.xpath("//*[@id='button-image']/i");

	public ProductRegisterPage clickEditThumb() {
		click(editThumb);

		return this;
	}

	By prdImgDir = By
			.xpath("//*[@id='modal-image']/div/div/div[2]/div[2]/div[3]/div/a/i");

	public ProductRegisterPage clickPrdImgDir() {
		click(prdImgDir);

		return this;
	}

	By uplImg = By.xpath("//*[@id='button-upload']");

	public ProductRegisterPage clickUplImg() {
		click(uplImg);

		return this;
	}

	By prdId = By.xpath("//*[@id='input-model']");

	public ProductRegisterPage typePrdId(String text) {
		type(prdId, text);

		return this;
	}

	By prdPrc = By.xpath("//*[@id='input-price']");

	public ProductRegisterPage typePrdPrc(String text) {
		type(prdPrc, text);

		return this;
	}

	By prdQty = By.xpath("//*[@id='input-quantity']");

	public ProductRegisterPage typePrdQty(String text) {
		type(prdQty, text);

		return this;
	}

	By linkTab = By.xpath("//*[@id='form-product']/ul/li[3]/a");

	public ProductRegisterPage clickLinkTab() {
		click(linkTab);

		return this;
	}

	By category = By.xpath("//*[@id='input-category']");

	public ProductRegisterPage typeCategory(String text) {
		type(category, text);

		return this;
	}

	By categorySel = By.xpath("//*[@id='tab-links']/div[2]/div/ul");

	public ProductRegisterPage selectFirstCategorySel() {
		try {
			selectFirstListItem(categorySel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	By saveBtn = By.xpath("//*[@id='content']/div[1]/div/div/button");

	public ProductRegisterPage clickSaveBtn() {
		click(saveBtn);

		return this;
	}

	By title = By.xpath("//*[@id='input-meta-title2']");

	public ProductRegisterPage typeTitle(String text) {
		type(title, text);

		return this;
	}

	public ProductRegisterPage reload(String token) {
		super.load("/admin/index.php?route=catalog/product/add&token=" + token);
		return this;
	}
}
