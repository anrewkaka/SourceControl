package dto.shopxuany;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class ProductDto {
	public String prdName;
	public String prdDesc;
	public String title;
	public String prdId;
	public String prdPrc;
	public String prdQty;
	public String cat1;
	public String cat2;
	public String imgThumb;
	public List<String> img;

	private static int COLUMN_INDEX_CAT2 = 0;
	private static int COLUMN_INDEX_NAME = 1;
	private static int COLUMN_INDEX_PRICE = 2;
	private static int COLUMN_INDEX_DESC_FL_NM = 3;
	private static int COLUMN_INDEX_THUMB_FL_NM = 4;
	private static int COLUMN_INDEX_IMG_FL_NM_LST = 5;
	private static String TARGET_FOLDER = "C:\\Users\\anrew\\Desktop\\RubyUploads\\";
	private static String PRODUCT_IMAGE_FOLDER = "catalog/san_pham/";

	public static ProductDto createPrdInfo(String line) {
		String[] prdInf = line.split("\\t");

		ProductDto product = new ProductDto();
		product.prdName = prdInf[COLUMN_INDEX_NAME];

		String desc = "";
		try {
			desc = FileUtils.readFileToString(new File(TARGET_FOLDER
					+ prdInf[COLUMN_INDEX_DESC_FL_NM]));
		} catch (IOException e) {
			e.printStackTrace();
		}
		product.prdDesc = desc;

		product.title = String.format("Shop Xuân Ý - Son Môi - %s",
				product.prdName);
		product.prdId = "testestse";
		product.prdPrc = prdInf[COLUMN_INDEX_PRICE];
		product.prdQty = "20";
		product.cat1 = "Trang Điểm";
		product.cat2 = prdInf[COLUMN_INDEX_CAT2];
		product.imgThumb = PRODUCT_IMAGE_FOLDER
				+ prdInf[COLUMN_INDEX_THUMB_FL_NM];
		product.img = new ArrayList<String>();
		String[] prdImgs = prdInf[COLUMN_INDEX_IMG_FL_NM_LST].split("→");
		for (String prdImg : prdImgs) {
			product.img.add(PRODUCT_IMAGE_FOLDER + prdImg);
		}

		return product;
	}
}
