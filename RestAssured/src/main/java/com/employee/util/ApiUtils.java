package com.employee.util;

import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiUtils {
    @SneakyThrows //same as throws exception--> passes exception to main method
    public static String readJsonAndReturnAsString(String filePath){
        //return new String(Files.readAllBytes(Paths.get(filePath)));
        return Files.readAllBytes(Paths.get(filePath)).toString();
    }

    @SneakyThrows
    public static void storeStringAsJson(String filePath, String methodName, Response response){
        Files.write(Paths.get(filePath + "/" + methodName + ".json"), response.asByteArray());
    }
}
