package com.web.mapper;

import com.web.modle.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/13 10:27
 * @describe
 */
@Mapper
@Repository
public interface UserInformationMapper {

    /**
     * 获取当前用户的用户信息
     *
     * @param userName
     * @return
     */
    @Select("select * from user where username = #{userName}")
    List<User> userInformation(@Param("userName") String userName);

    /**
     * 获取所有用户的某些列，用于通讯录
     *
     * @return
     */
    @Select("select* from user order by uid asc limit #{start},#{pageSize}")
    List<User> getAllUser(@Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * 获取所有的用户，包括当前用户
     *
     * @return
     */
    @Select("select* from user")
    List<User> getUser();

    /**
     * 根据部门查找人
     *
     * @param start
     * @param pageSize
     * @param department
     * @return
     */
    @Select("select* from user where department =#{condition} order by uid asc limit #{start},#{pageSize}")
    List<User> getDepartmentUser(@Param("start") int start, @Param("pageSize") int pageSize, @Param("condition") String department);

    @Delete("delete from user where uid=#{uid}")
    void deleteUser(int uid);

    /**
     * 查询是否存在某用户
     *
     * @param uid
     * @return
     */
    @Select("select count(*) from user where uid = #{uid}")
    int getUserCount(@Param("uid") int uid);

    /**
     * 修改用户信息用户
     *
     * @param uid
     * @return
     */
    @Update("update user set username=#{username},phone=#{phone},email=#{email},gender=#{gender},birthday=#{birthday},department=#{department},position=#{position} where uid = #{uid}")
    void updateUser(@Param("uid") int uid, @Param("username") String username, @Param("phone") String phone, @Param("email") String email,
                    @Param("gender") String gender, @Param("birthday") String birthday, @Param("department") String department, @Param("position") String position);

    /**
     * 修改用户信息用户（个人信息修改）
     *
     * @param uid
     * @return
     */
    @Update("update user set username=#{username},phone=#{phone},email=#{email},gender=#{gender},birthday=#{birthday} where uid = #{uid}")
    void personalModification(@Param("uid") int uid, @Param("username") String username, @Param("phone") String phone, @Param("email") String email,
                              @Param("gender") String gender, @Param("birthday") String birthday);

    /**
     * 根据id获取用户信息
     *
     * @param uid
     * @return
     */
    @Select("select * from user where uid = #{uid}")
    List<User> getUserId(@Param("uid") int uid);

    /**
     * 修改用户信息用户（个人信息修改）
     *
     * @param uid
     * @return
     */
    @Update("update user set avatarImgUrl=#{avatarImgUrl} where uid = #{uid}")
    void headImg(@Param("uid") int uid, @Param("avatarImgUrl") String avatarImgUrl);
}
