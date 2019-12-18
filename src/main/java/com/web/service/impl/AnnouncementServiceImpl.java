package com.web.service.impl;

import com.web.mapper.AnnouncementMapper;
import com.web.modle.Announcement;
import com.web.modle.LayuiTableResultUtil;
import com.web.modle.Page;
import com.web.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/11 16:26
 * @describe
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    /**
     * 返回所有公告
     * @return
     */
    @Override
    public List<Announcement> getAllAnnouncement() {
        return announcementMapper.getAllAnnouncement();
    }

    /**
     * 返回最新公告
     * @return
     */
    @Override
    public List<Announcement> getAnnouncement() {
        return announcementMapper.getAnnouncement();
    }
    /**
     * 获取当前页显示的公告数据
     *
     * @param _currentPage
     * @param _rows
     * @return
     */
    @Override
    public Page<Announcement> findByAnnouncement(String _currentPage, String _rows) {
        Page<Announcement> page = new Page<>();
        //获取当前页
        int currentPage = Integer.parseInt(_currentPage);
        //获取当前页显示数据数
        int rows = Integer.parseInt(_rows);
        //获取新闻的总数
        int totalCount = announcementMapper.getAllAnnouncement().size();
        //总的页数
        int totalPage = (totalCount % rows == 0) ? (totalCount / rows) : (totalCount / rows) + 1;
        //起始位置，从第几条数据开始取
        int start = (currentPage - 1) * rows;
        /**
         * 在数据库中获取数据
         */
        List<Announcement> list = announcementMapper.findByAnnouncement(start,rows);
        page.setRows(rows);
        page.setCurrentPage(currentPage);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);
        page.setList(list);
        return page;
    }

    /**
     * 添加公告
     * @param aName
     * @param aPublisher
     * @param aDepartment
     * @param aTime
     * @param aPlate
     * @param aContent
     * @return
     */
    @Override
    public LayuiTableResultUtil addAnnouncement(String aName, String aPublisher, String aDepartment, String aTime, String aPlate, String aContent) {

        LayuiTableResultUtil layuiTableResultUtil = new LayuiTableResultUtil();
        announcementMapper.addAnnouncement(aName, aPublisher, aDepartment, aTime, aPlate, aContent);
        List<Announcement> announcement = announcementMapper.announcementNumber(aName);
        if(announcement.size()>=1){
            layuiTableResultUtil.setCode(1);
            layuiTableResultUtil.setMsg("添加成功");
            layuiTableResultUtil.setData(announcement);
        }else {
            layuiTableResultUtil.setCode(2);
            layuiTableResultUtil.setMsg("添加失败");
            layuiTableResultUtil.setData(announcement);
        }
        return layuiTableResultUtil;
    }
}
