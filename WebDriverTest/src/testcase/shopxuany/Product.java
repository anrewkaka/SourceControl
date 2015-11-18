package testcase.shopxuany;

import page.shopxuany.admin.LoginPage;
import page.shopxuany.admin.ProductListPage;
import page.shopxuany.admin.ProductRegisterPage;
import utils.JdbcExecute;
import dto.shopxuany.ProductDto;

public class Product {
	public static void main(String[] args) {

		ProductListPage prdLstPage = new ProductListPage();
		LoginPage loginPage = prdLstPage.load();
		loginPage.typeUsername("admin");
		loginPage.typePassword("Gydcuatui1");
		loginPage.clickLoginBtn();

		String token = prdLstPage.getToken();
		ProductRegisterPage prdRegPage = prdLstPage.clickAddPrdBtn();

		for (int i = 0; i < 2; i++) {
			prdRegPage = prdRegPage.reload(token);

			ProductDto product = ProductDto
					.createPrdInfo("Son	Son Vacci Hàn Quốc	75000	rqffbngbpslfbngvvsdj.txt	shopxuany_aaqlgghnxc.jpg	shopxuany_ytjdqoplxd.jpg→shopxuany_vtxtfhxpaa.jpg→");
			reg(prdRegPage, product);
		}

	}

	private static void reg(ProductRegisterPage prdRegPage, ProductDto product) {
		prdRegPage.typePrdName(product.prdName);
		prdRegPage.typePrdDesc(product.prdDesc);
		prdRegPage.typeTitle(product.title);

		prdRegPage.clickDataTab();
		prdRegPage.typePrdId(product.prdId);
		prdRegPage.typePrdPrc(product.prdPrc);
		prdRegPage.typePrdQty(product.prdQty);

		prdRegPage.clickLinkTab();
		prdRegPage.typeCategory(product.cat1);
		prdRegPage.waitForTransition(1);
		prdRegPage.selectFirstCategorySel();
		prdRegPage.typeCategory(product.cat2);
		prdRegPage.waitForTransition(1);
		prdRegPage.selectFirstCategorySel();

		prdRegPage.clickSaveBtn();

		prdRegPage.waitForTransition(5);

		String prdId = JdbcExecute.executeScalar(
				"select max(product_id) as product_id from oc_product",
				"product_id");
		System.err.println(prdId);
	}
}
