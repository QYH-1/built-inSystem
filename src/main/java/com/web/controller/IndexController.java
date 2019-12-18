package com.web.controller;

import com.web.modle.Announcement;
import com.web.modle.News;
import com.web.modle.Page;
import com.web.service.AnnouncementService;
import com.web.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/10/16 15:02
 * @describe
 */
@Controller
public class IndexController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NewsService newsService;
    @Autowired
    private AnnouncementService announcementService;

    /**
     * 跳转到全部新闻页，并实现分页
     *
     * @param currentPage 当前页
     * @param model
     * @return
     */
    @RequestMapping(value = "/allNews")
    public String findNewsList(String currentPage, Model model) {
        if (currentPage == null) {
            currentPage = "1";
        }
        String rows = "8";
        Page<News> byNews = newsService.findByNews(currentPage, rows);
        model.addAttribute("newList", byNews);
        return "allNews";
    }

    /**
     * 跳转到全部新闻页，并实现分页
     *
     * @param currentPage 当前页
     * @param model
     * @return
     */
    @RequestMapping(value = "/allAnnouncement")
    public String findAnnouncementList(String currentPage, Model model) {
        if (currentPage == null) {
            currentPage = "1";
        }
        String rows = "8";
        Page<Announcement> byAnnouncement = announcementService.findByAnnouncement(currentPage, rows);
        model.addAttribute("announcementList", byAnnouncement);
        return "allAnnouncement";
    }
}
