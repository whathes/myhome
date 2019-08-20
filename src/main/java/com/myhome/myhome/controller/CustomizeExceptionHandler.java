package com.myhome.myhome.controller;

import com.alibaba.fastjson.JSON;
import com.myhome.myhome.dto.ResultDTO;
import com.myhome.myhome.exception.CustomizeErrorCode;
import com.myhome.myhome.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model,
                        HttpServletResponse response){
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            ResultDTO resultDTO =null;
            if (ex instanceof CustomizeException){
//                resultDTO=ResultDTO.errprOf((CustomizeException) ex);
            }else {
                resultDTO=ResultDTO.errprOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer =response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            }catch (IOException ioe){

            }
            return null;

        }else {
            if (ex instanceof CustomizeException){

                model.addAttribute("message",ex.getMessage());
            }else {

                model.addAttribute("message","服务器爆炸Boom！");
            }
            return new ModelAndView("error");

        }


    }
    private HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode==null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
