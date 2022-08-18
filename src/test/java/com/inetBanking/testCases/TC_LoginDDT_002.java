package com.inetBanking.testCases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {
	@Test(dataProvider="LoginData")
	public void LoginDDT(String user,String pwd)
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		LoginPage lp= new LoginPage(driver);
		lp.setUserName(user);
		logger.info("uname given");		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		lp.setPassword(pwd);
		logger.info("pwd given");


		lp.ClickSubmit();
		if (isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
			lp.ClickLogout();
			driver.switchTo().alert().accept();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.switchTo().defaultContent();
		}

	}
	public boolean isAlertPresent()
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e)
		{

			return false;
		}

	}
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException
	{
		String Path = System.getProperty("user.dir")+"/src/test/java/com/inetBanking/testData/LoginData.xlsx";
		int rownum= XLUtils.getRowCount(Path, "Sheet1");
		int cocount= XLUtils.getCellCount(Path, "Sheet1", 1);
		String logindata[][]= new String[rownum][cocount];
		for (int i=1;i<=rownum;i++)
		{
			for(int j=0;j<cocount;j++)
			{
				logindata[i][j]=XLUtils.getCellData(Path, "Sheet1", i, j);
			}
		}
		return logindata;

	}
}
