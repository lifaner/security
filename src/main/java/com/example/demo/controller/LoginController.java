package com.example.demo.controller;

import com.example.demo.dto.ManageUserDto;
import com.example.demo.entity.User;
import com.example.demo.utils.JwtTokenUtil;
import com.google.code.kaptcha.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Create by lifan.
 * Date: 2018/7/6.
 * Time: 15:57.
 */
@RestController
@Slf4j
public class LoginController{

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping({"/login/valid"})
    public ResponseEntity login(Model model, String username, String password,
                                           String validateCode, String remember, HttpSession session){
        log.info("username={},passowrd={},validateCode={}",username,password,validateCode);

        String keyword = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (keyword == null) {
            return badRequest("验证码失效");
        }

        if (!keyword.toLowerCase().equals(validateCode.toLowerCase())) {
            return badRequest("验证码不正确，请重新输入");
        }

        if ("".equals(username) || "".equals(password)) {
            return badRequest("用户名或密码不能为空");
        }
        //todo 验证用户是否存在，密码是否正确
        ManageUserDto userDto = new ManageUserDto();
        userDto.setUserId(1000l);
        userDto.setUserName(username);
        String s = DigestUtils.md5Hex(DigestUtils.md5Hex(password) + userDto.getUserId());
        userDto.setUserPassword(s);

        boolean result = s.equals(userDto.getUserPassword());

        if (!result) {
            return badRequest("账号密码不符");
        }

        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        String token = jwtTokenUtil.generateToken(user);

        //todo 验证权限
        HttpHeaders headers = new HttpHeaders();
        headers.add("lifan-alert", string2Unicode("登录成功"));
        headers.add("lifan-params", "login success");
        return ResponseEntity.ok().headers(headers).body(token);
    }

    private ResponseEntity<Object> badRequest(String message) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("lifan-alert", string2Unicode(message));
        headers.add("lifan-params", "login user");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(headers).body(null);
    }

    private static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();

        for(int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }
}
