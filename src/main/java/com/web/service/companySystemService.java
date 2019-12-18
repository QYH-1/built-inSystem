package com.web.service;

import com.web.modle.Institution;
import com.web.modle.LayuiTableResultUtil;
import com.web.modle.Page;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/19 11:35
 * @describe
 */
public interface companySystemService {

    /**
     * 获取所有的制度
     * @param pageNum
     * @param pageSize
     * @return
     */
    LayuiTableResultUtil getALLSystem(int pageNum, int pageSize);

    /**
     * 获取不同类型的制度
     * @param pageNum
     * @param pageSize
     * @param iType
     * @return
     */
    LayuiTableResultUtil getTypeSystem(int pageNum, int pageSize, String iType);

    /**
     *根据制度名查询制度
     * @param iName
     * @return
     */
    LayuiTableResultUtil getiNameSystem(String iName);

    /**
     * 获取所有制度
     *
     * @return
     */
    List<Institution> getAllInstitution();

    /**
     * 获取当前页显示的数据
     * @param currentPage
     * @param rows
     * @return
     */
    Page<Institution> findByInstitution(String currentPage, String rows);
}
