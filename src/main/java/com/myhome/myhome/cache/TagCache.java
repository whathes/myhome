package com.myhome.myhome.cache;

import com.myhome.myhome.dto.TagDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagCache {
    public static List<TagDTO> get(){
        List<TagDTO> tagDTOS =new ArrayList<>();
        TagDTO program =new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js","php","python","java","html"));
        tagDTOS.add(program);

        TagDTO framework =new TagDTO();
        framework.setCategoryName("开发框架");
        framework.setTags(Arrays.asList("spring","struts","django","SpringBoot"));
        tagDTOS.add(framework);

        TagDTO server =new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("apache","tomcat","unix","window-server","docker"));
        tagDTOS.add(server);

        TagDTO dataBase =new TagDTO();
        dataBase.setCategoryName("数据库");
        dataBase.setTags(Arrays.asList("MySQL","sqlserver","redis","oracle","mongoDB"));
        tagDTOS.add(dataBase);

        TagDTO tool =new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git","vim","idea","eclipse","chrome","postman","navicat"));
        tagDTOS.add(tool);


        return tagDTOS;
    }
}
