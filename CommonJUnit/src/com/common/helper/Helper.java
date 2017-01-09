package com.common.helper;

import static org.junit.Assume.assumeTrue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import com.google.common.base.Predicate;

public class Helper 
{
	private static ChromeDriverService service;
	private static PhantomJSDriverService pService;
	private static WebDriver driver;
	private static DesiredCapabilities dCapabilities;
	private static Actions action;
	private static WebDriverWait wait;
	
	
	static
	{
		driver = null;
		action = null;
		wait = null;
		dCapabilities = null;
	}
	
	public static void deleteAllCookies()
	{
		driver.manage().deleteAllCookies();
	}
	
	public static String getpageSource() throws Exception 
	{
		return "Page Source is: " + driver.getPageSource();
	}
	
	
	public static String getpageTitle() throws Exception 
	{
		return "Title is: " + driver.getTitle();
	}
	
	public static void enterKey() throws Exception
	{
		driver.switchTo().defaultContent();
		driver.switchTo().activeElement().sendKeys(Keys.RETURN);

	}
	
	public static String getDateAsString(String format)
	{
		Calendar date = new GregorianCalendar();
		
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateAsString = formatter.format(date.getTime());
		
		return dateAsString;
	}
	

	public static void acceptAlert() throws Exception
	{
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	
	public static void scrollOnFrameOnDeviceSelectionScreen(WebElement element) throws Exception
	{
		action = new Actions(driver);		
	
		action.moveToElement(element).clickAndHold().moveByOffset(0, 200).release().perform();
		Thread.sleep(1000);

	}
	
	public static void scrollFrameToAnElement(WebElement element, int offset) throws Exception
	{
		action = new Actions(driver);	
		
		
		action.moveToElement(element).clickAndHold().moveByOffset(0, offset).release().perform();
		Thread.sleep(1000);
	}
	
	public static void scrollOnFrameForNewOrgButton(WebElement element) throws Exception
	{
		action = new Actions(driver);	
		
			
		action.moveToElement(element).clickAndHold().moveByOffset(0, 3000).release().perform();
		Thread.sleep(1000);

	}
	public static WebElement returnJQueryElement(String element) throws Exception
	{
		WebElement jElement = (WebElement) ((JavascriptExecutor) driver).executeScript(element);
		Thread.sleep(500);
		return jElement;
	}
	
	public static List<WebElement> returnJQueryElements(String element) throws Exception
	{
		@SuppressWarnings("unchecked")
		List<WebElement> jElement = (List<WebElement>) ((JavascriptExecutor) driver).executeScript(element);
		Thread.sleep(500);
		return jElement;
	}
	
	public static void takeScreenshot() throws Exception
	{
		driver = new Augmenter().augment(driver);
		String timeNow = new SimpleDateFormat("hh:mm:ss").format(new GregorianCalendar().getTime());
		
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        System.out.println("File:" + srcFile);
        FileUtils.copyFile(srcFile, new File("./Screenshot/screenshot" + timeNow + ".png"));
	}
	public static void moveToAnElement(WebElement element) throws Exception
	{
		action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	
	public static void doubleClickAnElement(WebElement element) throws Exception
	{
		action = new Actions(driver);
		action.doubleClick(element);
	}
	
	public static void clickOnAnElement(WebElement element) throws Exception
	{
		action = new Actions(driver);
		action.click(element).build().perform();
	}
	
	public static void dragToAnElement(WebElement source, WebElement target) throws Exception
	{
		action = new Actions(driver);
		action.dragAndDrop(source, target).perform();
	}
	public static void dragToAnElementByOffset(WebElement source, int xOffset, int yOffset) throws Exception
	{
		action = new Actions(driver);
		action.dragAndDropBy(source, xOffset, yOffset).perform();
	}
	public static WebElement getElementById(String id) throws Exception
	{
		return getElementById(id, true);
	}
	
	public static WebDriverWait waitForElement() throws Exception
	{
		wait = new WebDriverWait(driver, 30);
		return wait;
	}
	
	public static WebElement getElementById(String id, boolean wait) throws Exception
	{
		By locator = By.id(id);
		return getElement(locator, wait);
	}
	
	public static WebElement getElementByName(String name) throws Exception
	{
		By locator = By.name(name);
		return getElement(locator, true);
	}
	
	public static WebElement getElementByLinkText(String linkText) throws Exception
	{
		return getElementByLinkText(linkText, true);
	}
	
	public static WebElement getElementByLinkText(String linkText, boolean wait) throws Exception
	{
		By locator = By.linkText(linkText);
		return getElement(locator, wait);
	}
	
	public static WebElement getElementByXpath(String xpath) throws Exception
	{
		By locator = By.xpath(xpath);
		return getElement(locator, true);
	}
	
	public static WebElement getElementByCssSelector(String selector, boolean wait) throws Exception
	{
		By locator = By.cssSelector(selector);
		return getElement(locator, true);
	}
	
	public static WebElement getElementByXpath(String xpath, int timeOut) throws Exception
	{
		By locator = By.xpath(xpath);
		return getElement(locator, true, timeOut);
	}
	
	public static WebElement getElementByXpath(String xpath, boolean wait) throws Exception
	{
		By locator = By.xpath(xpath);
		return getElement(locator, wait);
	}
	
	public static WebElement getElementByCssSelector(String selector) throws Exception
	{
		By locator = By.cssSelector(selector);
		return getElement(locator, true);
	}
	
	public static List<WebElement> getElementsByCssSelector(String selector) throws Exception
	{
		By locator = By.cssSelector(selector);
		return getElements(locator, false);
	}
	
	public static WebElement getElementByClassName(String className) throws Exception
	{
		By locator = By.className(className);
		return getElement(locator, true);
	}
	
	public static List<WebElement> getElementsByClassName(String className) throws Exception
	{
		By locator = By.className(className);
		return getElements(locator, false);
	}
	
	public static List<WebElement> getElementsById(String id) throws Exception
	{
		By locator = By.id(id);
		return getElements(locator, false);
	}
	
	public static List<WebElement> getElementsByTagName(String tagName) throws Exception
	{
		By locator = By.tagName(tagName);
		return getElements(locator, false);
	}
	
	public static List<WebElement> getElementsByXpath(String xpath) throws Exception
	{
		By locator = By.xpath(xpath);
		return getElements(locator, false);
	}
	
	private static WebElement getElement(By locator, boolean wait) throws Exception
	{
		return getElement(locator, wait, getTimeoutSeconds());
	}
	
	
	public static void saveScreenShot()
	{
				
		String fileName = String.format(".\\Screenshot\\%s\\failure_Evidence_%s.png", getDateAsString("yyyyMMdd"), 
																		getDateAsString("HHmmss"));
		
		// Take screenshot & save to location above...
		File screenShot = takeScreenShot();
		
		try
		{
			FileUtils.copyFile(screenShot, new File(fileName));	
		}
		catch (IOException e)
		{
			// do nothing... screenshot not critical
		}
	}
	
	private static WebElement getElement(By locator, boolean wait, int timeOutSeconds) throws Exception
	{
		WebElement element = null;
		int tryCount = 0;
		
		if (wait)
		{
			waitForElement(locator, timeOutSeconds);
		}
		
		while (element == null)
		{
			try
			{
				element = driver.findElement(locator);
			}
			catch (Exception e)
			{
				if (tryCount == 3)
				{
					saveScreenShot();
					throw e;
				}
				
				// Wait a few seconds before retry
				Thread.sleep(2000);
				
				tryCount++;
			}
		}
		
		return element;
	}
	
	private static List<WebElement> getElements(By locator, boolean wait) throws Exception
	{
		List<WebElement> elements = null;
		int tryCount = 0;
		
		if (wait)
		{
			waitForElement(locator, getTimeoutSeconds());
		}
		
		while (elements == null)
		{
			try
			{
				elements = driver.findElements(locator);
			}
			catch (Exception e)
			{
				if (tryCount == 3)
				{
					saveScreenShot();
					throw e;
				}
				
				// Wait a few seconds before retry
				Thread.sleep(2000);
				
				tryCount++;
			}
		}
		
		return elements; 
	}
	
	private static int getTimeoutSeconds()
	{
		return 30;
	}
	
	public static void launchBrowser(String browser) throws Exception
	{
		//driver = null;
				
		switch(browser)
		{
			case "Chrome" :
				driver = initialiseChromeDriver();
				break;
				
			case "Firefox" : 
				driver = initialiseIFirefoxDriver();
				break;
				
			case "Internet Explorer" :
				driver = initialiseIExplorerDriver();
				break;
				
			case "Safari" :
				driver = initialiseISafariDriver();
				break;
				
			case "Phantom" :
				driver = initialiseIPhantomJS();
				break;
				
			case "HtmlUnit" :
				driver = initialiseIHtmlUnitDriver();
				break;
				
			// Add additional browsers here
			default :
				throw new Exception(String.format("WebDriver for %s is not configured", browser));
		}
		
		driver.manage().window().maximize();
	}
	
	 private static boolean isSupportedPlatform() 
	 {
		    Platform current = Platform.getCurrent();
		    return Platform.MAC.is(current) || Platform.WINDOWS.is(current);
	 }
	
	public static void closeBrowser()
	{
		driver.quit();
		stopService();
	}
	
	public static void stopService() 
	{
						
		if(browserName().equalsIgnoreCase("Chrome"))
		{			
			service.stop();
			
		} else if(browserName().equalsIgnoreCase("Phantom"))
		{
			pService.stop();
			
		}else if(!browserName().equalsIgnoreCase("Chrome"))
		{
			//do nothing
		}
	}
	
	private static String browserName()
	{
		return ((RemoteWebDriver)driver).getCapabilities().getBrowserName();
	}

	public static void stopAlert() throws Exception 
	{
				
		if(browserName().equalsIgnoreCase("Firefox"))
		{			
			acceptAlert();
			
		} else if(!browserName().equalsIgnoreCase("Firefox"))
		{
			//do nothing
		}
	}
	
	public static void proceedWhenCertificationFails() throws Exception
	{
		if(browserName().equalsIgnoreCase("PhantomJS") || browserName().equalsIgnoreCase("Safari"))
		{
			launchUrl(new StoredValues().get("CountryToLaunch"));	
			
		} else if(!(browserName().equalsIgnoreCase("PhantomJS") || browserName().equalsIgnoreCase("Safari")))
		{
			//do nothing
		}
	}
	
		
	public static void launchUrl(String url)
	{
		driver.navigate().to(url);
	}

	private static WebDriver initialiseChromeDriver() throws IOException
	{
		service = new ChromeDriverService.Builder()
			.usingDriverExecutable(new File("browsers\\Chrome\\chromedriver.exe"))
			.usingAnyFreePort()
			.build();
		service.start();
		
		driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());		
		
		return driver;
	}

	private static WebDriver initialiseIExplorerDriver() throws Exception
	{
		File file = new File("browsers\\IEDriverServer\\IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		
		
		dCapabilities = DesiredCapabilities.internetExplorer();
		dCapabilities.setCapability("ignoreZoomSetting", true);
		dCapabilities.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
		dCapabilities.setCapability(CapabilityType.VERSION, "10");
		dCapabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
		
		driver = new InternetExplorerDriver(dCapabilities);		
		
		return driver;
		
	}
	
	private static WebDriver initialiseIFirefoxDriver() throws Exception
	{	
		driver = new FirefoxDriver();
		return driver;
	}
	
	private static WebDriver initialiseIHtmlUnitDriver() throws Exception
	{	
		String PROXY = "localhost:4444";
		
		Proxy proxy = new Proxy();
		proxy.setAutodetect(false); 
		proxy.setHttpProxy(PROXY)
			.setFtpProxy(PROXY)
			.setSslProxy(PROXY);
		
		dCapabilities = new DesiredCapabilities();
		dCapabilities.setCapability(CapabilityType.PROXY, proxy);
		dCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		dCapabilities.setBrowserName("Chrome");
		dCapabilities.setJavascriptEnabled(true);		
		
		driver = new HtmlUnitDriver();
		
		return driver;

	}
	
	private static WebDriver initialiseIPhantomJS() throws IOException
	{	
		String PROXY = "localhost:4444";
		Proxy proxy = new Proxy();
		proxy.setAutodetect(false); 
		proxy.setHttpProxy(PROXY)
			.setFtpProxy(PROXY)
			.setSslProxy(PROXY);
		
		dCapabilities = new DesiredCapabilities();
		dCapabilities.setJavascriptEnabled(true);
		dCapabilities.setBrowserName("Firefox");
		dCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		dCapabilities.setCapability("takesScreenshot", true);
		dCapabilities.setCapability(CapabilityType.PROXY, proxy);

		
		pService = new PhantomJSDriverService.Builder()
						        .usingPhantomJSExecutable(new File("browsers\\Headless\\phantomjs.exe"))
						        .usingPort(4444)
						        .usingCommandLineArguments(new String[] {"--web-security=no", "--ignore-ssl-errors=yes", 
						        				"--webdriver=4444", "--ssl-protocol=any"})
						        .usingGhostDriverCommandLineArguments(new String[] {"--webdriver=4444"})
						        .build();
		
		pService.start();
	
		driver =  new PhantomJSDriver(pService, dCapabilities);
		
		return driver;
		
	}
	
	private static File takeScreenShot()
	{
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	}
	
	private static WebDriver initialiseISafariDriver() throws IOException
	{
		assumeTrue(isSupportedPlatform());		
		return new SafariDriver();
	}
	
	private static void waitForElement(final By locator, int timeOutSeconds)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
			wait.until(new Predicate<WebDriver>()
			{
				@Override
				public boolean apply(WebDriver driver)
				{
					 return driver.findElement(locator) != null &&
							driver.findElement(locator).isDisplayed() &&
							driver.findElement(locator).isEnabled();
				}
			});
		}
		catch(TimeoutException e)
		{
			saveScreenShot();
			throw e;
		}
	}
	
}
