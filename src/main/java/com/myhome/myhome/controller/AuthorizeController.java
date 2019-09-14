package com.myhome.myhome.controller;

import com.myhome.myhome.dto.AccessTokenDTO;
import com.myhome.myhome.dto.githubUser;
import com.myhome.myhome.model.User;
import com.myhome.myhome.provider.githubProvider;
import com.myhome.myhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private githubProvider githubProvider;


    @Autowired
    private UserService userService;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse){
        AccessTokenDTO accessTokenDTO =new AccessTokenDTO();
//        accessTokenDTO.setClient_id("0473f27e35a5a296e9b2");
        accessTokenDTO.setClient_id(clientId);
//        accessTokenDTO.setClient_secret("aae088a79325f2f4507cf55e3ad7f15be8f7e38d");
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
//        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken =githubProvider.getAccessToken(accessTokenDTO);
        githubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.getName());
        if(githubUser!=null ){
            httpServletRequest.getSession().setAttribute("user",githubUser);
            User user =new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));

            user.setAvatarUrl(githubUser.getAvatarUrl());
            userService.createOrUpdate(user);
            httpServletResponse.addCookie(new Cookie("token",token));
            //重定向
            return "redirect:/";

        }else {

            System.out.println("登陆失败");
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
