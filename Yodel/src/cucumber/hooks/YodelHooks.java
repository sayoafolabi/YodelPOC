package cucumber.hooks;

import com.common.helper.Helper;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class YodelHooks extends Helper{
	
	@Before
	public void setUp() throws Exception
	{
		launchBrowser("Chrome");
	}
	
	@After
	public void tearDown() throws Exception
	{
		closeBrowser();
	}

}
