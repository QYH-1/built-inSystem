package com.web.controller;

import com.web.constant.UserLogin;
import com.web.modle.User;
import com.web.service.AnnouncementService;
import com.web.service.LoginService;
import com.web.service.NewsService;
import com.web.service.impl.LoginServiceImpl;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/10/15 17:01
 * @describe
 */
@Controller
@Slf4j
@Value
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginServiceImpl loginServiceImpl;
    @Autowired
    private LoginService loginService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private AnnouncementService announcementService;

    /**
     * 通过账号密码登录
     *
     * @param username
     * @param password
     * @param map
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String loginCon(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           Map<String, Object> map, HttpSession session, Model model, HttpServletRequest request) {

        List<User> user = loginService.login(username, password);
        logger.info(String.valueOf(user));
        logger.info(user.get(0).getAvatarImgUrl());
        //向主界面传递当前用户的用户名
        session.setAttribute("tname", username);
        //当前用户id
        session.setAttribute("uId", user.get(0).getUId());

        logger.info(user.get(0).getAvatarImgUrl());
        //当前用户头像路径
        String urlImage = null;
        if (user.get(0).getAvatarImgUrl() == null) {
            urlImage = "../images/common/ico_uer.gif";
        } else {
            urlImage = "../images/avatarImg/" + user.get(0).getAvatarImgUrl();

        }
        logger.info(urlImage);
        session.setAttribute("avatarImgUrl", urlImage);
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        // logger.info("登录时间",df);// new Date()为获取当前系统时间

        //将获取到的用户的值赋值给全局变量
        UserLogin userLogin = new UserLogin();
        userLogin.userName = username;
        userLogin.uid = user.get(0).getUId();
        userLogin.userType = user.get(0).getUserType();

        if (user.size() > 0) {
            //登陆成功，防止表单重复提交，可以重定向到主页
            model.addAttribute("contents", newsService.getNews());
            model.addAttribute("announcement", announcementService.getAnnouncement());
            /*如果已经存在Session的话，直接返回它；没有就创建一个，再返回
             * 当然Session是自动放在response中的Header中的，这里不用做其他处理*/
            request.getSession();
            return "index";
        } else {
            //登陆失败
            map.put("msg", "用户名或密码错误,请重新输入!");
            return "login";
        }
    }

    /**
     * 注销登录
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public String logout(HttpSession session) {

        session.invalidate();//关闭session
        return "login";
    }
}

