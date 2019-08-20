package com.myhome.myhome.mapper;

import com.myhome.myhome.model.Comment;
import com.myhome.myhome.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creater,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creater},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset} , #{size}")
    List<Question> listQuestion(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creater=#{userId} limit #{offset} , #{size}")
    List<Question> listByUserId(@Param("userId") Integer userId,@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Select("select count(1) from question where creater=#{userId}")
    Integer countByUserId(@Param("userId") Integer userId);

    @Select("select * from question where id=#{id}")
    Question getById(@Param("id") Integer id);

    @Update("update question set title =#{title}, description =#{description},gmt_modified=#{gmtModified},tag=#{tag} where id=#{id}")
    void update(Question question);

    @Update("update question set view_count=#{count} where id=#{id}")
    void updateViewCount(@Param("count") Integer count, @Param("id") Integer id);

    @Update("update question set comment_count=#{commentCount} where id=#{parentId}")
    void updateCommentCount(@Param("commentCount") Integer count, @Param("parentId") Integer parentId);

    @Select("select * from comment where parent_id=#{id} and type=1")
    List<Comment> selectByQuestionId(@Param("id") Integer id);

    @Select("select * from question where id!=#{id} and tag regexp #{tag}")
    List<Question> selectTag(@Param("id") Integer id,@Param("tag") String tag);

}
