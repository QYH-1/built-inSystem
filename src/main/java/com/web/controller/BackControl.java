package com.web.controller;

import com.web.service.AnnouncementService;
import com.web.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/10/17 9:22
 * @describe 所有界面的跳转
 */
@Controller
public class BackControl {
    @Autowired
    private NewsService newsService;
    @Autowired
    private AnnouncementService announcementService;

    /**
     * 跳转转到登录界面
     *
     * @return
     */
    @RequestMapping(value = "/")
    public String login() {

        return "login";
    }

    /**
     * 跳转转到登录界面
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public String getlogin() {

        return "login";
    }

    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("contents", newsService.getNews());
        model.addAttribute("announcement", announcementService.getAnnouncement());
        return "index";
    }

    /**
     * 跳转到知识管理
     *
     * @return
     */
    @RequestMapping(value = "/knowledgeManagement")
    public String knowledgeManagement() {
        return "knowledgeManagement";
    }

    /**
     * 跳转到公告发布界面
     *
     * @return
     */
    @RequestMapping(value = "/announce")
    public String Announce() {
        return "announce";
    }

    /**
     * 跳转到公告发布界面
     *
     * @return
     */
    @RequestMapping(value = "/releaseNews")
    public String releaseNews() {
        return "releaseNews";
    }

    /**
     * @return
     */
    @RequestMapping(value = "/newsDisplay")
    public String newsDisplay() {
        return "newsDisplay";
    }

    /**
     * 个人信息界面
     *
     * @return
     */
    @RequestMapping(value = "/information")
    public String test() {
        return "information";
    }

    /**
     * 工作流程（知识管理）
     *
     * @return
     */
    @RequestMapping(value = "/fileShow")
    public String file() {
        return "fileShow";
    }

    /**
     * 制度列表（知识管理）
     *
     * @return
     */
    @RequestMapping(value = "/companySystemList")
    public String companySystemList() {
        return "companySystemList";
    }

    /**
     * 地质资料（知识管理）
     *
     * @return
     */
    @RequestMapping(value = "/geologicalData")
    public String geologicalData() {
        return "geologicalData";
    }

    /**
     * 模板文件（知识管理）
     *
     * @return
     */
    @RequestMapping(value = "/templateFile")
    public String templateFile() {
        return "templateFile";
    }

    /**
     * 模板文件（知识管理
     *
     * @return
     */
    @RequestMapping(value = "/companySystemDataShow")
    public String companySystemDataShow() {
        return "companySystemDataShow";
    }

    /**
     * 上传头像
     * @return
     */
    @RequestMapping(value = "/uploadUserImage")
    public String uploadUserImage() {
        return "uploadUserImage";
    }
}
