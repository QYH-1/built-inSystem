package com.web.service.impl;

import com.web.mapper.UserInformationMapper;
import com.web.modle.LayuiTableResultUtil;
import com.web.modle.User;
import com.web.service.CommunicationRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/13 14:50
 * @describe
 */
@Service
public class communicationRecordServiceImpl implements CommunicationRecordService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserInformationMapper userInformationMapper;

    /**
     * 获取全部用户
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public LayuiTableResultUtil getALLUser(int pageNum, int pageSize) {
        LayuiTableResultUtil layuiTableResultUtil = new LayuiTableResultUtil();
        //起始位置，从第几条数据开始取
        int start = (pageNum - 1) * pageSize;
        //获取用户的总数
        int totalCount = userInformationMapper.getUser().size();
        List<User> users = userInformationMapper.getAllUser(start,pageSize);

        if (users.size() != 0) {
            layuiTableResultUtil.setTotals(totalCount);
            layuiTableResultUtil.setData(users);

        }
        return layuiTableResultUtil;
    }

    /**
     *根据部门查找人
     * @param pageNum
     * @param pageSize
     * @param department
     * @return
     */
    @Override
    public LayuiTableResultUtil getDepartmentUser(int pageNum, int pageSize, String department) {

        LayuiTableResultUtil layuiTableResultUtil = new LayuiTableResultUtil();
        //起始位置，从第几条数据开始取
        int start = (pageNum - 1) * pageSize;
        List<User> users = userInformationMapper.getDepartmentUser(start,pageSize,department);
        if(users.size() != 0){
            layuiTableResultUtil.setTotals(users.size());
            layuiTableResultUtil.setData(users);
        }
        return layuiTableResultUtil;
    }

    @Override
    public LayuiTableResultUtil getUsernameUser(String username) {
        logger.info(username);
        LayuiTableResultUtil layuiTableResultUtil = new LayuiTableResultUtil();
        List<User> users = userInformationMapper.userInformation(username);
        logger.info(String.valueOf(users));
        if(users.size() != 0){
            layuiTableResultUtil.setTotals(users.size());
            layuiTableResultUtil.setData(users);
        }
        return layuiTableResultUtil;
    }

    @Override
    public LayuiTableResultUtil deleteUsernameUser(int uid) {
        LayuiTableResultUtil layuiTableResultUtil = new LayuiTableResultUtil();
        userInformationMapper.deleteUser(uid);
        int count = userInformationMapper.getUserCount(uid);
        if(count ==0){
            layuiTableResultUtil.setCode(1);
            layuiTableResultUtil.setMsg("删除成功");
        }else {
            layuiTableResultUtil.setCode(0);
            layuiTableResultUtil.setMsg("删除失败");
        }
        return layuiTableResultUtil;
    }

}
