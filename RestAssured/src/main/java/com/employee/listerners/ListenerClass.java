package com.employee.listerners;

import com.employee.annontations.FrameworkAnnotation;
import com.employee.reports.ExtentLogger;
import com.employee.reports.ExtentReporter;
import lombok.NoArgsConstructor;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

@NoArgsConstructor
public final class ListenerClass implements ISuiteListener, ITestListener {

    @Override
    public void onStart(ISuite suite) {      //ISuiteListener
        ExtentReporter.initReports();
    }


    @Override
    public void onFinish(ISuite suite) {
        ExtentReporter.flushReports();
    }

    @Override
    public void onTestStart(ITestResult result) {        //ITestListener
        ExtentReporter.createTest(result.getMethod().getMethodName());
        ExtentReporter.assignAuthor(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).AuthorName());
        ExtentReporter.assignCategroy(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).Category());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getMethod().getMethodName() + " - Test Passed");
    }


    @Override
    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail(result.getMethod().getMethodName() + " - Test Failed");
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.info(result.getMethod().getMethodName() + " - Test Skipped");
    }
}
