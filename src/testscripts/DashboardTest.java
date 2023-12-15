package testscripts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DashboardPage;

public class DashboardTest extends TestBase{
	DashboardPage dashboardPage;
	
	@Test
	public void verifyItemListInSideFilterOptions() {
		List<String> expectedCategoriesList 
			= new ArrayList<String>(Arrays.asList("fashion","electronics","household"));
		
		login();
		dashboardPage = new DashboardPage();
		System.out.println("STEP - Get Total number of Items in categories");
		int actualCategoriesItemSize = dashboardPage.getTotalNumberItemsInCategories();
		
		System.out.println("VERIFY - Number of Items visible in categories");
		Assert.assertEquals(actualCategoriesItemSize, 3);
		
		System.out.println("VERIFY - Items in Categories");
		List<String> actualCategoriesList = dashboardPage.getCategoriesItemTextList();
		System.out.println(actualCategoriesList);
		Assert.assertEquals(actualCategoriesList, expectedCategoriesList);
		
		System.out.println("STEP - Get Total number of Items in Sub Categories");
		int actualSubCategoriesItemSize = dashboardPage.getTotalNumberItemsInSubCategories();
		
		System.out.println("VERIFY - Number of Items visible in Sub Categories");
		Assert.assertEquals(actualSubCategoriesItemSize, 5);
		
		System.out.println("STEP - Get Total number of Items in Search For");
		int actualSearchForItemSize = dashboardPage.getTotalNumberItemsInSearchFor();
		
		System.out.println("VERIFY - Number of Items visible in Search For");
		Assert.assertEquals(actualSearchForItemSize, 2);
	}
	
	
	
}
