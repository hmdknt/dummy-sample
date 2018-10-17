package com.kento.dummy.controller;

import com.kento.dummy.domain.model.GoogleTask;
import org.springframework.security.authentication.jaas.LoginExceptionResolver;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.security.auth.login.LoginContext;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class AuthController {

    @GetMapping("/auth")
    public String login() {
        return "auth/index";
    }

    @GetMapping("/auth/result")
    public String auth(OAuth2AuthenticationToken authentication,Model model) {

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

        return "/auth/result";
    }

    @PostMapping("/google")
    public String google(Model model) {
        return "/google/result";
    }

    @RequestMapping("/back")
    public String top(Model model) {
        return "forward:auth";
    }
}
