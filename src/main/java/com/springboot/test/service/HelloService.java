package com.springboot.test.service;

import com.springboot.test.entity.Student;

import java.util.List;
import java.util.Map;

public interface HelloService {

    List<Student> getStudent();

    List<Map<String, Object>> getShop(String keyword);
}
