package com.employee.tests;

import com.employee.annontations.FrameworkAnnotation;
import com.employee.constants.FrameworkConstantsSingleton;
import com.employee.enums.CategoryType;
import com.employee.pojo.Employee;
import com.employee.reports.ExtentLogger;
import com.employee.requestBuilder.RequestBuilder;
import com.employee.util.ApiUtils;
import com.employee.util.RandomUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static com.employee.util.RandomUtils.*;

public class PostTests {
    @FrameworkAnnotation(Category = CategoryType.MINI_REGRESSION_POST_CALL)
    @Test
    public void createEmployee(Method method) {
        String name = getName();
        int age = getAge();
        int id = getID();

        Employee employee = Employee
                .builder()
                .setId(id)
                .setAge(age)
                .setName(name)
                .setSalary(5000)
                .build();

        RequestSpecification requestSpecification = RequestBuilder
                .buildRequestForPostCalls()
                .header("Content-Type", ContentType.JSON)
                .body(employee);

        Response response = requestSpecification.post("/create");

        ExtentLogger.logRequest(requestSpecification);

        ApiUtils.storeStringAsJson(FrameworkConstantsSingleton.getInstance().getResponseJsonFolderPath(), method.getName(), response);

        //validate response schema
        response.then().body(JsonSchemaValidator.matchesJsonSchema(new File(FrameworkConstantsSingleton.getInstance().getResponseSchemaFolderPath() + "/schema.json")));

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(response.body().jsonPath().getString("status")).isEqualTo("success");
        Assertions.assertThat(response.body().jsonPath().getString("data.name")).isEqualTo(name);
        Assertions.assertThat(response.body().jsonPath().getInt("data.age")).isEqualTo(age);

        Uninterruptibles.sleepUninterruptibly(60, TimeUnit.SECONDS);
    }

    @FrameworkAnnotation(Category = CategoryType.SANITY_POST_CALL, AuthorName = {"Pavi", "Karthick"})
    @Test
    public void createEmployeeUsingJSONObject(Method method) {
        String name = getName();
        int age = getAge();
        int id = getID();

        JSONObject employee = new JSONObject();
        employee.put("name", name);
        employee.put("age", age);
        employee.put("salary", 5000);
        employee.put("id", id);

        RequestSpecification requestSpecification = RequestBuilder
                .buildRequestForPostCalls()
                .header("Content-Type", ContentType.JSON)
                .body(employee.toMap());

        Response response = requestSpecification.post("/create");

        ExtentLogger.logRequest(requestSpecification);

        Uninterruptibles.sleepUninterruptibly(60, TimeUnit.SECONDS);

        ExtentLogger.logResponse(response.prettyPrint());

        ApiUtils.storeStringAsJson(FrameworkConstantsSingleton.getInstance().getResponseJsonFolderPath(), method.getName(), response);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(response.body().jsonPath().getString("status")).isEqualTo("success");
        Assertions.assertThat(response.body().jsonPath().getString("data.name")).isEqualTo(name);
        Assertions.assertThat(response.body().jsonPath().getInt("data.age")).isEqualTo(age);
    }

    @FrameworkAnnotation(Category = CategoryType.SMOKE_POST_CALL)
    @Test
    public void createEmployeeFromExternalFile(Method method) throws IOException {
        String requestBody = ApiUtils
                .readJsonAndReturnAsString(FrameworkConstantsSingleton.getInstance().getRequestBodyFolderPath() + "/postRequest.json")
                .replace("fname", RandomUtils.getName())
                .replace("age", String.valueOf(RandomUtils.getAge()));

        RequestSpecification requestSpecification = RequestBuilder
                .buildRequestForPostCalls()
                .header("Content-Type", ContentType.JSON)
                .body(requestBody);

        Response response = requestSpecification.post("/create");

        ExtentLogger.logRequest(requestSpecification);

        Uninterruptibles.sleepUninterruptibly(60, TimeUnit.SECONDS);

        ExtentLogger.logResponse(response.prettyPrint());

        ApiUtils.storeStringAsJson(FrameworkConstantsSingleton.getInstance().getResponseJsonFolderPath(), method.getName(), response);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(response.body().jsonPath().getString("status")).isEqualTo("success");
    }
}
