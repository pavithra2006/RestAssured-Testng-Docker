package com.employee.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentManager {

    private static ThreadLocal<ExtentTest> extTest = new ThreadLocal<>();

    static ExtentTest getExtTest(){
        return extTest.get();
    }

    static void setExtTest(ExtentTest ext){
        if(Objects.nonNull(ext))
            extTest.set(ext);
    }

    static void unloadExtTest(){
        extTest.remove();
    }
}
