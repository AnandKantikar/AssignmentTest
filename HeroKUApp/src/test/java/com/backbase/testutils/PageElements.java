package com.backbase.testutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.backbase.commonfunctions.CommonFunctions;
import com.backbase.datafactory.DataFactory;
import com.backbase.helpermethods.HelperMethods;
import com.backbase.objectrepository.HeroKUAppPage;

public class PageElements {

	private static PageElements mInstance;
	private CommonFunctions commonFunctions;
	private DataFactory dataLibrabry;
	public WebDriver driver;
	private HelperMethods helperMethods;
	private HeroKUAppPage heroKUAppPage;
	public static PageElements getInstance() {
		if (mInstance == null) {
			synchronized (PageElements.class) {
				if (mInstance == null) mInstance = new PageElements();
			}
		}
		return mInstance;
	}

	public static void removeInstance() {
		mInstance = null;
	}

	public HeroKUAppPage getHeroKUAppPage() {
		if (heroKUAppPage == null)
			heroKUAppPage = PageFactory.initElements(driver, HeroKUAppPage.class);
		return heroKUAppPage;
	}
	
	public HelperMethods getHelperMethods() {
		if (helperMethods == null)
			helperMethods = PageFactory.initElements(driver, HelperMethods.class);
		return helperMethods;
	}
	
	public CommonFunctions getCommonFunctions() {
		if (commonFunctions == null)
			commonFunctions = PageFactory.initElements(driver, CommonFunctions.class);
		return commonFunctions;
	}

	public void resetApplication() {
		dataLibrabry = null;
		commonFunctions = null;
		commonFunctions = null;
		heroKUAppPage=null;
		helperMethods=null;
		driver.quit();
	}
}


