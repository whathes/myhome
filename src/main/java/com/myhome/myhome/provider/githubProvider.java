package com.myhome.myhome.provider;

import com.alibaba.fastjson.JSON;
import com.myhome.myhome.dto.AccessTokenDTO;
import com.myhome.myhome.dto.githubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class githubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        String s = JSON.toJSONString(accessTokenDTO);
        System.out.println(s);
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            String split = string.split("&")[0].split("=")[1];
            System.out.println(split);
            return split;
        } catch (IOException e) {
        }
        System.out.println("is null");
        return null;
    }

    public githubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);
            githubUser githubUser=JSON.parseObject(string,githubUser.class);
            return githubUser;
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
        return null;
    }



}