package com.myhome.myhome.controller;

import com.myhome.myhome.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController {




    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO uplode(){

        FileDTO fileDTO=new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/imgs/fly.png");




        return fileDTO;
    }

}
