package com.myhome.myhome.controller;

import com.myhome.myhome.cache.TagCache;
import com.myhome.myhome.dto.QuestionDTO;
import com.myhome.myhome.mapper.QuestionMapper;
import com.myhome.myhome.mapper.UserMapper;
import com.myhome.myhome.model.Question;
import com.myhome.myhome.model.User;
import com.myhome.myhome.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class publishController {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionService questionService;

    @GetMapping("/publish")
    public String publish(Model model){

        model.addAttribute("tags", TagCache.get());

        return "publish";
    }


    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") Integer id,
                       Model model){
        QuestionDTO question =questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        model.addAttribute("tags", TagCache.get());

        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(
    @RequestParam(value = "title",required = false) String title,
    @RequestParam(value = "description",required = false) String description,
    @RequestParam(value = "tag",required = false) String tag,
    @RequestParam(value = "id",required = false) Integer id,
    HttpServletRequest request,
    Model model
    ){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("tags", TagCache.get());

        model.addAttribute("error","请填写以上内容");
        if (title==""){
            model.addAttribute("error","问题不能为空");
            return "publish";
        }
        if (description==""){
            model.addAttribute("error","问题描述不能为空");
            return "publish";
        }
        if (tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user =(User)request.getSession().getAttribute("user");


        if (user==null)
        {
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        Question question=new Question();
        question.setTag(tag);
        question.setDescription(description);
        question.setTitle(title);
        question.setCreater(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setId(id);


        questionService.createOrUpdate(question);

        return "redirect:/";
    }
}
