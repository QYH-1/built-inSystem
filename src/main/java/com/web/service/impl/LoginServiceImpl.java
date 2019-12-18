package com.web.service.impl;

import com.web.mapper.LoginMapper;
import com.web.modle.User;
import com.web.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/10/14 16:09
 * @describe 实现loginService中的方法
 */
@Slf4j
@Service
@Mapper
public class LoginServiceImpl implements LoginService {

    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private LoginService loginService;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public List<User> login(String username, String password) {

        return loginMapper.login(username,password);
    }
}
