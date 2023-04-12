package com.employee.pojo;

import lombok.Builder;
import lombok.Getter;

@Builder(setterPrefix = "set")
@Getter
public class Employee {
    private String name;
    private int salary;
    private int age;
    private int id;
}
