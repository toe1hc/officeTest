package testng_framework;

import org.testng.annotations.Test;

public class Part_III_Priority {

 //priority
  @Test(priority = 1)
  public void TC_Create_New_Account() {
	  System.out.println("Run test 01");
  }
  
  @Test(priority = 2)
  public void TC_Create_New_Customer() {
	  System.out.println("Run test 02");
  }
  @Test(priority = 3)
  public void TC_Delete_Account() {
	  System.out.println("Run test 03");
  }
  
  @Test(priority = 4)
  public void TC_Delete_Customer() {
	  System.out.println("Run test 04");
  }
  
  //description 
  @Test(description = "Edit A Account")
  public void TC_Edit_Account() {
	  System.out.println("Run test 05");
  }
  
  // skip test case
  @Test(enabled = false)
  public void TC_Edit_Customer() {
	  System.out.println("Run test 06");
  }
	  
  
}
