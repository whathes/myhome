package com.myhome.myhome.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LikeCountMapper {
    @Update("update question set like_count=#{count} where id=#{id}")
    public void likeCountUp(@Param("count") Integer count,@Param("id") Integer id);

}
