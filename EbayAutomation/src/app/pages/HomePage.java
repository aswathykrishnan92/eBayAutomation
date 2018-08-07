package app.pages;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import core.BasePage;
import utils.Locator;
import utils.TestNGUtils;

public class HomePage extends BasePage {

	WebDriver driver;

	public Locator SEARCH_BAR=new Locator("SEARCH BAR", "com.ebay.mobile:id/search_box",Locator.Type.ID );
	public Locator SEARCH_BAR_ENTRY=new Locator("SEARCH BAR ENTRY", "com.ebay.mobile:id/search_src_text",Locator.Type.ID );
	public Locator SIGN_IN_BUTTON=new Locator("Sign In Button", "com.ebay.mobile:id/button_sign_in",Locator.Type.ID );
	public Locator USERNAME=new Locator("username", "com.ebay.mobile:id/edit_text_username",Locator.Type.ID );
	public Locator PASSWORD=new Locator("password", "com.ebay.mobile:id/edit_text_password",Locator.Type.ID );
	public Locator SIGN_IN_BUTTON_ACCEPT=new Locator("Sign In Button Accept", "com.ebay.mobile:id/button_sign_in",Locator.Type.ID );
	public Locator MENU_BUTTON=new Locator("Menu Button", "com.ebay.mobile:id/home",Locator.Type.ID );
	public Locator SETTINGS=new Locator("Setting button", "com.ebay.mobile:id/menuitem_settings",Locator.Type.ID );
	public Locator USER=new Locator("User", "com.ebay.mobile:id/textview_sign_in_status",Locator.Type.ID );
	public Locator SIGN_OUT=new Locator("Sign Out Button", "//android.widget.TextView[@text='Sign out']",Locator.Type.XPATH );
	public Locator SIGN_OUT_PROFILE=new Locator("Sign Out Button Profile", "com.ebay.mobile:id/ebay_promo_image",Locator.Type.ID );
	public Locator SIGN_OUT_OK=new Locator("Sign Out OK", "android:id/button1",Locator.Type.ID );

	public HomePage() {
	}

	public HomePage searchItem(String sku) throws Exception {
		TestNGUtils.reportLog("Search Item:"+sku);
		getAction().waitFor(2000);
		getAction().findElementByID(SEARCH_BAR);
		getAction().click(SEARCH_BAR);
		getAction().findElementByID(SEARCH_BAR_ENTRY);
		getAction().sendText(SEARCH_BAR_ENTRY, sku);
		getAction().pressEnter();
		getAction().waitFor(8000);


		return this;
	}
	public HomePage signIn(String username,String password) throws Exception {

		TestNGUtils.reportLog("Entering the login credentials: Username-"+username+" Password-"+password);
		getAction().storeKeyValue("signedIn", false);
		getAction().waitFor(11000);
		getAction().findElement(SIGN_IN_BUTTON);
		getAction().click(SIGN_IN_BUTTON);
		getAction().waitFor(5000);
		getAction().findElement(USERNAME);
		getAction().sendText(USERNAME, username);
		getAction().findElementByID(PASSWORD);
		getAction().click(PASSWORD);
		getAction().sendText(PASSWORD, password);
		getAction().click(SIGN_IN_BUTTON_ACCEPT);
		getAction().waitFor(11000);


		Assert.assertTrue(!getAction().isElementVisible(SIGN_IN_BUTTON_ACCEPT), "Sign in button still visible. Sign In unsuccessful");
		getAction().storeKeyValue("signedIn", true);
		return this;
	}
	public HomePage signOut() {

		if((boolean) getAction().retrieveKeyValue("signedIn")) {
		TestNGUtils.reportLog("User Sign out");
		getAction().waitFor(5000);
		getAction().findElement(MENU_BUTTON);
		getAction().click(MENU_BUTTON);
		if(!getAction().isElementVisible(USER)) {
			getAction().click(MENU_BUTTON);

		}
		getAction().waitFor(3000);	
		getAction().scrollTo(SETTINGS);
		getAction().click(SETTINGS);
		if(getAction().isElementVisible(SIGN_OUT)) {
			getAction().findElement(SIGN_OUT);
			getAction().click(SIGN_OUT);
			getAction().findElement(SIGN_OUT_OK);
			getAction().click(SIGN_OUT_OK);
			getAction().waitFor(3000);

			Assert.assertTrue(getAction().isElementVisible(SIGN_IN_BUTTON), "Sign Out is unsuccessful;");
		}
		getAction().storeKeyValue("signedIn", false);
		}

		return this;
	}


}
