package com.web.service.impl;

import com.web.mapper.UserInformationMapper;
import com.web.modle.LayuiTableResultUtil;
import com.web.modle.User;
import com.web.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/13 10:26
 * @describe
 */
@Service
public class UserInformationServiceImpl implements UserInformationService {

    @Autowired
    private UserInformationMapper mapper;

    /**
     * 根据用户名查询用户的信息
     * @param userName
     * @return
     */
    @Override
    public List<User> userInformation(String userName) {
        return mapper.userInformation(userName);
    }

    /**
     * 修改数据库中数据，并返回数据到前端
     * @param uid
     * @param username
     * @param phone
     * @param email
     * @param gender
     * @param birthday
     * @param department
     * @param position
     * @return
     */
    @Override
    public LayuiTableResultUtil updateUser(int uid, String username, String phone, String email,
                                           String gender, String birthday, String department, String position) {

        LayuiTableResultUtil layuiTableResultUtil = new LayuiTableResultUtil();
        mapper.updateUser(uid, username, phone, email, gender, birthday, department, position);

        layuiTableResultUtil.setCode(1);
        layuiTableResultUtil.setMsg("修改成功");

        return layuiTableResultUtil;
    }

    /**
     * 修改用户信息，并向前端返回信息（个人信息修改）
     * @param uid
     * @param username
     * @param phone
     * @param email
     * @param gender
     * @param birthday
     * @return
     */
    @Override
    public LayuiTableResultUtil personalModification(int uid, String username, String phone, String email, String gender, String birthday) {
        LayuiTableResultUtil layuiTableResultUtil = new LayuiTableResultUtil();
        mapper.personalModification(uid, username, phone, email, gender, birthday);
        List<User> user = mapper.getUserId(uid);

        layuiTableResultUtil.setCode(1);
        layuiTableResultUtil.setMsg("修改成功");
        layuiTableResultUtil.setData(user);

        return layuiTableResultUtil;
    }
}

