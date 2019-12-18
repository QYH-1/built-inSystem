package com.web.controller;

import com.web.modle.LayuiTableResultUtil;
import com.web.modle.User;
import com.web.service.UserInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/13 10:34
 * @describe
 */
@Controller
public class UserInformationController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserInformationService service;

    /**
     * 获取用户信息
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/UserInformation")
    @ResponseBody
    public List<User> UserInformation(String userName) {
        logger.info(userName);
        List<User> user = service.userInformation(userName);
        return user;
    }

    /**
     * 管理员修改用信息
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
    @RequestMapping(value = "/updateUser")
    @ResponseBody
    public LayuiTableResultUtil updateUser(@RequestParam("uid") int uid, @RequestParam("username") String username, @RequestParam("phone") String phone,
                                           @RequestParam("email") String email, @RequestParam("gender") String gender, @RequestParam("birthday") String birthday, @RequestParam("department") String department, @RequestParam("position") String position) {
        logger.info("---修改用户信息");
        logger.info(uid + "--" + username + "--" + phone + "--" + email + "--" + gender + "--" + birthday + "--" + department + "--" + position);
        LayuiTableResultUtil list = new LayuiTableResultUtil();
        list = service.updateUser(uid, username, phone, email, gender, birthday, department, position);

        //logger.info(String.valueOf(list));
        return list;
    }

    /**
     * 用户个人修改信息
     *
     * @param request
     * @param username
     * @param phone
     * @param email
     * @param gender
     * @param birthday
     * @return
     */
    @RequestMapping(value = "/personalModification")
    @ResponseBody
    public LayuiTableResultUtil personalModification(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("phone") String phone,
                                                     @RequestParam("email") String email, @RequestParam("gender") String gender, @RequestParam("birthday") String birthday) {
        //获取session
        HttpSession session = request.getSession();
        int uid = (int) session.getAttribute("uId");
        logger.info(String.valueOf(uid));

        logger.info("---修改用户信息");
        //logger.info(String.valueOf(uid));
        //logger.info(uid + "--" + username + "--" + phone + "--" + email + "--" + gender + "--" + birthday);
        LayuiTableResultUtil list = new LayuiTableResultUtil();
        list = service.personalModification(uid, username, phone, email, gender, birthday);
        //logger.info(String.valueOf(list));
        return list;
    }

}
