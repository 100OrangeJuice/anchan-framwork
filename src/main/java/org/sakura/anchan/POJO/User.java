package org.sakura.anchan.POJO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    /**用户id*/
    private Integer id;
    /**用户名*/
    private String username;
    /**密码*/
    private String password;
    /**邮箱*/
    private String email;
    /**手机号*/
    private String phone;
    /**修改密码的问题*/
    private String question;
    /**修改密码的问题的答案*/
    private String answer;
    /**角色，用户or管理员*/
    private Integer role;
    /**创建时间*/
    private Date createTime;
    /**更新时间*/
    private Date updateTime;


}