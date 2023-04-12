package com.employee.util;

import com.employee.enums.ConfigProperties;
import com.employee.constants.FrameworkConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertiesUtils {
    private static Properties properties = new Properties();
    private static HashMap<String, String> CONFIGMAP = new HashMap<>();

    static {
        try(FileInputStream fis = new FileInputStream(FrameworkConstants.getConfigPropertiesPath())){
            properties.load(fis);
            properties.entrySet().forEach(entry -> CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue())));
        } catch (Exception e) {
            System.out.println("Exception while reading config.properties file");
            System.exit(0);
        }
    }

    public static String getValue(ConfigProperties key){
        if(Objects.isNull(CONFIGMAP.get(key.toString().toLowerCase())) || Objects.isNull(key)){
            throw new RuntimeException("Error while reading config.property file, please check property " + key);
        }

        return CONFIGMAP.get(key.toString().toLowerCase());
    }


}
