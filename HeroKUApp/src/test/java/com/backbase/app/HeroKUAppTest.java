package com.backbase.app;

import org.testng.annotations.Test;

import com.backbase.datafactory.DataFactory;
import com.backbase.testutils.BaseSuite;
import com.backbase.testutils.PageElements;

public class HeroKUAppTest extends BaseSuite {


	@Test
	public void hroKUAppTest() {
		initReport(DataFactory.getData(testName, 3, 1));
		log.info("***********CreateNewCompTest Starts*******************");
		result(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().btn_FiltrByNme),DataFactory.testid1);
		result(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().btn_AddComputr),DataFactory.testid2);
		PageElements.getInstance().getHelperMethods().createComp(false, compNme);
		result(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().btn_FiltrByNme),DataFactory.testid4);
		PageElements.getInstance().getHelperMethods().createComp(true,compNme);
		result(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().label_Done),DataFactory.testid3);
		PageElements.getInstance().getHelperMethods().searchCompNme(compNme);
		result(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().btn_CompurNme),DataFactory.testid6);
		if(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().btn_CompurNme)) {
			PageElements.getInstance().getCommonFunctions().clickElement(PageElements.getInstance().getHeroKUAppPage().btn_CompurNme);
			log.info(compNme+" "+"  Clicked");	
			if(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().drpDwn_Company)) {
				PageElements.getInstance().getCommonFunctions().selctFrmDrpDwn(PageElements.getInstance().getHeroKUAppPage().drpDwn_Company, compnyNme);
				PageElements.getInstance().getCommonFunctions().clickElement(PageElements.getInstance().getHeroKUAppPage().btn_Cancel);
				result(PageElements.getInstance().getCommonFunctions().isNotDisplayed(PageElements.getInstance().getHeroKUAppPage().label_Done),DataFactory.testid10);
				log.info("Cancel button Clicked");	
			}else {
				log.info("Company Dropdown Does not displayed");	
			}
						
		PageElements.getInstance().getHelperMethods().searchCompNme(compNme);
		if(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().btn_CompurNme)) {
			PageElements.getInstance().getCommonFunctions().clickElement(PageElements.getInstance().getHeroKUAppPage().btn_CompurNme);
			log.info(compNme+" "+"  Clicked");	
			PageElements.getInstance().getCommonFunctions().selctFrmDrpDwn(PageElements.getInstance().getHeroKUAppPage().drpDwn_Company, compnyNme);
			if(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().btn_SaveComputer)) {
				PageElements.getInstance().getCommonFunctions().clickElement(PageElements.getInstance().getHeroKUAppPage().btn_SaveComputer);
				log.info("Save this computer button Clicked");	
			}else {
				log.info("Save this computer button Clicked");	
			}
			result(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().label_Done),DataFactory.testid9);

		}else {
			log.info(compNme+" does not displayed");
		}
		PageElements.getInstance().getHelperMethods().deleteComp(compNme);
		result(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().label_Done),DataFactory.testid7);
		PageElements.getInstance().getHelperMethods().searchCompNme(compNme);
		result(PageElements.getInstance().getCommonFunctions().isDisplayed(PageElements.getInstance().getHeroKUAppPage().label_NoComptrDisplay),DataFactory.testid8);
		result(PageElements.getInstance().getCommonFunctions().isNotDisplayed(PageElements.getInstance().getHeroKUAppPage().btn_CompurNme),DataFactory.testid11);
	}




	}
}

