package com.backbase.commonfunctions;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.backbase.testutils.PageElements;



// This class is created to  maintain all generic/common functions
public class CommonFunctions {
    private WebDriver driver;
    private boolean isVisible, isDisplayed;
    private String workingDir = System.getProperty("user.dir");
 
    public CommonFunctions(WebDriver driver) {
        this.driver = driver;
       
    }

    /**
     * Method Name - isDisplayed
     * Method description - Verifies the element presence passed as parameter
     * param 1 - Web Element
     */
    public boolean isDisplayed(WebElement element) {
        return waitForVisibilityOfElement(element) && element != null && element.isDisplayed();
    }
    
    /**
     * Method Name - isDisplayed
     * Method description - Verifies the element doesnt presence passed as parameter
     * param 1 - Web Element
     */
    public boolean isNotDisplayed(WebElement element) {
       
    	 boolean isElementDispalyed = false;
    	 isElementDispalyed = waitForVisibilityOfElement(element);      
         return !isElementDispalyed;
    	
    }
    /**
     * Method Name - waitForVisibilityOfElement
     * Method description - Explicitly Wait the element appearance for specified time, if it appears before specified time, then it skip the remaining wait
     * param 1 - Web Element
     */
    public boolean waitForVisibilityOfElement(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 40);
            element = (WebElement) wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }

    }

    /**
     * Method Name - getTextFromElement
     * Method description -Get the text passed  parameter as element
     */
    public String getTex(WebElement element) {
        boolean isValue = false;
        String actTxt = null;
        isValue = waitForVisibilityOfElement(element);
        if (isValue) {
            actTxt = element.getText().trim();
        }

        return actTxt;
    }

    /**
     * Method Name - tap_On_Element
     * Method description - Click on the element array passed as parameter
     */
    public void clickElement(WebElement... element) {
        for (int i = 0; i < element.length; i++) {
            isVisible = waitForVisibilityOfElement(element[i]);
            isDisplayed = isDisplayed(element[i]);
            if (isVisible && isDisplayed)
                element[i].click();
        }
    }

    /**
     * Method Name - enter_Value
     * Method description - Enter on the element array passed as parameter
     */

    public void enter_Value(String value, WebElement... element) {
        for (int i = 0; i < element.length; i++) {
            isDisplayed = isDisplayed(element[i]);
            if (isDisplayed) {
                element[i].sendKeys(value);
            }
        }
    }
    /**
     * Method Name - selctFrmDrpDwn
     * Method description -Select an element from DropDown
     * param  - DropDown element, option
     */
  public void selctFrmDrpDwn(WebElement element,String option) {
	 
    	  Select sclt=new Select(element);
    	  sclt.selectByVisibleText(option);  
    	  PageElements.getInstance().driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      }
	
  
    /**
     * Method Name - capture_ScreenShott
     * Method description - Capture the Screen shot passes/failes parameter as testid
     */

    public void capture_ScreenShot(String path,String testid) {
        try {
            String FailTest = workingDir + "\\Reports\\Evidences";
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
            File theDir = new File(FailTest + "\\" + path);
            if (!theDir.exists()) {
                new File(FailTest + "\\" + path).mkdirs();
            }
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH.mm.ss");
            String currentDateTime = format.format(date);
            String destFile = currentDateTime + "_" + testid + ".png";
            FileUtils.copyFile(scrFile, new File(path + "/" + destFile));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    
    
    }
