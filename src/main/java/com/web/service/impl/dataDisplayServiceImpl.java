package com.web.service.impl;

import com.web.mapper.dataDisplayMapper;
import com.web.modle.Announcement;
import com.web.modle.News;
import com.web.service.dataDisplayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/15 14:36
 * @describe
 */
@Service
public class dataDisplayServiceImpl implements dataDisplayService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private dataDisplayMapper anMapper;

    /**
     * 查询公告信息
     *
     * @param anName
     * @return
     */
    @Override
    public List<Announcement> getAnnouncementData(String anName) {

        List<Announcement> an = anMapper.getAnnouncementData(anName);
        return an;
    }

    /**
     * 查询新闻信息
     *
     * @param newsName
     * @return
     */
    @Override
    public List<News> getNewsData(String newsName) {
        List<News> news = anMapper.getNewsNameData(newsName);
        return news;
    }
}
