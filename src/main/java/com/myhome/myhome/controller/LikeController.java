package com.myhome.myhome.controller;

import com.myhome.myhome.dto.CommentDTO;
import com.myhome.myhome.service.CommentService;
import com.myhome.myhome.service.LikeCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LikeController {
    @Autowired
    LikeCountService likeCountService;
    @Autowired
    CommentService commentService;
    @GetMapping("/likeCountUp/{id}")
    @Transactional
    public void likeCountUp(@PathVariable("id") Integer id){
        CommentDTO count=commentService.selectById(id);
        likeCountService.likeUp(count.getLikeCount()+1,id);
    }
    @GetMapping("/likeCountDown/{id}")
    @Transactional
    public void likeCountDown(@PathVariable("id") Integer id){
        CommentDTO count=commentService.selectById(id);
        likeCountService.likeUp(count.getLikeCount()-1,id);
    }

}
