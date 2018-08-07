package website.tests;
import org.testng.annotations.Test;

import utils.TestInitializationUtils;
public class EbayTest {
	
	@Test(groups= {"searchProduct"})
	public void searchProduct() {
		TestInitializationUtils utils=new TestInitializationUtils();
		utils.getDriverForWeb();
		
	}

}
