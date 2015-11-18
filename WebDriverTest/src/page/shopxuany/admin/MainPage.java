package page.shopxuany.admin;

import org.openqa.selenium.By;

import page.PageObject;

import common.Constants;

public class MainPage extends PageObject {
	By menuBtn = By.xpath("//*[@id='button-menu']");
	By leftNavi = By.xpath("//*[@id='column-left']");
	By catalogMng = By.xpath("//*[@id='catalog']/a");
	By prdLink = By.xpath("//*[@id='catalog']/ul/li[2]/a");

	public MainPage clickMenuBtn() {
		click(menuBtn);
		waitForTransition(10);

		return this;
	}

	public String getLeftNaviCssClass() {

		return getAttribute(leftNavi, Constants.ATTR_CSS_CLASS);
	}

	public MainPage clickCatalogMng() {
		click(catalogMng);
		waitForTransition(10);

		return this;
	}

	public ProductListPage clickPrdLink() {
		click(prdLink);

		return new ProductListPage();
	}
}
