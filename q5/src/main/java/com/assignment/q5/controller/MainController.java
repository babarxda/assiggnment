package com.assignment.q5.controller;

import com.assignment.q5.repository.DemoDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
    @Autowired
    DemoDataRepository repository;

    @GetMapping("/")
    public String test(Model model) {
        String value = repository.findById(1L).get().getValue();
        model.addAttribute("data", value);
        return "index";
    }
}
