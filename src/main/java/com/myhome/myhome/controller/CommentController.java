package com.myhome.myhome.controller;

import com.myhome.myhome.dto.CommentDTO;
import com.myhome.myhome.dto.CommentcreateDTO;
import com.myhome.myhome.dto.QuestionDTO;
import com.myhome.myhome.dto.ResultDTO;
import com.myhome.myhome.exception.CustomizeErrorCode;
import com.myhome.myhome.model.Comment;
import com.myhome.myhome.model.User;
import com.myhome.myhome.service.CommentService;
import com.myhome.myhome.service.QuestionService;
import com.myhome.myhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentcreateDTO commentcreateDTO,
                       HttpServletRequest request,
                       Model model){

        if (commentcreateDTO.getType()==1){
            QuestionDTO question = questionService.getById(commentcreateDTO.getParentId());
            User user =(User) request.getSession().getAttribute("user");

            if(user==null){
                return ResultDTO.errprOf(CustomizeErrorCode.NO_LOGIN);
            }
            if (question==null){
                return ResultDTO.errprOf(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            if (commentcreateDTO ==null||commentcreateDTO.getContent()==null||commentcreateDTO.getContent()==""){
                return ResultDTO.errprOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
            }

            Comment comment =new Comment();
            comment.setParentId(commentcreateDTO.getParentId());
            comment.setType(commentcreateDTO.getType());
            comment.setContent(commentcreateDTO.getContent());
            comment.setGmtModified(System.currentTimeMillis());
            comment.setGmtCreate(System.currentTimeMillis());
            comment.setCommentator(user.getId());
            comment.setLikeCount(0);
            commentService.insertCommentDTO(comment);


            System.out.println("=================================");
            Integer count = commentService.inCommentCount(comment.getParentId());
            System.out.println(count);
//        Map<Object,Object> objectObjectMap=new HashMap<>();
//        objectObjectMap.put("message","成功");


            return  ResultDTO.okOf();
        }else{

            User user =(User) request.getSession().getAttribute("user");

            if(user==null){
                return ResultDTO.errprOf(CustomizeErrorCode.NO_LOGIN);
            }

            if (commentcreateDTO ==null||commentcreateDTO.getContent()==null||commentcreateDTO.getContent()==""){
                return ResultDTO.errprOf(CustomizeErrorCode.COMMENT_IS_EMPTY);
            }
            Comment comment =new Comment();
            comment.setParentId(commentcreateDTO.getParentId());
            comment.setType(commentcreateDTO.getType());
            comment.setContent(commentcreateDTO.getContent());
            comment.setGmtModified(System.currentTimeMillis());
            comment.setGmtCreate(System.currentTimeMillis());
            comment.setCommentator(user.getId());
            comment.setLikeCount(0);
            commentService.insertCommentDTO(comment);

//            CommentDTO commentDTO = commentService.listByIdForTwo(comment.getId());

            return ResultDTO.okOf();
        }

    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO comments(@PathVariable("id") Integer id,Model model){
        List<Comment> comments = commentService.selectBycommentParentId(id);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDTO commentDTO =new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setParentId(comment.getParentId());
            commentDTO.setLikeCount(comment.getLikeCount());
            commentDTO.setType(comment.getType());
            commentDTO.setCommentator(comment.getCommentator());
            commentDTO.setContent(comment.getContent());
            commentDTO.setGmtCreate(comment.getGmtCreate());
            commentDTO.setGmtModified(comment.getGmtModified());
            User user = userService.findById(comment.getCommentator());
            if (user!=null){
                commentDTO.setUser(user);
                commentDTOS.add(commentDTO);
            }else{
                continue;
            }
        }
        model.addAttribute("commentDTOS",commentDTOS);

        return ResultDTO.okOf(commentDTOS);
    }

}
