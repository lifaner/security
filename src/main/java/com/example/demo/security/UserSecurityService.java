package com.example.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Create by lifan.
 * Date: 2018/7/6.
 * Time: 13:24.
 */
@Component
@Slf4j
public class UserSecurityService implements UserDetailsService {

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //todo 根据用户名查询 找到用户 密码 与 权限

        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        User user = new User(username, "123456",
                AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
//        log.info("user={}",user);
//        System.out.println(build.getPassword());
        return user;
    }

}
