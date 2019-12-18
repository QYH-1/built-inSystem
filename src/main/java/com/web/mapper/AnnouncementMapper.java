package com.web.mapper;

import com.web.modle.Announcement;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/11 16:27
 * @describe
 */
@Mapper
@Repository
public interface AnnouncementMapper {
    /**
     * 获取所有的公告
     *
     * @return
     */
    @Select("select * from announcement order by aTime desc")
    List<Announcement> getAllAnnouncement();

    /**
     * 获取最新公告
     *
     * @return
     */
    @Select("select * from announcement order by aTime desc limit 5")
    List<Announcement> getAnnouncement();

    /**
     * 查找当前页面的数据
     *
     * @param start
     * @param rows
     * @return
     */
    @Select("select * from announcement order by aTime desc limit #{start},#{rows}")
    List<Announcement> findByAnnouncement(@Param("start") int start, @Param("rows") int rows);

    @Insert("Insert into announcement (aName,aPublisher,aTime,aDepartment,aContent,aPlate) values (#{aName},#{aPublisher},#{aTime},#{aDepartment},#{aContent},#{aPlate})")
    void addAnnouncement(@Param("aName") String aName, @Param("aPublisher") String aPublisher, @Param("aDepartment") String aDepartment, @Param("aTime") String aTime,
                         @Param("aPlate") String aPlate, @Param("aContent") String aContent);

    /**
     * 根据名字查询公告
     *
     * @return
     */
    @Select("select * from announcement where aName =#{aName}")
    List<Announcement> announcementNumber(@Param("aName") String aName);
}
