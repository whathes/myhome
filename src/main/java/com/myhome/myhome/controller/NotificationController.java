package com.myhome.myhome.controller;

import com.myhome.myhome.model.User;
import com.myhome.myhome.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String getQuestionId(HttpServletRequest request,
                                @PathVariable("id") Integer id,
                                @RequestParam(name = "page",defaultValue = "1") Integer page,
                                @RequestParam(name = "size",defaultValue = "10") Integer size,
                                Model model){
        User user =(User)request.getSession().getAttribute("user");


        if (user==null){
            return "redirect:/";
        }
//        PaginationDTO<NotificationDTO> paginationDTO=notificationService.listReplies(id,page,size);
//        model.addAttribute("pagination",paginationDTO);

        return "profile";
    }
}
