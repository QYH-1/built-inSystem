package com.web.modle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/10/14 15:59
 * @describe 用户实体类
 */
@Data //相当于getter setter
@AllArgsConstructor //所有参数构造函数
@NoArgsConstructor //没有参数的构造函数
@Builder  //构造一个对象 用法User.builder().id(11).username("111").build();
public class User {
    /**
     * Id
     */
    private int uId;
    /**
     * 姓名
     */
    private String username ;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 性别
     */
    private String gender;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 头像地址
     */
    private String avatarImgUrl;
    /**
     * 最近登录时间
     */
    private String recentlyLanded;
    /**
     * 用户类型
     */
    private int userType;
    /**
     * 用户所在部门
     */
    private String department;
    /**
     * 职位
     */
    private String position;
 }
