package com.web.service.impl;

import com.web.mapper.NewsMapper;
import com.web.modle.Announcement;
import com.web.modle.LayuiTableResultUtil;
import com.web.modle.News;
import com.web.modle.Page;
import com.web.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/8 16:59
 * @describe 实现NewsService中的方法，为index界面中的新闻模块回写数据
 */
@Slf4j
@Service
@Mapper
public class NewsServiceImpl implements NewsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private NewsMapper mapper;

    /**
     * 返回全部新闻
     *
     * @return
     */
    @Override
    public List<News> getAllNews() {
        logger.info(String.valueOf(mapper.getAllNews()));
        return mapper.getAllNews();
    }

    /**
     * 返回最新新闻
     *
     * @return
     */
    @Override
    public List<News> getNews() {
        return mapper.getNews();
    }

    /**
     * 获取当前页显示的数据
     *
     * @param _currentPage
     * @param _rows
     * @return
     */
    @Override
    public Page<News> findByNews(String _currentPage, String _rows) {
        Page<News> page = new Page<>();
        //获取当前页
        int currentPage = Integer.parseInt(_currentPage);
        //获取当前页显示数据数
        int rows = Integer.parseInt(_rows);
        //获取新闻的总数
        int totalCount = mapper.getAllNews().size();
        //总的页数
        int totalPage = (totalCount % rows == 0) ? (totalCount / rows) : (totalCount / rows) + 1;
        //起始位置，从第几条数据开始取
        int start = (currentPage - 1) * rows;
        /**
         * 在数据库中获取数据
         */
        List<News> list = mapper.findByNews(start,rows);

        page.setRows(rows);
        page.setCurrentPage(currentPage);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);
        page.setList(list);
        return page;
    }

    @Override
    public LayuiTableResultUtil addNews(String newsName, String nPublisher, String nDepartment, String nTime, String nPlate, String nContent) {

        LayuiTableResultUtil layuiTableResultUtil = new LayuiTableResultUtil();
        mapper.addNews(newsName, nPublisher, nDepartment, nTime, nPlate, nContent);
        List<Announcement> announcement = mapper.newsNumber(newsName);
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
