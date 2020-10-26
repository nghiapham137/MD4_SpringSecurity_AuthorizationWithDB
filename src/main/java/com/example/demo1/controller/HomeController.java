package com.example.demo1.controller;

import com.example.demo1.service.task_service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private TaskService taskService;

    @GetMapping()
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("list", taskService.findAll());
        return modelAndView;
    }
}
