package com.web.service;


import com.web.modle.LayuiTableResultUtil;
import com.web.modle.News;
import com.web.modle.Page;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/8 16:53
 * @describe 新闻的服务类
 */
public interface NewsService {
    /**
     * 获取全部新闻
     *
     * @return
     */
    List<News> getAllNews();

    /**
     * 获取最新新闻
     *
     * @return
     */
    List<News> getNews();

    /**
     * 获取当前页显示的数据
     * @param currentPage
     * @param rows
     * @return
     */
    Page<News> findByNews(String currentPage, String rows);

    /**
     * 添加新闻
     * @param newsName
     * @param nPublisher
     * @param nDepartment
     * @param nTime
     * @param nPlate
     * @param nContent
     * @return
     */
    LayuiTableResultUtil addNews(String newsName, String nPublisher, String nDepartment, String nTime, String nPlate, String nContent);
}
