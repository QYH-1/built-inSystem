package com.web.service;

import com.web.modle.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/6 15:43
 * @describe
 */
@Service
public interface LoginService {
    /**
     * 通过用户名和密码实现登录
     *
     * @param username
     * @param password
     * @return
     */
    List<User> login(String username, String password);
}
