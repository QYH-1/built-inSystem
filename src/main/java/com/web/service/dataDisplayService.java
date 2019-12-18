package com.web.service;


import com.web.modle.Announcement;
import com.web.modle.News;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/15 14:35
 * @describe
 */
public interface dataDisplayService {

    /**
     * 通过公告名获取公告信息
     * @param anName
     * @return
     */
    List<Announcement> getAnnouncementData(String anName);

    /**
     * 通过新闻名名获取新闻信息
     * @param newsName
     * @return
     */
    List<News> getNewsData(String newsName);
}
