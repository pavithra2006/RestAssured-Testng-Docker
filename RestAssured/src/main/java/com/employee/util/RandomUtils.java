package com.employee.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomUtils {
    //business layer --> we can do all business level changes here
    //example: changing id range
    public static String getName(){
        return FakerUtils.getString();
    }

    public static int getAge(){
        return FakerUtils.getNumber(20, 60);
    }

    public static int getID(){
        return FakerUtils.getNumber(1000, 5000);
    }
}
