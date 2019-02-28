package com.backbase.testutils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.backbase.commonfunctions.CommonFunctions;
import com.backbase.datafactory.DataFactory;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseSuite {
	final String workingDir = System.getProperty("user.dir");
	private String extentConfig = workingDir + "\\Files\\config.xml";
	protected ExtentTest test;
	protected Logger log;
	private ExtentReports report;
	private String loggerLocation = workingDir + "\\resources\\log4j.properties";
	protected CommonFunctions commonFunctions=null;
    private boolean expected=true;
    private String chrome=workingDir+"\\Files\\chromedriver.exe";
	protected final String testName="ManualCases";
	protected final String  testData="HeroKUData";
	protected String compNme="MicroMax";
	protected String compnyNme="RCA";
    @BeforeSuite
	protected void onBeforeSuite() throws Throwable {
		PropertyConfigurator.configure(loggerLocation);
		log = Logger.getLogger(this.getClass().getName());
		java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
        String machine = localMachine.getHostName();
        String username = System.getProperty("user.name");
        initReport(machine, username, this.getClass().getSimpleName());
	}
	
	@BeforeMethod
	protected void launchApp() {
		String url="http://computer-database.herokuapp.com/computers";
		System.setProperty("webdriver.chrome.driver", chrome);
		 PageElements.getInstance().driver=new ChromeDriver();
		PageElements.getInstance().driver.manage().window().maximize();
		PageElements.getInstance().driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PageElements.getInstance().driver.get(url);
	}
	
	protected void initReport(String machine, String username, String className) {
		StringBuilder builder = new StringBuilder();
		builder.append(workingDir);
		builder.append("\\Reports\\");
		builder.append(className);
		builder.append(".html");
		report = new ExtentReports(builder.toString(), true);
		report
		.addSystemInfo("Host Name", machine)
		.addSystemInfo("User Name", username);
		report.loadConfig(new File(extentConfig));
	}
	
	protected void initReport(String testName) {
		test = report.startTest("Automation Test: " + testName);
	}

	protected void result(boolean actual, String testId) {
		SoftAssert assertion=new SoftAssert();
			if(actual) {
				assertion.assertEquals(actual, expected);
				test.log(LogStatus.PASS, DataFactory.getData(testName,testId));
				PageElements.getInstance().getCommonFunctions().capture_ScreenShot("PassTestScreens", testId);
			}else {
				test.log(LogStatus.FAIL, DataFactory.getData(testName,testId));
				PageElements.getInstance().getCommonFunctions().capture_ScreenShot("FailedTestScreens", testId);
			}
			assertion.assertAll();
		}
		
	
	 @AfterMethod(alwaysRun = true)
	 protected void getResult(ITestResult result) throws InterruptedException, IOException {  
		 if (result.getStatus() == ITestResult.FAILURE) {
	            test.log(LogStatus.FAIL, result.getThrowable());
	        }
	        report.endTest(test);
	        report.flush();
	    }
	
	 @AfterSuite(alwaysRun = true)
	 protected void onAfterSuite() {
	        PageElements.removeInstance();
	        PageElements.getInstance().resetApplication();
	        log.info("Suite Execution is Completed");
	    }
}
