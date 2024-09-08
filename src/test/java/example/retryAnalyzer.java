package example;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retryAnalyzer implements IRetryAnalyzer {


    @Override
    public boolean retry(ITestResult iTestResult) {
        int count = 0;

        return false;
    }
}
