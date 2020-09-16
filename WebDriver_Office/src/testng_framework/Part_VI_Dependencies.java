package testng_framework;

import org.testng.Assert;
import org.testng.annotations.Test;



public class Part_VI_Dependencies {
	
	  @Test()
	  public void TC_01_Create_New_Account() {
		  System.out.println("Run test 01");
	  }
	  
	  @Test(dependsOnMethods = "TC_01_Create_New_Account")
	  public void TC_02_Edit_Account() {
		  System.out.println("Run test 02");
	  }

	  @Test(dependsOnMethods = "TC_01_Create_New_Account")
	  public void TC_03_Create_New_Customer() {
		  System.out.println("Run test 03");
		  Assert.assertTrue(false);
	  }
	  
	  @Test(dependsOnMethods = "TC_03_Create_New_Customer")
	  public void TC_04_Edit_Customer() {
		  System.out.println("Run test 04");
	  }
	  
	  @Test(dependsOnMethods = "TC_04_Edit_Customer")
	  public void TC_05_Delete_Account() {
		  System.out.println("Run test 05");
	  }
	  
	  @Test(dependsOnMethods = "TC_05_Delete_Account")
	  public void TC_06_Delete_Customer() {
		  System.out.println("Run test 06");
	  }
	  

	  

}
