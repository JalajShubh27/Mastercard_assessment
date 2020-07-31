package com.repo.assignment.controller;


import com.repo.assignment.resourceloader.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ApiController {

    @Autowired
    DataService dataServiceObj;

    @GetMapping("/connected")
    public String connected(@RequestParam(name = "origin") String origin, @RequestParam(name = "destination") String destination) {
        String ans = "No";
        try {
            ans = dataServiceObj.showPath(origin, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

}
