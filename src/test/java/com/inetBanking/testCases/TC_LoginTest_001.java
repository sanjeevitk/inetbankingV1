package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

// import org.testng.Assert;


import com.inetBanking.pageObjects.LoginPage;


public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() throws IOException
	{
		driver.get(baseURL);
		logger.info("URL is opened");
		LoginPage lp= new LoginPage(driver);
		lp.setUserName(username);
		logger.info("entered u name");
		lp.setPassword(password);
		logger.info("entered pwd");
		//Assert.assertTrue(false);
		lp.ClickSubmit();	
		logger.info("URL is opened");

		/*
		if(driver.getTitle().equals("https://demo.guru99.com/v4/index.p454"))
		{
			Assert.assertTrue(true);
			logger.info("login passed");


		}
		else
		{
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("login failed");
		}
*/
	}



}
