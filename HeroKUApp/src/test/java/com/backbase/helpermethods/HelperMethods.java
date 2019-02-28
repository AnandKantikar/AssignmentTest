package com.backbase.helpermethods;

import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import com.backbase.testutils.PageElements;

public class HelperMethods {
	protected Logger log;
	String workingDir = System.getProperty("user.dir");
	private String loggerLocation = workingDir + "\\resources\\log4j.properties";
	private WebDriver driver;
	public HelperMethods(WebDriver driver) {
        this.driver = driver;
       
    }
	public void createComp(boolean create, String compNme) {

		PropertyConfigurator.configure(loggerLocation);
		log = Logger.getLogger(this.getClass().getName());
		if(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().btn_AddComputr)) {
			PageElements.getInstance().getCommonFunctions().clickElement(PageElements.getInstance().getHeroKUAppPage().btn_AddComputr);
			log.info("Add Computer button Clicked");
			if(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().txt_ComputrNme)) {
				PageElements.getInstance().getCommonFunctions().enter_Value(compNme,PageElements.getInstance().getHeroKUAppPage().txt_ComputrNme);
				log.info("Entered Computer Name"+" :"+compNme);		
			}else {
				log.info("Enter Computer Name filed disabled/not displayed");

			}
			if(create) {
			if(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().btn_SaveComputer)) {
				PageElements.getInstance().getCommonFunctions().clickElement(PageElements.getInstance().getHeroKUAppPage().btn_SaveComputer);
				log.info("Create this computer button Clicked");	
			}
			}else if(create==false) {
				
					PageElements.getInstance().getCommonFunctions().clickElement(PageElements.getInstance().getHeroKUAppPage().btn_Cancel);
					log.info("Cancel button Clicked");	
				}else {
					log.info("Cancel button does not displayed");

				}
		}else {
			log.info("Add Computer button does not displayed");

		}
		}
	
	public void searchCompNme(String computrNme) {
		PropertyConfigurator.configure(loggerLocation);
		log = Logger.getLogger(this.getClass().getName());
		if(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().txt_FiltrByNme)) {
			PageElements.getInstance().getHeroKUAppPage().txt_FiltrByNme.clear();
			PageElements.getInstance().getCommonFunctions().enter_Value(computrNme,PageElements.getInstance().getHeroKUAppPage().txt_FiltrByNme);
			log.info("Entered Computer Name"+" :"+computrNme+"in Filtered by Name text field");		
			PageElements.getInstance().getCommonFunctions().clickElement(PageElements.getInstance().getHeroKUAppPage().btn_FiltrByNme);
			log.info("Filter by Name button Clicked");	
	}else {
		log.info("Filter By Name button does not displayed");
	}

		
		
	}
	public void deleteComp(String computrNme) {
		searchCompNme(computrNme);
		if(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().btn_CompurNme)) {
			PageElements.getInstance().getCommonFunctions().clickElement(PageElements.getInstance().getHeroKUAppPage().btn_CompurNme);
			log.info(computrNme+" "+"  Clicked");	
		}else {
			log.info("computrNme does not displayed");
		}
		if(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().btn_DeleteComp)) {
			PageElements.getInstance().getCommonFunctions().clickElement(PageElements.getInstance().getHeroKUAppPage().btn_DeleteComp);
			log.info(computrNme+" "+"  Clicked");	
		}else {
			log.info("computrNme does not displayed");
		}
	}
}
