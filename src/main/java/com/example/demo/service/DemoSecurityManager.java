package com.example.demo.service;

import com.example.demo.utils.JwtTokenUtil;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
/**
 * Create by lifan.
 * Date: 2018/7/9.
 * Time: 13:45.
 */

@Service
public class DemoSecurityManager {
    final static Logger logger = LoggerFactory.getLogger(DemoSecurityManager.class);
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Pair<Boolean,UserDetails> valid(String authToken) {
        if(authToken==null){
            return new ImmutablePair<>(false,null);
        }
        String username = jwtTokenUtil.getUsernameFromToken(authToken);

        logger.info("JwtAuthenticationTokenFilter checking authentication, userName = " + username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            logger.info("JwtAuthenticationTokenFilter loadUserByUsername by userName = " + username
                    + " return userDetails " + userDetails.getUsername());

            boolean validateToken = jwtTokenUtil.validateToken(authToken, userDetails);

            logger.info("++++++++++++++++++++++++JwtAuthenticationTokenFilter validateToken = " + validateToken);
            return new ImmutablePair<>(validateToken,userDetails);
        }
        return new ImmutablePair<>(false,null);
    }


}
