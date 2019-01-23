package com.springboot.test.mapper;

import com.springboot.test.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface HelloMapper {

    List<Student> getStudent();

    List<Map<String, Object>> getShop(@Param(value="keyword") String keyword);
}
