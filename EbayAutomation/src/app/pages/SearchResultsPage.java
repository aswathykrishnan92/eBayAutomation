package app.pages;

import java.util.Random;

import core.BasePage;
import utils.Locator;
import utils.Locator.Type;
import utils.TestNGUtils;

public class SearchResultsPage extends BasePage {

	public static Locator SRP_PRODUCT_CARD=new Locator("Product card", "//android.widget.RelativeLayout[@index='{0}']", Type.XPATH);
	public static Locator SRP_PRODUCT_CARD_TITLE=new Locator("Product card name", "//android.widget.RelativeLayout[@index='{0}']/android.widget.RelativeLayout/android.widget.TextView", Type.XPATH);
	public static Locator SRP_PRODUCT_CARD_PRICE=new Locator("Product card price", "(//android.widget.TextView[@resource-id='com.ebay.mobile:id/textview_item_price'])[{0}]", Type.XPATH);

	public SearchResultsPage() {

	}
	
	/**
	 * @author Aswathy_Krishnan
	 * Description: Click on a Random product in SRP after scrolling
	 * @return
	 * @throws Exception
	 */
	public SearchResultsPage clickOnRandomProduct() throws Exception {
		Random ran = new Random();
		int x = ran.nextInt(2);
		x=x+1;
		TestNGUtils.reportLog("Clicking on a random product at position:"+x);
		getAction().scrollForN(2);
		getAction().findElement(SRP_PRODUCT_CARD_TITLE.format(String.valueOf(x)));
		String productName=getAction().getText(SRP_PRODUCT_CARD_TITLE.format(String.valueOf(x)));
		getAction().findElement(SRP_PRODUCT_CARD_PRICE.format(String.valueOf(x+1)));
		String productPrice=getAction().getText(SRP_PRODUCT_CARD_PRICE.format(String.valueOf(x+1))).replaceAll(",", "").replaceAll("Rs.", "");
		getAction().storeKeyValue("productName", productName);
		getAction().storeKeyValue("productPrice", productPrice);
		getAction().click(SRP_PRODUCT_CARD_TITLE.format(String.valueOf((x))));
		getAction().waitFor(3000);
	
		return this;
		
		
	}
	
	
}
