package com.web.controller;

import com.web.modle.Announcement;
import com.web.modle.LayuiTableResultUtil;
import com.web.modle.News;
import com.web.service.AnnouncementService;
import com.web.service.dataDisplayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/15 14:34
 * @describe
 */
@Controller
public class dataDisplayController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private dataDisplayService dataService;
    @Autowired
    private AnnouncementService announcementService;

    /**
     * 获取公告信息，返回给前端弹窗
     *
     * @param anName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/announcementsData")
    public List<Announcement> getAnnouncementData(String anName) {

        log.info(anName);
        log.info(String.valueOf(dataService.getAnnouncementData(anName)));
        List<Announcement> announcements = dataService.getAnnouncementData(anName);
        return announcements;
    }

    /**
     * 获取新闻信息，返回给前端弹窗
     *
     * @param newsName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/newsData")
    public List<News> getNewsData(String newsName) {

        log.info(newsName);
        log.info(String.valueOf(dataService.getAnnouncementData(newsName)));
        List<News> news = dataService.getNewsData(newsName);
        return news;
    }

    /**
     * 添加公告
     * @param request
     * @param aName
     * @param aPublisher
     * @param aDepartment
     * @param aTime
     * @param aPlate
     * @param aContent
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addAnnouncement")
    public LayuiTableResultUtil addAnnouncement(HttpServletRequest request, @RequestParam("aName") String aName, @RequestParam("aPublisher") String aPublisher,
                                                @RequestParam("aDepartment") String aDepartment, @RequestParam("aTime") String aTime, @RequestParam("aPlate") String aPlate, @RequestParam("aContent") String aContent) {
        log.info(aName + aPublisher + aDepartment + aTime + aPlate + aContent);
        LayuiTableResultUtil list = new LayuiTableResultUtil();
        list = announcementService.addAnnouncement(aName, aPublisher, aDepartment, aTime, aPlate, aContent);

        return list;
    }

    /**
     * 添加新闻
     * @param request
     * @param newsName
     * @param nPublisher
     * @param nDepartment
     * @param nTime
     * @param nPlate
     * @param nContent
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addNews")
    public LayuiTableResultUtil addNews(HttpServletRequest request, @RequestParam("newsName") String newsName, @RequestParam("nPublisher") String nPublisher,
                                        @RequestParam("nDepartment") String nDepartment, @RequestParam("nTime") String nTime, @RequestParam("nPlate") String nPlate, @RequestParam("nContent") String nContent) {
        log.info(newsName + nPublisher + nDepartment + nTime + nPlate + nContent);
        LayuiTableResultUtil list = new LayuiTableResultUtil();
        list = announcementService.addAnnouncement(newsName, nPublisher, nDepartment,nTime, nPlate, nContent);

        return list;
    }
}
