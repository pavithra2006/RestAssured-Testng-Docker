package com.employee.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

//note: lombok --> if fields are non static then we can add at class level, if static we need to add at field level
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FrameworkConstants {
    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/resources";
    private static final String CONFIG_PROPERTIES_PATH = RESOURCES_PATH + "/config.properties";
    private static final String RESPONSE_SCHEMA_FOLDER_PATH = RESOURCES_PATH + "/schema";

    private static final String REQUEST_BODY_FOLDER_PATH = RESOURCES_PATH + "/request";
    private static final String RESPONSE_JSON_FOLDER_PATH = System.getProperty("user.dir") + "/output";

    public static String getConfigPropertiesPath(){
        return CONFIG_PROPERTIES_PATH;
    }
    public static String getResponseSchemaFolderPath(){
        return RESPONSE_SCHEMA_FOLDER_PATH;
    }
    public static String getRequestBodyFolderPath(){
        return REQUEST_BODY_FOLDER_PATH;
    }
    public static String getResponseJsonBodyFolderPath(){
        return RESPONSE_JSON_FOLDER_PATH;
    }
}
