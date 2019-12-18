package com.web.mapper;

import com.web.modle.Institution;
import com.web.modle.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/12/16 16:35
 * @describe
 */
@Mapper
@Repository
public interface KnowledgeMapper {

    /**
     * 获取所有制度
     *
     * @return
     */
    @Select("select * from institution order by iId asc limit #{start},#{pageSize}")
    List<Institution> getAllInstitution(@Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * 根据部门查找制度
     *
     * @param start
     * @param pageSize
     * @param iDepartment
     * @return
     */
    @Select("select* from institution where iDepartment =#{iDepartment} order by iId asc limit #{start},#{pageSize}")
    List<Institution> getDepartmentInstitution(@Param("start") int start, @Param("pageSize") int pageSize, @Param("iDepartment") String iDepartment);

    /**
     * 获取当前制度的数据
     *
     * @param iName
     * @return
     */
    @Select("select * from institution where iName = #{iName}")
    List<Institution> institutionInformation(@Param("iName") String iName);

}
