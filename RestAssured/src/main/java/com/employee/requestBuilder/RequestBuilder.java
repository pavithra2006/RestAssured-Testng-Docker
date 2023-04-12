package com.employee.requestBuilder;

import com.employee.enums.ConfigProperties;
import com.employee.util.PropertiesUtils;
import io.restassured.config.LogConfig;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestBuilder {
    public static RequestSpecification buildRequestForGetCalls(){
        return given()
                .config(config.logConfig(LogConfig.logConfig().blacklistHeader("Content-Type")))
                .log()
                .all()
                .baseUri(PropertiesUtils.getValue(ConfigProperties.BASEURL));
    }

    public static RequestSpecification buildRequestForPostCalls(){
        return given()
                .config(config.logConfig(LogConfig.logConfig().blacklistHeader("Content-Type")))
                .log()
                .all()
                .baseUri(PropertiesUtils.getValue(ConfigProperties.BASEURL));
    }
}
