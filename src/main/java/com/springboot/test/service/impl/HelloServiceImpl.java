package com.springboot.test.service.impl;

import com.springboot.test.mapper.HelloMapper;
import com.springboot.test.entity.Student;
import com.springboot.test.service.HelloService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("HelloService")
public class HelloServiceImpl implements HelloService {

    @Resource
    private HelloMapper helloMapper;

    @Override
    public List<Student> getStudent() {
        return helloMapper.getStudent();
    }
}
