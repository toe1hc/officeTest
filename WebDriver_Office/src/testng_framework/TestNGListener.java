package testng_framework;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult Result) {
		 System.out.println("The name of the testcase failed is :"+ Result.getName());		
	}

	@Override
	public void onTestSkipped(ITestResult Result) {
		System.out.println("The name of the testcase Skipped is :"+ Result.getName());
		
	}

	@Override
	public void onTestStart(ITestResult Result) {
		//System.out.println(Result.getName()+" test case started");	
	}

	@Override
	public void onTestSuccess(ITestResult Result) {
		 System.out.println("The name of the testcase passed is :"+ Result.getName());
		
	}
}
