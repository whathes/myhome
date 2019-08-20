package com.myhome.myhome.service;

import com.myhome.myhome.mapper.LikeCountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeCountService {

    @Autowired
    LikeCountMapper likeCountMapper;
    public void likeUp(Integer count ,Integer id){


    }

}
