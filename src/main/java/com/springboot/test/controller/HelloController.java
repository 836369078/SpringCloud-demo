package com.springboot.test.controller;

import com.springboot.test.entity.Student;
import com.springboot.test.service.HelloService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @Resource
    private Student student;

    @Resource
    private HelloService helloService;

    @Value("${name}")
    private String name;

    @Value("${age}")
    private Integer age;

    @Value("${content}")
    private String content;


    @RequestMapping(value = "/ims", method = RequestMethod.GET)/**
     * 根据关键字对商户ID与商户名进行过滤
     *
     * @param keyword 关键字
     * @throws
     * @return
     */
    public List<Map<String, Object>> getShop(@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
        return helloService.getShop(keyword);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String say() {
        return content;
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String stu() {
        return student.getName();
    }

    @RequestMapping(value = "/num/{id}&{ids}", method = RequestMethod.GET)
    public String num(@PathVariable("id") Integer id, @PathVariable("ids") Integer ids) {
        return "id = " + id + "ids = " + ids;
    }

    @GetMapping(value = "/num2")
    public String num2(@RequestParam(value = "id", defaultValue = "0", required = false) Integer id) {
        return "id = " + id;
    }

    @GetMapping(value = "/getStudent")
    public List<Student> getStudent() {
        return helloService.getStudent();
    }
}
