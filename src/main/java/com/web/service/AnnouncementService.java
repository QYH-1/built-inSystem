package com.web.service;


import com.web.modle.Announcement;
import com.web.modle.LayuiTableResultUtil;
import com.web.modle.Page;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/11 16:24
 * @describe
 */
public interface AnnouncementService {
    /**
     * 获取所有公告
     *
     * @return
     */
    List<Announcement> getAllAnnouncement();

    /**
     * 获取最新公告
     *
     * @return
     */
    List<Announcement> getAnnouncement();
    /**
     * 获取当前页显示的数据
     * @param currentPage
     * @param rows
     * @return
     */
    Page<Announcement> findByAnnouncement(String currentPage, String rows);

    /**
     * 添加公告
     * @param aNam
     * @param aPublisher
     * @param aDepartment
     * @param aTime
     * @param aPlate
     * @param aContent
     * @return
     */
    LayuiTableResultUtil addAnnouncement(String aNam, String aPublisher, String aDepartment, String aTime, String aPlate, String aContent);
}

