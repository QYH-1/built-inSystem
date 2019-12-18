package com.web.service.impl;

import com.web.mapper.KnowledgeMapper;
import com.web.modle.Institution;
import com.web.modle.LayuiTableResultUtil;
import com.web.service.KnowledgeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/12/16 16:33
 * @describe
 */
@Service
public class KnowledgeManagementServiceImpl implements KnowledgeManagementService {

    @Autowired
    private KnowledgeMapper mapper;

    @Override
    public LayuiTableResultUtil getALLInstitution(int pageNum, int pageSize) {
        LayuiTableResultUtil layuiTableResultUtil = new LayuiTableResultUtil();
        //起始位置，从第几条数据开始取
        int start = (pageNum - 1) * pageSize;
        //获取用户的总数
        int totalCount = mapper.getAllInstitution(start,pageSize).size();
        List<Institution> users = mapper.getAllInstitution(start,pageSize);

        if (users.size() != 0) {
            layuiTableResultUtil.setTotals(totalCount);
            layuiTableResultUtil.setData(users);
        }else {
            layuiTableResultUtil.setMsg("不存在数据");
        }
        return layuiTableResultUtil;
    }

    @Override
    public LayuiTableResultUtil getDepartmentInstitution(int pageNum, int pageSize, String iDepartment) {

        LayuiTableResultUtil layuiTableResultUtil = new LayuiTableResultUtil();
        //起始位置，从第几条数据开始取
        int start = (pageNum - 1) * pageSize;
        List<Institution> users = mapper.getDepartmentInstitution(start,pageSize,iDepartment);
        if(users.size() != 0){
            layuiTableResultUtil.setTotals(users.size());
            layuiTableResultUtil.setData(users);
        }else {
            layuiTableResultUtil.setMsg("不存在数据");
        }
        return layuiTableResultUtil;
    }

    @Override
    public LayuiTableResultUtil getINameInstitution(String iName) {
        LayuiTableResultUtil layuiTableResultUtil = new LayuiTableResultUtil();
        List<Institution> users = mapper.institutionInformation(iName);

        if(users.size() != 0){
            layuiTableResultUtil.setTotals(users.size());
            layuiTableResultUtil.setData(users);
        }else {
            layuiTableResultUtil.setMsg("不存在数据");
        }
        return layuiTableResultUtil;
    }
}
