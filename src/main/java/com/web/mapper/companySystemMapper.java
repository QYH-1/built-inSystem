package com.web.mapper;

import com.web.modle.Institution;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/19 11:48
 * @describe
 */
@Mapper
@Repository
public interface companySystemMapper {

    /**
     * 获取当前制度的信息
     * @param iName
     * @return
     */
    @Select("select * from institution where iName = #{iName}")
    List<Institution> institutionInformation(@Param("iName") String iName);

    /**
     * 获取所有制度的某些列，用于通讯录
     * @return
     */
    @Select("select* from institution order by iId desc limit #{start},#{pageSize}")
    List<Institution> getAll(@Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * 获取所有的制度，包括当前制度
     * @return
     */
    @Select("select* from institution")
    List<Institution> getInstitution();

    /**
     * 根据类型查找制度
     * @param start
     * @param pageSize
     * @param iType
     * @return
     */
    @Select("select* from institution where iType =#{iType} order by uid desc limit #{start},#{pageSize}")
    List<Institution> getTypeInstitution(@Param("start") int start, @Param("pageSize") int pageSize, @Param("iType") String iType);

    /**
     * 获取所有的制度
     *
     * @return
     */
    @Select("select * from institution order by iTime desc")
    List<Institution> getAllInstitution();

    /**
     * 查找当前页面的数据
     *
     * @param start
     * @param rows
     * @return
     */
    @Select("select * from institution order by iTime desc limit #{start},#{rows}")
    List<Institution> findByInstitution(@Param("start") int start, @Param("rows") int rows);
}
