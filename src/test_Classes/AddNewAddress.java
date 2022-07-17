package test_Classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base_Classes.BaseClass;
import pomClasses.LoginPage;



public class AddNewAddress {
	
	WebDriver driver;
	LoginPage lp;
    pomClasses.HomePage hp;
	pomClasses.ProfilePage pp;
	
	
	//@Parameters("browser")
	@BeforeClass
	
	public void beforeclass() 
	{
		driver = BaseClass.getWebDriver();
	}

	
	@BeforeMethod
	
	public void beforemethod()
	{
		lp = new LoginPage(driver);
		hp = new pomClasses.HomePage(driver);
		pp = new pomClasses.ProfilePage(driver);
	}
	
	@Test
	
	public void VerifyUserCanLogIn() throws InterruptedException
	{
		lp.EnterEmailID();
		Reporter.log("User Entered EmailID");
		lp.EnterPassword();
		Reporter.log("User Entered PassWord");
		lp.ClickOnSubmitButton();
		Reporter.log("User Click on Submit Button");
		hp.hoverToProfileName();
		
		String text = hp.GetLogOutText();
		
			if(text.equals("Logout"))
			{
				System.out.println("TestPassed");
			}
		else
		{
			Assert.fail();
		}
		
	}
	
	
	@DataProvider(name="dataset")
	public String[][] dataToTest()
	{
		
	String [][] data = {{"Mahesh","9856412589","411041","Pune City"}, {"Suverna","9856415889","413512","Latur City"}};
	
	return data;
	
	}
	
	@Test(priority=1, dataProvider="dataset")
	
	public void UserCanAddNewAddress(String name,String mobnumber,String pincode,String locality) throws InterruptedException, EncryptedDocumentException, IOException
	{
		
		hp.MovePointer();
		hp.hoverToProfileName();
		hp.ClickOnMyProfileText();
		pp.ClickOnManageAddress();
		pp.ClickOnAddNewAddress();
		
		List<String> datalist = new ArrayList<>();
		datalist.add(name);
		datalist.add(mobnumber);
		datalist.add(pincode);
		datalist.add(locality);
		
		pp.GetDataForAddress(datalist);
		pp.AdressField();
		pp.ClickOnRadio();
		//pp.ClickOnSave();
	}
	
	@AfterMethod
	public void afterMethod()
	{
		System.out.println("After Method");
	}
	
	@AfterClass
	public void afterClass()
	{
		//driver.quit();
	}

}
