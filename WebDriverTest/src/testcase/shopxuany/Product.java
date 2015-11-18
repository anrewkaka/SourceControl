package testcase.shopxuany;

import page.shopxuany.admin.LoginPage;
import page.shopxuany.admin.ProductListPage;
import page.shopxuany.admin.ProductRegisterPage;

public class Product {
	public static void main(String[] args) {
		ProductListPage prdLstPage = new ProductListPage();

		LoginPage loginPage = prdLstPage.load();
		loginPage.typeUsername("admin");
		loginPage.typePassword("Gydcuatui1");

		loginPage.clickLoginBtn();

		ProductRegisterPage prdRegPage = prdLstPage.clickAddPrdBtn();
		prdRegPage.typePrdName("test prd nm");
		prdRegPage.typePrdDesc("test prd desc");
		prdRegPage.typeTitle("aaaaaaaaaaaaaaaa");

		prdRegPage.clickDataTab();
		prdRegPage.typePrdId("abc");
		prdRegPage.typePrdPrc("123");
		prdRegPage.typePrdQty("10");

		// prdRegPage.clickThumbImg();
		// prdRegPage.clickEditThumb();
		//
		// prdRegPage.waitForTransition(2);
		// prdRegPage.clickPrdImgDir();
		// prdRegPage.waitForTransition(2);
		// prdRegPage.clickUplImg();
		// prdRegPage.selectUploadFile("/RubyUploads/shopxuany_dqgzpqewht.jpg");

		prdRegPage.clickLinkTab();
		prdRegPage.typeCategory("Son");
		prdRegPage.waitForTransition(1);
		prdRegPage.selectFirstCategorySel();

		prdRegPage.typeCategory("Trang Điểm");
		prdRegPage.waitForTransition(1);
		prdRegPage.selectFirstCategorySel();
		prdRegPage.clickSaveBtn();
	}
}
