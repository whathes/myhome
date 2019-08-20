package com.myhome.myhome.mapper;

import com.myhome.myhome.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment(content,parent_id,commentator,gmt_create,gmt_modified,like_count,type) values(#{content},#{parentId},#{commentator},#{gmtCreate},#{gmtModified},#{likeCount},#{type})")
    void insertComment(Comment comment);

    @Select("select * from comment where parent_id=#{parentId}")
    Comment selectByParentId(@Param("parentId") Integer parentId);

    @Select("select count(*) FROM `comment` WHERE parent_id =#{parentId} and type=2")
    Integer commentCount(@Param("parentId") Integer parentId);

    @Select("select * from comment where id=#{id} and type=2")
    Comment commentByIdForTwo(@Param("id") Integer id);

    @Select("select * from comment where parent_id=#{id} and type=2")
    List<Comment> selectById(Integer id);

    @Select("select * from comment where id=#{id}")
    Comment selectByCommentId(Integer id);

    @Update("update comment set comment_count=#{commentCount} where id=#{id}")
    void updateCountById(@Param("commentCount") Integer commentCount ,@Param("id") Integer id);
}
