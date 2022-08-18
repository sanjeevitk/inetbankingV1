package com.inetBanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

	public class LoginPage {
		WebDriver ldriver;
		
	public LoginPage(WebDriver rdriver)
		{
			ldriver=rdriver;
			PageFactory.initElements(rdriver,this);
		}
	
@FindBy(name="uid")
WebElement txtUserName;

@FindBy(name="password")
WebElement txtPassword;

@FindBy(name="btnLogin")
WebElement btnLogin;

@FindBy(xpath="/html/body/div[3]/div/u1/li[15]/a")
WebElement lnkLogout;


public void setUserName(String uname)
{
	txtUserName.sendKeys(uname);
}

public void setPassword(String pwd)
{
	txtPassword.sendKeys(pwd);
}


public void ClickSubmit()
{
	btnLogin.click();
}

public void ClickLogout()
{
	lnkLogout.click();
}
}