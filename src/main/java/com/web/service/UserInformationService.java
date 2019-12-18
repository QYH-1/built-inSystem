package com.web.service;


import com.web.modle.LayuiTableResultUtil;
import com.web.modle.User;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/13 10:23
 * @describe
 */
public interface UserInformationService {
    /**
     * 获取登录用户的个人信息
     * @param userName
     * @return
     */
    List<User> userInformation(String userName);

    /**
     *根据用户名修改用户
     * @param uid
     * @return
     */
    LayuiTableResultUtil updateUser(int uid, String username, String phone, String email, String gender, String birthday, String department, String position);

    /**
     *根据用户名修改用户（个人信息修改）
     * @param uid
     * @return
     */
    LayuiTableResultUtil personalModification(int uid, String username, String phone, String email, String gender, String birthday);
}
