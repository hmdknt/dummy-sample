package com.kento.dummy.controller;

import com.kento.dummy.domain.model.GoogleTask;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class AuthController {


    @GetMapping("/driver")
    public String driver() {
        return "/auth/authentication";
    }

    @RequestMapping("/auth")
    public String auth(Model model) {

        List<GoogleTask> googleTasks = new ArrayList<>();

        GoogleTask tasks = new GoogleTask();

        tasks.setEtag("hello");
        tasks.setId("123");
        tasks.setTitle("aaaa");

        googleTasks.add(tasks);

        tasks.setEtag("hello1");
        tasks.setId("1234");
        tasks.setTitle("aaaa");

        googleTasks.add(tasks);

        model.addAttribute("googleTasks", googleTasks);

        return "/auth/authentication_result";
    }

    @GetMapping("/google")
    public String google(Model model) {
        return "/google/result";
    }

}
