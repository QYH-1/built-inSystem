package com.web.mapper;

import com.web.modle.Announcement;
import com.web.modle.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/8 17:01
 * @describe
 */
@Mapper  //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
@Repository
public interface NewsMapper {
    /**
     * 获取新闻
     *
     * @return
     */
    @Select("select * from news order by nTime desc")
    List<News> getAllNews();

    /**
     * 获取最新新闻
     *
     * @return
     */
    @Select("select * from news order by nTime desc limit 5")
    List<News> getNews();

    /**
     * 查找当前页面的数据
     * @param start
     * @param rows
     * @return
     */
    @Select("select * from news order by nTime desc limit #{start},#{rows}")
    List<News> findByNews(@Param("start") int start, @Param("rows") int rows);

    /**
     * 查找当前页面的数据
     *
     * @param start
     * @param rows
     * @return
     */
    @Select("select * from announcement order by aTime desc limit #{start},#{rows}")
    List<Announcement> findByAnnouncement(@Param("start") int start, @Param("rows") int rows);

    /**
     * 添加新闻
     * @param newsName
     * @param nPublisher
     * @param nDepartment
     * @param nTime
     * @param nPlate
     * @param nContent
     */
    @Insert("Insert into news (newsName,nPublisher,nTime,nDepartment,nContent,nPlate) values (#{newsName},#{nPublisher},#{nTime},#{nDepartment},#{nContent},#{nPlate})")
    void addNews(@Param("newsName") String newsName, @Param("nPublisher") String nPublisher, @Param("nDepartment") String nDepartment, @Param("nTime") String nTime,
                 @Param("nPlate") String nPlate, @Param("nContent") String nContent);

    /**
     * 根据名字查询新闻
     *
     * @return
     */
    @Select("select * from news where newsName =#{newsName}")
    List<Announcement> newsNumber(@Param("newsName") String newsName);
}
