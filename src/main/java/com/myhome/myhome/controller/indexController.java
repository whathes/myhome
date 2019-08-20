package com.myhome.myhome.controller;

import com.myhome.myhome.dto.PaginationDTO;
import com.myhome.myhome.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class indexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "10") Integer size){

//        System.out.println(page);
        PaginationDTO pagination = questionService.listQuestion(page,size);

        model.addAttribute("pagination",pagination);
        return "index";
    }


}
