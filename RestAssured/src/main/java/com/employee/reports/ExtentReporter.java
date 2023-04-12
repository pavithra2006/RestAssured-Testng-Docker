package com.employee.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.employee.enums.CategoryType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentReporter {
    private static ExtentReports extent;
    private static ExtentTest test;

//    public static ExtentTest getTest() {
//        return test;
//    }

    public static void initReports(){

        if(Objects.isNull(extent)) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setReportName("Employee API Automation");
            spark.config().setDocumentTitle("API Automation");

            extent.attachReporter(spark);
        }
    }

    public static void flushReports(){
        if(Objects.nonNull(extent))
            extent.flush();
        ExtentManager.unloadExtTest();

        try {
            Desktop.getDesktop().browse(new File("index.html").toURI());
        } catch (IOException e) {
            throw new RuntimeException("Exception while opening Extent report");
        }
    }

    public static void createTest(String testcaseName){
        ExtentManager.setExtTest(extent.createTest(testcaseName));
    }

    public static void assignAuthor(String[] authors){
        for(String author: authors){
            ExtentManager.getExtTest().assignAuthor(author);
        }
    }

    public static void assignCategroy(CategoryType[] categories){
        for(CategoryType category: categories){
            ExtentManager.getExtTest().assignCategory(String.valueOf(category));
        }
    }
}
