package testng_framework;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(testng_framework.TestNGListener.class)
public class Part_VII_Listener {

  @Test
  public void TC_01_Pass() {
	  System.out.println("Pass nha !");
  }
  
  @Test
  public void TC_02_Skip() {
	  System.out.println("Test skipped");
	  throw new SkipException("Skipped testcase");
  }
  
  @Test
  public void TC_03_Failed() {
	  System.out.println("Test failed");
	  Assert.assertTrue(false);
  }
}
