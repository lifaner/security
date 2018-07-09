package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Create by lifan.
 * Date: 2018/7/9.
 * Time: 14:03.
 */
public class TokenUtils {

    private TokenUtils(){

    }

    final static Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    public static String getToken(HttpServletRequest request){
        String authToken = request.getHeader(SecurityUtils.HEAD_KEY);

        if(authToken == null){
            Cookie[] cookies = request.getCookies();
            if(cookies!=null){
                for(Cookie cookie:cookies){
                    if(SecurityUtils.HEAD_KEY.equals(cookie.getName()) ){
                        authToken=	cookie.getValue();
                    }
                }
            }
        }
        return authToken;
    }
}
