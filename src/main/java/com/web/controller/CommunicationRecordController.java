package com.web.controller;

import com.web.modle.LayuiTableResultUtil;
import com.web.service.CommunicationRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/13 14:54
 * @describe
 */
@Controller
public class CommunicationRecordController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CommunicationRecordService service;

    @RequestMapping(value = "/communicationRecord")
    public String communicationRecord(HttpSession session) {
        String url = null;
        String userName = (String) session.getAttribute("tname");

        logger.info(userName);
        if (userName.equals("admin")) {
            url = "communicationRecord";
        } else {
            url = "communicationRecord-user";
        }
        return url;
    }

    /**
     * 给出所有的用户列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/UserList")
    @ResponseBody
    public LayuiTableResultUtil UserList(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                         @RequestParam(required = false, defaultValue = "10") int pageSize) {
        logger.info("获取用户列表");
        //实例化封装类
        LayuiTableResultUtil list = new LayuiTableResultUtil();
        try {
            logger.info(String.valueOf(pageNum));
            logger.info(String.valueOf(pageSize));

            list = service.getALLUser(pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("用户列表查询异常！", e);
        }
        logger.info(String.valueOf(list));
        return list;
    }

    /**
     * 根据条件查询人员并分页
     *
     * @param username
     * @param department
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/effectiveUser")
    @ResponseBody
    public LayuiTableResultUtil effectiveUser(String username, String department, @RequestParam(required = false, defaultValue = "1") int pageNum,
                                              @RequestParam(required = false, defaultValue = "10") int pageSize) {

        logger.info("查找用户");
        logger.info(username);
        logger.info(department);
        //实例化封装类
        LayuiTableResultUtil list = new LayuiTableResultUtil();
        //根据部门查找人
        if (username.equals("") && department.equals("")) {
            try {
                list = service.getALLUser(pageNum, pageSize);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("用户列表查询异常！", e);
            }
        } else if (username.equals("") && !department.equals("")) {
            try {
                list = service.getDepartmentUser(pageNum, pageSize, department);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("用户列表查询异常！", e);
            }
        } else {
            try {
                list = service.getUsernameUser(username);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("用户列表查询异常！", e);
            }
        }
        return list;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public LayuiTableResultUtil deleteUser(int uid) {

        logger.info("删除用户： " + uid);
        LayuiTableResultUtil list = new LayuiTableResultUtil();
        list = service.deleteUsernameUser(uid);
        return list;
    }

}
