package requirement1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class CarLoanCalculator {

		
	/*global variables*/
	private static WebDriver driver;
	private static String baseUrl;   
	private static String chromeDriverPath;
	private static String firefoxDriverPath;
	private static String browser;
	private static Properties prop;
	private static FileInputStream file;
	
	private static String amount;
	private static String rate;
	private static String tenure;
	
	@Test(priority=1)
	public static void getPrerequisite() {
		chromeDriverPath=System.getProperty("user.dir")+"\\src/test/resources\\drivers\\chromedriver.exe";//getting Chrome driver path dynamically
		firefoxDriverPath=System.getProperty("user.dir")+"\\src/test/resources\\drivers\\geckodriver.exe";//getting Firefox driver path dynamically
		
		/*reading properties file for input*/
		try {
			file = new FileInputStream(System.getProperty("user.dir")+"\\src/test/resources\\setup.properties");
			
			
		}
		catch (FileNotFoundException e) {
			
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
        amount=prop.getProperty("Amount");
        rate=prop.getProperty("Rate");
		tenure=prop.getProperty("Tenure");
		
	}
	
	/*setting-up properties and opening browser*/
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
	
	
	/*navigating to URL*/
	@Test(priority=3)
	public static void navigateUrl(){
		 
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}
	
	@Test(priority=4)
	public static void enterData() {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"car-loan\"]/a")).click();
		
		driver.findElement(By.xpath("//*[@id=\"loanamount\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"loanamount\"]")).sendKeys(amount);
		
		driver.findElement(By.xpath("//*[@id=\"loaninterest\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"loaninterest\"]")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//*[@id=\"loaninterest\"]")).sendKeys(rate);
		
		
		driver.findElement(By.xpath("//*[@id=\"emicalculatorinnerform\"]/div[7]/div/div/div/div/div/label[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"loanterm\"]")).sendKeys(Keys.BACK_SPACE);;
		driver.findElement(By.xpath("//*[@id=\"loanterm\"]")).sendKeys(tenure);
		driver.findElement(By.xpath("//*[@id=\"emicalculatorinnerform\"]/div[7]/div/div/div/div/div/label[2]")).click();
	}
	
	@Test(priority=5)
	private static void retriveOutput() {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String emi=driver.findElement(By.xpath("//*[@id=\"emiamount\"]/p/span")).getText();
		String interest=driver.findElement(By.xpath("//*[@id=\"emitotalinterest\"]/p/span")).getText();
		
		System.out.println("------Requirement 1------");
		System.out.println("Interest amount is : "+interest);
		System.out.println("Monthly EMI amount : "+emi);
		System.out.println("\n\n\n");
	}
	
	
	/*closing driver*/
	@Test(priority=6)
	public static void closeDriver() {
		driver.quit();
	}
	
	
	
}
