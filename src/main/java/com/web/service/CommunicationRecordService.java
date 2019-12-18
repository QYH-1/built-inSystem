package com.web.service;


import com.web.modle.LayuiTableResultUtil;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/13 14:50
 * @describe
 */
public interface CommunicationRecordService {

    /**
     * 获取所有的用户
     * @param pageNum
     * @param pageSize
     * @return
     */
    LayuiTableResultUtil getALLUser(int pageNum, int pageSize);

    /**
     * 获取查询部门的用户
     * @param pageNum
     * @param pageSize
     * @param department
     * @return
     */
    LayuiTableResultUtil getDepartmentUser(int pageNum, int pageSize, String department);

    /**
     *根据用户名查询用户
     * @param username
     * @return
     */
    LayuiTableResultUtil getUsernameUser(String username);

    /**
     *根据用户名删除用户
     * @param uid
     * @return
     */
    LayuiTableResultUtil deleteUsernameUser(int uid);

}
