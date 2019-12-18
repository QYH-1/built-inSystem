package com.web.mapper;

import com.web.modle.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/10/14 16:03
 * @describe
 */
@Mapper  //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
@Repository
public interface LoginMapper {
    /**
     * 登录
     * @return
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    List<User> login(@Param("username") String name, @Param("password") String password);
}

