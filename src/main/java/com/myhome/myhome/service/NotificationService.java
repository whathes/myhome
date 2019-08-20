package com.myhome.myhome.service;

import com.myhome.myhome.dto.NotificationDTO;
import com.myhome.myhome.dto.PaginationDTO;
import com.myhome.myhome.enums.NotificationEnum;
import com.myhome.myhome.mapper.CommentMapper;
import com.myhome.myhome.mapper.NotificationMapper;
import com.myhome.myhome.mapper.QuestionMapper;
import com.myhome.myhome.mapper.UserMapper;
import com.myhome.myhome.model.Comment;
import com.myhome.myhome.model.Notification;
import com.myhome.myhome.model.Question;
import com.myhome.myhome.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO listReplies(Integer id, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO=new PaginationDTO<>();
        Integer totalCount = notificationMapper.countByUserId(id);
        Integer totalPage;

        if (totalCount%size==0){
            totalPage  = totalCount / size;
        }else{
            totalPage  = totalCount / size + 1;
        }
        if (totalPage==0){
            totalPage=1;
        }
        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        paginationDTO.setPagination(totalPage,page);
        //分页
        Integer offset=size*(page-1);




        //将问题属性加入Page的数据

        List<Notification> notifications=notificationMapper.listByReceiver(id,offset,size);
        if (notifications.size()==0){
            return paginationDTO;
        }

        List<NotificationDTO> notificationDTOS = new ArrayList<>();

        for (Notification notification : notifications) {


            NotificationDTO notificationDTO = new NotificationDTO();
            User user = userMapper.findById(notification.getNotifier());
            Question question=questionMapper.getById(notification.getQuestionId());
            BeanUtils.copyProperties(notification,notificationDTO);

            notificationDTO.setType("回复了你的问题：");
            notificationDTO.setGmtCreate(notification.getGmtCreate());
            notificationDTO.setOuterTitle(question.getTitle());
            notificationDTO.setNotifier(user.getId());
            notificationDTO.setNotifierName(user.getName());
            notificationDTOS.add(notificationDTO);
        }



        paginationDTO.setData(notificationDTOS);


        return paginationDTO;
    }
}
