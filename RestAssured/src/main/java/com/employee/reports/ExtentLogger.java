package com.employee.reports;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.Header;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentLogger {
    public static void pass(String message) {
        ExtentManager.getExtTest().pass(message);
    }

    public static void fail(String message) {
        ExtentManager.getExtTest().fail(MarkupHelper.createLabel(message, ExtentColor.RED));

    }

    public static void info(String message) {
        ExtentManager.getExtTest().info(message);
    }

    public static void logResponse(String message) {
        ExtentManager.getExtTest().pass("Response body");
        ExtentManager.getExtTest().pass(MarkupHelper.createCodeBlock(message, CodeLanguage.JSON));
    }

    public static void logRequest(RequestSpecification requestSpecification) {
        QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);

        ExtentManager.getExtTest().info("Request details - Base URL: " + query.getBaseUri());
        if (Objects.nonNull(query.getBody())) {
            ExtentManager.getExtTest().info("Request body");
            ExtentManager.getExtTest().info(MarkupHelper.createCodeBlock(query.getBody(), CodeLanguage.JSON));
        }
        ExtentManager.getExtTest().info("Request details - Query param: " + query.getQueryParams());
        ExtentManager.getExtTest().info("Request details - Path param: " + query.getPathParams());

        for (Header header : query.getHeaders()) {
            ExtentManager.getExtTest().info("Request Header: " + header.getName() + " " + header.getValue());
        }
    }
}
