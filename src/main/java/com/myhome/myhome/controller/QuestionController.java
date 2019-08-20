package com.myhome.myhome.controller;

import com.myhome.myhome.dto.CommentDTO;
import com.myhome.myhome.dto.QuestionDTO;
import com.myhome.myhome.service.CommentService;
import com.myhome.myhome.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id,
                           Model model){
        QuestionDTO questionDTO=questionService.getById(id);

        List<CommentDTO> comments = commentService.listByQuestionId(id);

//        List<Integer> counts =new ArrayList<>();

        questionService.inView(questionDTO.getViewCount()+1,id);

        for (CommentDTO comment : comments) {
            Integer count = commentService.inCommentCount(comment.getId());
            commentService.update2CommentCount(id,count);
            comment.setCommentCount(count);
//            questionService.updateQuestionCommentNum(comments.size(),comment.getId());
        }
        questionDTO.setCommentCount(comments.size());

        List<QuestionDTO> relatedQuestions= questionService.selectRelated(questionDTO);
//
//        model.addAttribute("counts",counts);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        model.addAttribute("relatedQuestions",relatedQuestions);
//        User user =questionDTO.getUser();
//        model.addAttribute("user",user);

        return "question";
    }
}
