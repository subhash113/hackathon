package requirement2;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileNotFoundException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class write {

	
		
	    public static void writeIntoExcel(WebDriver driver) {
		 
	    int tablerows=webpageDriver.rowno;
	    String out[]=new String[(tablerows+1)*6];
	    int k=0;
		 File file=new File(".\\src/test/resources\\output.xlsx");
		 //WebDriver driver=webpageDriver.driver;
		 
		 XSSFWorkbook wb=new XSSFWorkbook();
		 

		 XSSFSheet sh =wb.createSheet("output-data");
		 
		
		 
		 XSSFRow row = sh.createRow(0);
		 
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 for(int i=0;i<7;i++) {
			 
			 XSSFCell header = row.createCell(i);
			 header.setCellValue(driver.findElement(By.xpath("//*[@id=\"paymentschedule\"]/table/tbody/tr[1]/th["+(i+1)+"]")).getText());
		 }
		 
		 
		 
		 
		 if(!read.data[7].split(" ")[1].equalsIgnoreCase("Jan")) {
			 tablerows++;
		 }
		 
		 
		 for(int i=1;i<=tablerows;i++) {
			 for(int j=2;j<8;j++) {
				 	WebDriverWait wait = new WebDriverWait(driver, 20);
				 	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"paymentschedule\"]/table/tbody/tr["+2*i+"]/td["+j+"]")));
				 
				    int attempts = 0;
				    while(attempts < 10) {
				        try {
				        	driver.findElement(By.xpath("//*[@id=\"paymentschedule\"]/table/tbody/tr["+2*i+"]/td["+(j)+"]")).click();
				            break;
				        } catch(Exception e) {
				        }
				        attempts++;
				    }
				 	 out[k++]=driver.findElement(By.xpath("//*[@id=\"paymentschedule\"]/table/tbody/tr["+2*i+"]/td["+(j)+"]")).getText();
				 	 
				     
			 }
		 }
		
		k=0;
		int year=Integer.parseInt(read.data[7].split(" ")[0]);
		for(int i=1;i<=tablerows;i++) {
			XSSFRow row2=sh.createRow(i);
			 for(int j=0;j<7;j++) {
				 if(j==0) {
					 row2.createCell(j).setCellValue(year++);
				 }
				 else {
				 	row2.createCell(j).setCellValue(out[k++]);
				 }
			 }
		}
		
		 
		   
		    
		 FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 try {
			wb.write(fos);
			;
		} catch (IOException e) {
			e.printStackTrace();
		}
		           
		 try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 try {
			 fos.flush();
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    }
}
