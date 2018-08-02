package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Create by lifan.
 * Date: 2018/7/6.
 * Time: 13:26.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends JwtUser {

    private long userId;
    private String username;
    private String password;
    private int userType;

    public User() {
        super(null, null, null, null, null);
    }

    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_DEFAULT"));

        //1 系统管理员 2 景区负责人 3 员工 4 公司客服

        String roleName = getRoleNameByType(getUserType());
        if(roleName!=null){

            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" +roleName));
        }
        return grantedAuthorities;
    }

    private String getRoleNameByType(int type) {
        String roleName = null;
        switch (type) {
            case 1:
                roleName = "ADMIN";
                break;
            case 2:
                roleName = "LEADER";
                break;
            case 3:
                roleName = "MEMBER";
                break;
            case 4:
                roleName = "CUSTOMER_SERVICE";
                break;
        }
        return roleName;
    }



}
