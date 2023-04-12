package com.employee.tests;

import com.employee.annontations.FrameworkAnnotation;
import com.employee.constants.FrameworkConstantsSingleton;
import com.employee.enums.CategoryType;
import com.employee.reports.ExtentLogger;
import com.employee.requestBuilder.RequestBuilder;
import com.employee.util.ApiUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class GetTests {

    @FrameworkAnnotation
//    @FrameworkAnnotation(Category = {CategoryType.END_TO_END_GET_CALL, CategoryType.REGRESSION_GET_CALL} , AuthorName = "Pavithra Karthick")
    @Test
    public void getEmployee(Method method) {

//        Response response = RequestBuilder
//                .buildRequestForGetCalls()
//                .header("Content-Type", ContentType.JSON)
//                .when()
//                .get("/employees");

//interface
        RequestSpecification requestSpecification = RequestBuilder
                .buildRequestForGetCalls()
                .header("Content-Type", ContentType.JSON);

//interface
        Response response = requestSpecification.get("/employees");

        ExtentLogger.logRequest(requestSpecification);

        Uninterruptibles.sleepUninterruptibly(60, TimeUnit.SECONDS);

        ExtentLogger.logResponse(response.prettyPrint());

        ApiUtils.storeStringAsJson(FrameworkConstantsSingleton.getInstance().getResponseJsonFolderPath(), method.getName(), response);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(response.body().jsonPath().getString("status")).isEqualTo("success");
        Assertions.assertThat(response.body().jsonPath().getList("data")).hasSizeGreaterThan(0);

        JSONObject jsonObject = new JSONObject(response.asString());    //asString() method is from rest assured
        for (int i = 0; i < jsonObject.getJSONArray("data").length() - 1; i++) {
            System.out.println(jsonObject.getJSONArray("data").getJSONObject(i).get("employee_name"));
        }
    }

    @FrameworkAnnotation(Category = CategoryType.SANITY_GET_CALL)
    @Test(dataProvider = "getIdList")
    public void getEmployeeByID(int id, String empName, Method method) {
        RequestSpecification requestSpecification = RequestBuilder
                .buildRequestForGetCalls()
                .pathParams("id", id)
                .header("Content-Type", ContentType.JSON);

        Response response = requestSpecification.get("/employee/{id}");

        ExtentLogger.logRequest(requestSpecification);

        Uninterruptibles.sleepUninterruptibly(60, TimeUnit.SECONDS);

        ExtentLogger.logResponse(response.prettyPrint());

        ApiUtils.storeStringAsJson(FrameworkConstantsSingleton.getInstance().getResponseJsonFolderPath(), method.getName(), response);

        
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(response.body().jsonPath().getString("status")).isEqualTo("success");
        Assertions.assertThat(response.body().jsonPath().getInt("data.id")).isNotNull();
        Assertions.assertThat(response.body().jsonPath().getString("data.employee_name")).isEqualTo(empName);
    }

    @DataProvider
    public Object[][] getIdList() {
        return new Object[][]{
                {1, "Tiger Nixon"},
                {2, "Garrett Winters"},
                {3, "Ashton Cox"},
                {4, "Cedric Kelly"}};
    }

    @FrameworkAnnotation
    @Test
    public void postmanGetCall() {
        RequestSpecification requestSpecification = given()
                .auth()
                .basic("postman", "password");

        ExtentLogger.logRequest(requestSpecification);

        Response response = requestSpecification
                .get("https://postman-echo.com/basic-auth");

        ExtentLogger.logResponse(response.prettyPrint());

        Assertions.assertThat(response.jsonPath().getBoolean("authenticated")).isTrue();
    }

    @FrameworkAnnotation
    @Test
    public void postmanGetAllWorkspaces() {
        RequestSpecification requestSpecification = given()
                .header("X-API-Key", "PMAK-64326d423c6b77791a5ec291-4540a64b76a8c73fcb39d03b08e6aace68")
                .config(config.logConfig(LogConfig.logConfig().blacklistHeader("X-API-Key")));
        // still in loggers we will be finding the api key

        ExtentLogger.logRequest(requestSpecification);

        Response response = requestSpecification
                .get("https://api.getpostman.com/workspaces");

        ExtentLogger.logResponse(response.prettyPrint());

        Assertions.assertThat(response.jsonPath().getList("workspaces")).hasSizeGreaterThan(0);

        JSONObject jsonObject = new JSONObject(response.asString());
        Assertions.assertThat(jsonObject.getJSONArray("workspaces").getJSONObject(0).getString("id")).isEqualTo("7ff353c6-bf0f-46c9-811f-e389d4350236");
    }
}
