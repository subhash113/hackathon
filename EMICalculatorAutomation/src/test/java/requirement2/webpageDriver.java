package requirement2;

import java.io.FileInputStream;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class webpageDriver {

	/*global variables*/
	protected static WebDriver driver;
	private static String baseUrl;   
	private static String chromeDriverPath;
	private static String firefoxDriverPath;
	private static String browser;
	private static Properties prop;
	private static FileInputStream file;
	protected static int rowno;
	
	@Test(priority=1)
	public static void getPrerequisite() {
	
		/*reading properties file for setup*/
		try {
			file = new FileInputStream(System.getProperty("user.dir")+"\\src/test/resources\\setup.properties");
		} catch (FileNotFoundException e) {
			
			System.out.println("File is not found. Check file path.");
		}
        prop = new Properties();
        try {
			prop.load(file);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        
        browser=prop.getProperty("Browser");
        baseUrl=prop.getProperty("Website");
        chromeDriverPath=prop.getProperty("chromeDriverPath");
        firefoxDriverPath=prop.getProperty("firefoxeDriverPath");
	}
	
	
	@Test(priority=2)
	public static void  createWebDriver() {
		
		
		//firefox setup
		if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",firefoxDriverPath);
			driver = new FirefoxDriver();
		}
		//Chrome setup
		else {
			if(!browser.equalsIgnoreCase("chrome")) {
				System.out.println("chrome is selected by default\n");
			}
			System.setProperty("webdriver.chrome.driver",chromeDriverPath);
			driver = new ChromeDriver();
		}
	}
	
	@Test(priority=3)
	public static void navigateUrl(){
		 
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}
	
	@Test(priority=4)
	public static void navigateToHomeLoanPage() {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"menu-item-dropdown-2696\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"menu-item-3294\"]/a")).click();
		driver.navigate().back();
		driver.navigate().forward();
	}
	@Test(priority=5)
	public static void readExcelSheet() {
		read.readFromExcel();
	}
	
	@Test(priority=6)
	public static void insertValueToWebsite() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id=\"homeprice\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"homeprice\"]")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//*[@id=\"homeprice\"]")).sendKeys(read.data[0]);
		
		driver.findElement(By.xpath("//*[@id=\"downpayment\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"downpayment\"]")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//*[@id=\"downpayment\"]")).sendKeys(read.data[1]);
		
		driver.findElement(By.xpath("//*[@id=\"homeloaninsuranceamount\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"homeloaninsuranceamount\"]")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//*[@id=\"homeloaninsuranceamount\"]")).sendKeys(read.data[2]);
		
		driver.findElement(By.xpath("//*[@id=\"homeloanamount\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"homeloanamount\"]")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//*[@id=\"homeloanamount\"]")).sendKeys(read.data[3]);
		
		driver.findElement(By.xpath("//*[@id=\"homeloaninterest\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"homeloaninterest\"]")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//*[@id=\"homeloaninterest\"]")).sendKeys(read.data[4]);
		
		driver.findElement(By.xpath("//*[@id=\"homeloanterm\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"homeloanterm\"]")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//*[@id=\"homeloanterm\"]")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//*[@id=\"homeloanterm\"]")).sendKeys(read.data[5]);
		rowno=(int)Float.parseFloat(driver.findElement(By.xpath("//*[@id=\"homeloanterm\"]")).getAttribute("value"));
	
		driver.findElement(By.xpath("//*[@id=\"loanfees\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"loanfees\"]")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//*[@id=\"loanfees\"]")).sendKeys(read.data[6]);
		
		
		driver.findElement(By.xpath("//*[@id=\"startmonthyear\"]")).click();
		driver.findElement(By.xpath("//body/div[3]/div[2]/table/thead/tr[2]/th[2]")).click();
		driver.findElement(By.xpath("//*[contains(text(),"+read.data[7].split(" ")[0]+")]")).click();
		driver.findElement(By.xpath("//*[contains(text(),"+read.data[7].split(" ")[1]+")]")).click();
		
	
		driver.findElement(By.xpath("//*[@id=\"onetimeexpenses\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"onetimeexpenses\"]")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//*[@id=\"onetimeexpenses\"]")).sendKeys(read.data[8]);
		
		driver.findElement(By.xpath("//*[@id=\"propertytaxes\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"propertytaxes\"]")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//*[@id=\"propertytaxes\"]")).sendKeys(read.data[9]);
		
		driver.findElement(By.xpath("//*[@id=\"homeinsurance\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"homeinsurance\"]")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//*[@id=\"homeinsurance\"]")).sendKeys(read.data[10]);
		
		driver.findElement(By.xpath("//*[@id=\"maintenanceexpenses\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"maintenanceexpenses\"]")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//*[@id=\"maintenanceexpenses\"]")).sendKeys(read.data[11]);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1500)"); //Scroll vertically down by 1500 pixels	
		
	}
	
	@Test(priority=7)
	public static void writeExcel() {
		write.writeIntoExcel(driver);
	}
	
	/*closing driver*/
	@Test(priority=8)
	public static void closeDriver() {
		driver.close();
		System.out.println("----Requirement 2-----");
		System.out.println("Executed");
		System.out.println("Check Excel sheet for Output");
		System.out.println("\n\n\n");
	}
	
	
}
