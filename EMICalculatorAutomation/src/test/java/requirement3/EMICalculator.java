package requirement3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.openqa.selenium.TakesScreenshot;


public class EMICalculator {
	
	public static WebDriver  driver;
	public static String baseUrl;

	public static WebElement slider1;
	public static WebElement slider2;
	public static WebElement slider3;
	public static WebElement slider4;
	public static WebElement slider5;
	public static WebElement slider6;
	public static WebElement slider7;
	public static WebElement slider8;
	public static WebElement slider9;
	public static WebElement slider10;
	public static WebElement slider11;
	public static WebElement slider12;
	
		
	
	@Test(priority=1)
	public void loadDriver() throws Exception  {
		Properties props = new Properties();
		
		String driverkey = "";
		String driverval = "";
		String browser;
		
		InputStream readFile=null;
		
		try{
			File file=new File(".\\src/test/resources\\setup.properties");
			readFile= new FileInputStream(file);
			
			props.load(readFile);
			 browser=props.getProperty("Browser");
			// driverkey = props.getProperty("driverKey");
			 if(browser.equalsIgnoreCase("firefox")) {
				 driverval = props.getProperty("firefoxDriverPath");
			 	 driverkey ="webdriver.gecko.driver";
			 }
			 else {
				 driverval = props.getProperty("chromeDriverPath");
				 driverkey ="webdriver.chrome.driver";
			 }
			
			baseUrl = props.getProperty("Website");
			

			System.setProperty(driverkey, driverval);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		if(driverval.contains("chrome")) {
			ChromeOptions co= new ChromeOptions();
			
	        co.addArguments("--disable-notifications");
			
			
			driver = new ChromeDriver(co);
		}
		else if( driverval.contains("gecko") ) {
			FirefoxOptions fo = new FirefoxOptions();
			
			fo.addPreference("dom.webnotifications.enabled", false);
			
			driver = new FirefoxDriver();
			
		}
		
		else {
			throw new Exception("Web Driver not supported");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	//URL
	@Test(priority=2)
	private void getURL() {
		driver.get(baseUrl);
	}
	
	@Test(priority=3)
	private void LoanCalculator() {
		driver.findElement(By.xpath("//*[@id='menu-item-2696']")).click();// click on "calculators"
		driver.findElement(By.xpath("//*[@id='menu-item-2423']")).click(); // click on "loan calculators"
		
		
		driver.navigate().to("https://emicalculator.net"); // navigating back to avoid the ad
		
		driver.findElement(By.xpath("//*[@id='menu-item-2696']")).click();
		driver.findElement(By.xpath("//*[@id='menu-item-2423']")).click();
		
	
		
		//EMI Calculator
		
		
		// checking Sliders and text boxes
		
		slider1=driver.findElement(By.xpath("//*[@id='loanamountslider']/span")); // checking loan slider
		Actions action= new Actions(driver);
		action.dragAndDropBy(slider1, 50, 0).build().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		
		
		slider2=driver.findElement(By.xpath("//*[@id='loaninterestslider']/span")); // checking  interest slider
		Actions action1= new Actions(driver);
		action1.dragAndDropBy(slider2, 10, 0).build().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		
		
		slider3=driver.findElement(By.xpath("//*[@id='loantermslider']/span")); // checking  tenure slider
		Actions action2= new Actions(driver);
		action2.dragAndDropBy(slider3, 10, 0).build().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		
		
		slider4=driver.findElement(By.xpath("//*[@id='loanfeesslider']/span")); // checking fees slider
		Actions action3= new Actions(driver);
		action3.dragAndDropBy(slider4, 20, 0).build().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		
		// scrolling down the page to locate the calendar element
		
		JavascriptExecutor jse= (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,1000)");
		
		driver.findElement(By.xpath("//*[@id='startmonthyear']")).click(); // inspecting calendar text box
		driver.findElement(By.cssSelector("span[class='month focused active']")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		
		
	}


    // changing Tenure scale
	
	   @Test(priority=4)
       public void ChangeOfScale() {
		
		driver.findElement(By.xpath("//*[@id=\"ltermwrapper\"]/div[1]/div/div/div/div/div/label[2]")).click(); // changing from year to month
		
		
	}
	
	// taking screenshot of change in scale
	   
	   @Test(priority=5)
	   public static void takeScreenshot() throws IOException {
		File file =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String fileName="screen";
		//FileUtils.
		FileUtils.copyFile(file, new File(".\\src\\test\\resources\\com\\screenshots\\"+fileName+".png"));
		System.out.println("-----Requirement 3-----");
		System.out.println("Screenshot is captured and stored");
	}
	
	
	// Loan Amount Calculator
	   
	   @Test(priority=6)
	   public void LoanAmountCalculator() {
		
		driver.findElement(By.xpath("//*[@id=\"loan-amount-calc\"]/a[1]")).click(); // clicking on Loan amount cal
		 
		// checking text boxes and scales
		 
		 slider5 = driver.findElement(By.xpath("//*[@id=\"loanemislider\"]/span"));
		 Actions drag = new Actions(driver);
		 drag.dragAndDropBy(slider5, 50, 0).build().perform();
		 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
		 
		 slider6 = driver.findElement(By.xpath("//*[@id=\"loaninterestslider\"]/span"));
		 Actions drag1 = new Actions(driver);
		 drag1.dragAndDropBy(slider6, 50, 0).build().perform();
		 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
		 
	     slider7 = driver.findElement(By.xpath("//*[@id=\"loantermslider\"]/span"));
		 Actions drag2 = new Actions(driver);
		 drag2.dragAndDropBy(slider7, 50, 0).build().perform();
		 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
		 
		 slider8 = driver.findElement(By.xpath("//*[@id=\"loanfeesslider\"]/span"));
		 Actions drag3 = new Actions(driver);
		 drag3.dragAndDropBy(slider8, 50, 0).build().perform();
		 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
		
}
	   
	    @Test(priority=7)
		public void ChangeOfScale2() {
			driver.findElement(By.xpath("//*[@id=\"ltermwrapper\"]/div[1]/div/div/div/div/div/label[2]")).click();
			WebElement slider4 = driver.findElement(By.xpath("//*[@id=\"loantermslider\"]/span"));
			 Actions move4 = new Actions(driver);
			 move4.dragAndDropBy(slider4, 50, 0).build().perform();
			
		
		}
		
		// Taking screenshot of change in scale
		
	    @Test(priority=7)
	    
		public static  void takeScreenshot1() throws IOException {
			File file =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			String fileName="screen1";
			//FileUtils.
			FileUtils.copyFile(file, new File(".\\src\\test\\resources\\com\\screenshots\\"+fileName+".png"));
			System.out.println("Screenshot is captured and stored");
		}
			
	
	// Loan tenure Calculator
	
	    @Test(priority=8)
	    public void LoanTenureCalculator() {
		
		driver.findElement(By.xpath("//*[@id=\"loan-tenure-calc\"]/a[1]")).click(); // click on loan tenure calculator
		
		
		// check text boxes and scales
		
		 slider9 = driver.findElement(By.xpath("//*[@id=\"loanamountslider\"]/span"));
		 Actions move = new Actions(driver);
		 move.dragAndDropBy(slider9, 50, 0).build().perform();
		 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
		 
		 slider10 = driver.findElement(By.xpath("//*[@id=\"loanemislider\"]/span"));
		 Actions move1 = new Actions(driver);
		 move1.dragAndDropBy(slider10, 50, 0).build().perform();
		 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
		 
		 slider11 = driver.findElement(By.xpath("//*[@id=\"loaninterestslider\"]/span"));
		 Actions move2 = new Actions(driver);
		 move2.dragAndDropBy(slider11, 50, 0).build().perform();
		 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
		 
		 slider12 = driver.findElement(By.xpath("//*[@id=\"loanfeesslider\"]/span"));
		 Actions move3 = new Actions(driver);
		 move3.dragAndDropBy(slider12, 50, 0).build().perform();
		 
		 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
	
	}	
	
	// Taking screenshot of the Page
	
	    @Test(priority=9)
	    public static void takeScreenshot3() throws IOException {
		File file =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		
		String fileName="screen3";
		//FileUtils.
		FileUtils.copyFile(file, new File(".\\src\\test\\resources\\com\\screenshots\\"+fileName+".png"));
		System.out.println("Screenshot is captured and stored");
	}
	
	  
	
	// CLOSE BROWSER
	    
	    @Test(priority=10)
	    public void quitDriver() {
		driver.close();
		System.out.println("Execution completed\n\nCheck screenshots in folder to varify results\n\n\n");
		
	}
	
	
	



}
