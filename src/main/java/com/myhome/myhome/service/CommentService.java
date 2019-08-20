package com.myhome.myhome.service;

import com.myhome.myhome.dto.CommentDTO;
import com.myhome.myhome.enums.CommentTypeEnum;
import com.myhome.myhome.enums.NotificationEnum;
import com.myhome.myhome.enums.NotificationStatusEnum;
import com.myhome.myhome.exception.CustomizeErrorCode;
import com.myhome.myhome.exception.CustomizeException;
import com.myhome.myhome.mapper.CommentMapper;
import com.myhome.myhome.mapper.NotificationMapper;
import com.myhome.myhome.mapper.QuestionMapper;
import com.myhome.myhome.mapper.UserMapper;
import com.myhome.myhome.model.Comment;
import com.myhome.myhome.model.Notification;
import com.myhome.myhome.model.Question;
import com.myhome.myhome.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    @Transactional
    public void insertCommentDTO(Comment comment) {
        if (comment.getParentId()==null||comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType()== CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment =commentMapper.selectByCommentId(comment.getParentId());
            if (dbComment ==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            Comment commentFirst = commentMapper.selectByCommentId(comment.getParentId());
            Question questionCreate = questionMapper.getById(commentFirst.getParentId());
            commentMapper.insertComment(comment);
            //提醒通知功能
            Notification notification =new Notification();
            notification.setGmtCreate(System.currentTimeMillis());
            notification.setType(NotificationEnum.REPLY_COMMENT.getType());
            notification.setOuterId(comment.getParentId());
            notification.setNotifier(comment.getCommentator());
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
            notification.setReceiver(questionCreate.getCreater());
            notification.setQuestionId(commentFirst.getParentId());

            //插入提醒信息
            notificationMapper.insertNotification(notification);

        }else {
            //回复问题
            Question question =questionMapper.getById(comment.getParentId());
            if (question ==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insertComment(comment);
            Notification notification =new Notification();
            notification.setGmtCreate(System.currentTimeMillis());
            notification.setType(NotificationEnum.REPLY_QUESTION.getType());
            notification.setOuterId(comment.getParentId());
            notification.setNotifier(comment.getCommentator());
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
            notification.setReceiver(question.getCreater());
            notification.setQuestionId(question.getId());
            notificationMapper.insertNotification(notification);
        }
    }

    public Integer inCommentCount(Integer id){
        Integer count =commentMapper.commentCount(id);
        questionMapper.updateCommentCount(count,id);
        return count;
//        questionMapper.updateCommentCount(count,id);
    }

    public List<CommentDTO> listByQuestionId(Integer id) {
        List<Comment> comments=questionMapper.selectByQuestionId(id);
        List<CommentDTO> commentDTOS =new ArrayList<>();
        if(comments.size()==0){
            return new ArrayList<>();
        }

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
            commentDTO.setCommentCount(comment.getCommentCount());
            User user = userMapper.findByCreater(comment.getCommentator());
            commentDTO.setUser(user);
            commentDTOS.add(commentDTO);
        }
        System.out.println(commentDTOS.size());
        return commentDTOS;

    }




/**
    public List<CommentDTO> listByIdForTwo(Integer id) {
        List<Comment> comments =commentMapper.selectById(id);
        List<CommentDTO> commentDTOS =new ArrayList<>();
       if (comments.size()==0){
           return new ArrayList<>();
       }
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setParentId(comment.getParentId());
            commentDTO.setLikeCount(comment.getLikeCount());
            commentDTO.setType(comment.getType());
            commentDTO.setCommentator(comment.getCommentator());
            commentDTO.setContent(comment.getContent());
            commentDTO.setGmtCreate(comment.getGmtCreate());
            commentDTO.setGmtModified(comment.getGmtModified());
            commentDTO.setCommentCount(comment.getCommentCount());
            User user = userMapper.findByCreater(comment.getCommentator());
            commentDTO.setUser(user);
            commentDTOS.add(commentDTO);

        }
        return commentDTOS;
    }
*/

    public CommentDTO selectById(Integer id) {
        Comment comment =commentMapper.selectByCommentId(id);
        CommentDTO commentDTO = new CommentDTO();

        if (comment==null){
            return new CommentDTO();
        }
        commentDTO.setId(comment.getId());
        commentDTO.setParentId(comment.getParentId());
        commentDTO.setLikeCount(comment.getLikeCount());
        commentDTO.setType(comment.getType());
        commentDTO.setCommentator(comment.getCommentator());
        commentDTO.setContent(comment.getContent());
        commentDTO.setGmtCreate(comment.getGmtCreate());
        commentDTO.setGmtModified(comment.getGmtModified());
        User user = userMapper.findByCreater(comment.getCommentator());
        commentDTO.setUser(user);
        return commentDTO;
    }

    public List<Comment> selectBycommentParentId(Integer parentId) {
        List<Comment> comments = commentMapper.selectById(parentId);

        return comments;
    }

    @Transactional
    public void update2CommentCount(Integer count,Integer id) {
        commentMapper.updateCountById(count,id);
    }
}
