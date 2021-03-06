package com.zjx.controller;

import com.zjx.entity.Users;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello security";
    }

    @GetMapping("/index")
    public String index(){
        return "iam index";
    }

    @GetMapping("/update")
//    @Secured({"ROLE_sale", "ROLE_manager"})
//    @PreAuthorize("hasAnyAuthority('admins')")
    @PostAuthorize("hasAnyAuthority('admin')")
    public String update(){
        System.out.println("i am update");
        return "iam update";
    }

    @GetMapping("/getAll")
    @PostAuthorize("hasRole('ROLE_sale')")
    @PostFilter("filterObject.username == 'admin1'")//对返回结果进行过滤
    public List<Users> getAllUser(){

        List<Users> list = new ArrayList<Users>();
        list.add(new Users(1, "admin1", "123"));
        list.add(new Users(1, "admin2", "123"));
        return list;
    }
}
