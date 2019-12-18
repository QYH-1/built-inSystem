package com.web.mapper;

import com.web.modle.Announcement;
import com.web.modle.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/15 14:37
 * @describe
 */
@Mapper
@Repository
public interface dataDisplayMapper {
    /**
     * 查询公告具体信息
     * @param anName
     * @return
     */
    @Select("select * from announcement where aName= #{anName}")
    List<Announcement> getAnnouncementData(@Param("anName") String anName);

    /**
     * 查询新闻具体信息
     * @param newsName
     * @return
     */
    @Select("select * from news where newsName= #{newsName}")
    List<News> getNewsNameData(@Param("newsName") String newsName);

}
