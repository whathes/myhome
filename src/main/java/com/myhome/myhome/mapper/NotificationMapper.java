package com.myhome.myhome.mapper;

import com.myhome.myhome.dto.NotificationDTO;
import com.myhome.myhome.model.Notification;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NotificationMapper {
    @Insert("insert into notification(notifier, receiver,outer_id,type,gmt_create,status,question_id) values(#{notifier},#{receiver},#{outerId},#{type},#{gmtCreate},#{status},#{questionId})")
    void insertNotification(Notification notification);

    @Select("select count(*) FROM notification WHERE receiver =#{id}")
    Integer countByUserId(@Param("id") Integer id);

    @Select("select * from notification where receiver=#{id}")
    List<NotificationDTO> selectByReceiver(@Param("id") Integer id);

    @Select("select * from notification where receiver=#{userId} limit #{offset} , #{size}")
    List<Notification> listByReceiver(@Param("userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
}
