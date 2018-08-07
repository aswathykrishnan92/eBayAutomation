package app.pages;


import org.testng.Assert;

import core.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utils.Locator;
import utils.TestNGUtils;
import utils.Locator.Type;

public class ProductDetailsPage extends BasePage{
	AndroidDriver<MobileElement> driver;
	public static Locator PDP_PRODUCT_NAME=new Locator("Product name in PDP", "com.ebay.mobile:id/textview_item_name", Type.ID);
	public static Locator PDP_PRODUCT_CARD_PRICE=new Locator("Product price in PDP", "com.ebay.mobile:id/textview_item_price", Type.ID);
	public static Locator PDP_PRODUCT_BUY_NOW_BUTTON=new Locator("Product Buy Now Button", "com.ebay.mobile:id/button_bin", Type.ID);
	public static Locator PDP_QUANTITY=new Locator("Product Quantity", "android:id/numberpicker_input", Type.ID);
	public static Locator PDP_QUANTITY_2=new Locator("Product Quantity", "//android.widget.Button[@text='2']", Type.XPATH);
	public static Locator PDP_REVIEW=new Locator("Product Quantity", "com.ebay.mobile:id/take_act", Type.ID);

	public ProductDetailsPage(AndroidDriver<MobileElement> driver) {
		this.driver=driver;

	}


	public ProductDetailsPage verifyProductDetailsInProductPage() throws Exception {

		TestNGUtils.reportLog("Verifying whether product name and price matches with that of search results page");
		getAction().findElement(PDP_PRODUCT_NAME);
		String nameInPdp=getAction().getText(PDP_PRODUCT_NAME);
		String nameInSrp=(String) getAction().retrieveKeyValue("productName");
		Assert.assertEquals(nameInPdp, nameInSrp, "Product Name mismatch between PDP and SRP.Product name in PDP:"+nameInPdp+" Product name in SRP:"+nameInSrp);
		String priceInPdp=getAction().getText(PDP_PRODUCT_CARD_PRICE).replaceAll(",", "").replaceAll("Rs.", "");
		String priceInSrp=(String) getAction().retrieveKeyValue("productPrice");
		Assert.assertEquals( Long.parseLong(priceInPdp),Long.parseLong(priceInSrp), "Product Price mismatch between PDP and SRP.Product price in PDP:"+priceInPdp+" Product price in SRP:"+priceInSrp);


		return this;
	}
	public ProductDetailsPage addProductToCart() throws Exception {
		TestNGUtils.reportLog("Add the product to cart");
		getAction().findElement(PDP_PRODUCT_BUY_NOW_BUTTON);
		getAction().click(PDP_PRODUCT_BUY_NOW_BUTTON);
		getAction().waitFor(2000);
		return this;
	}
	public ProductDetailsPage addQuantity() throws Exception {
		TestNGUtils.reportLog("Choose default quantity");
		getAction().findElement(PDP_QUANTITY);
		getAction().click(PDP_QUANTITY);
		getAction().rotateTo("landscape");
		getAction().rotateTo("portrait");
		getAction().pressEnter();		
		getAction().waitFor(2000);
		return this;
	}
	public ProductDetailsPage clickOnReviewButton() throws Exception {
		TestNGUtils.reportLog("Click on review button");
		getAction().findElement(PDP_REVIEW);
		getAction().click(PDP_REVIEW);		
		getAction().waitFor(10000);
		return this;
	}
	
}
