package com.backbase.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeroKUAppPage {
WebDriver driver;

public HeroKUAppPage(WebDriver driver) {
	this.driver=driver;
}
	
//*****************Home Page WebElements****************
// Add a Computer
@FindBy(id="add")

public WebElement btn_AddComputr;


//Filter By Name
@FindBy(id="searchsubmit")

public WebElement btn_FiltrByNme;

//Filter By Name Text
@FindBy(id="searchbox")

public WebElement txt_FiltrByNme;

// Computer Name ACE
@FindBy(xpath="//a[contains(text(),'MicroMax')]")

public WebElement btn_CompurNme;

//Computer Name ACE
@FindBy(xpath="//section[contains(text(),'No computers found')]")

public WebElement label_NoComptrDisplay;

//Home 
@FindBy(xpath="//a[contains(text,'Play sample application — Computer database')]")

public WebElement label_Home;
//*****************Add/Edit computer Page WebElements****************

//Introduced Date
@FindBy(id="name")

public WebElement txt_ComputrNme;

//Introduced Date
@FindBy(id="introduced")

public WebElement txt_IntroduceDte;
//DisContinued Date
@FindBy(id="discontinued")
public WebElement txt_DisContinuedDte;

//Select Company DropDown
@FindBy(xpath="//select[@id='company']")

public WebElement drpDwn_Company;

// SaveComputer Button

@FindBy(xpath="//input[@class='btn primary']")

public WebElement btn_SaveComputer;
// Cancel Button
@FindBy(xpath="//a[text()='Cancel']")

public WebElement btn_Cancel;

//Delete Message
@FindBy(xpath="//div[contains(@class,'alert-message warning')]")

public WebElement label_Done;

//Delete Button
@FindBy(xpath="//input[@class='btn danger']")

public WebElement btn_DeleteComp;




}




