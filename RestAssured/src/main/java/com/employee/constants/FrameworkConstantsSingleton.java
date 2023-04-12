package com.employee.constants;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FrameworkConstantsSingleton {
    //no need of creating multiple getters
    private static FrameworkConstantsSingleton INSTANCE = null;

    public static synchronized FrameworkConstantsSingleton getInstance(){
        if(Objects.isNull(INSTANCE))
            INSTANCE = new FrameworkConstantsSingleton();

        return INSTANCE;
    }
    
    private final String resourcesPath = System.getProperty("user.dir") + "/src/test/resources";
    private final String configPropertiesPath = resourcesPath + "/config.properties";
    private final String responseSchemaFolderPath = resourcesPath + "/schema";

    private final String requestBodyFolderPath = resourcesPath + "/request";
    private final String responseJsonFolderPath = System.getProperty("user.dir") + "/output";
}
