package com.web.service;

import com.web.modle.LayuiTableResultUtil;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/12/16 16:33
 * @describe
 */
public interface KnowledgeManagementService {
    /**
     * 获取所有的制度
     * @param pageNum
     * @param pageSize
     * @return
     */
    LayuiTableResultUtil getALLInstitution(int pageNum, int pageSize);

    /**
     * 获取查询部门的制度
     * @param pageNum
     * @param pageSize
     * @param iDepartment
     * @return
     */
    LayuiTableResultUtil getDepartmentInstitution(int pageNum, int pageSize, String iDepartment);

    /**
     *根据用户名查询制度
     * @param iName
     * @return
     */
    LayuiTableResultUtil getINameInstitution(String iName);
}
