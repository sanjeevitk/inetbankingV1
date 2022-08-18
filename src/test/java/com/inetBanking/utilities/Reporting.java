package com.inetBanking.utilities;

//Listener class used to generate Extent reports

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.reporters.DotTestListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.aventstack.extentreports.reporter.configuration;
import com.inetBanking.testCases.BaseClass;


public class Reporting extends BaseClass implements ITestListener
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger;


	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report-"+timeStamp+".html";

		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/test-output/"+repName);//specify location of the report
		try {
			sparkReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		extent=new ExtentReports();

		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","pavan");

		sparkReporter.config().setDocumentTitle("InetBanking Test Project"); // Tile of report
		sparkReporter.config().setReportName("Functional Test Automation Report"); // name of the report
		//sparkReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
		sparkReporter.config().setTheme(Theme.DARK);
	}

	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
	}

	public void onTestFailure(ITestResult tr)
	{

		logger=extent.createTest(tr.getName()); // create new entry in th report

		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted

		//log
		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";

		try {
			captureScreen(driver,tr.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//log
		File f = new File(screenshotPath); 


		if(f.exists())
		{
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
		}


	}

	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
}