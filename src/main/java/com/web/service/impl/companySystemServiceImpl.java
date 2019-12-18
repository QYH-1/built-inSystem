package com.web.service.impl;

import com.web.mapper.companySystemMapper;
import com.web.modle.Institution;
import com.web.modle.LayuiTableResultUtil;
import com.web.modle.Page;
import com.web.service.companySystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/19 11:38
 * @describe 实现方法，获取制度数据
 */
@Service
public class companySystemServiceImpl implements companySystemService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private companySystemMapper mapper;

    @Override
    public LayuiTableResultUtil getALLSystem(int pageNum, int pageSize) {

        LayuiTableResultUtil layuiTableResultUtil = new LayuiTableResultUtil();
        //起始位置，从第几条数据开始取
        int start = (pageNum - 1) * pageSize;
        //获取用户的总数
        int totalCount = mapper.getInstitution().size();
        List<Institution> institutions = mapper.getAll(start, pageSize);
        if (institutions.size() != 0) {
            layuiTableResultUtil.setTotals(totalCount);
            layuiTableResultUtil.setData(institutions);

        }
        return layuiTableResultUtil;
    }

    @Override
    public LayuiTableResultUtil getTypeSystem(int pageNum, int pageSize, String iType) {
        LayuiTableResultUtil layuiTableResultUtil = new LayuiTableResultUtil();
        //起始位置，从第几条数据开始取
        int start = (pageNum - 1) * pageSize;
        List<Institution> institution = mapper.getTypeInstitution(start, pageSize, iType);
        if (institution.size() != 0) {
            layuiTableResultUtil.setTotals(institution.size());
            layuiTableResultUtil.setData(institution);
        }
        return layuiTableResultUtil;
    }

    @Override
    public LayuiTableResultUtil getiNameSystem(String iName) {
        LayuiTableResultUtil layuiTableResultUtil = new LayuiTableResultUtil();
        List<Institution> institutions = mapper.institutionInformation(iName);
        if (institutions.size() != 0) {
            layuiTableResultUtil.setTotals(institutions.size());
            layuiTableResultUtil.setData(institutions);
        }
        return layuiTableResultUtil;
    }

    @Override
    public List<Institution> getAllInstitution() {
        return null;
    }

    /**
     * 获取当前页显示的制度数据
     *
     * @param _currentPage
     * @param _rows
     * @return
     */
    @Override
    public Page<Institution> findByInstitution(String _currentPage, String _rows) {
        Page<Institution> page = new Page<>();
        //获取当前页
        int currentPage = Integer.parseInt(_currentPage);
        //获取当前页显示数据数
        int rows = Integer.parseInt(_rows);
        //获取新闻的总数
        int totalCount = mapper.getAllInstitution().size();
        //总的页数
        int totalPage = (totalCount % rows == 0) ? (totalCount / rows) : (totalCount / rows) + 1;
        //起始位置，从第几条数据开始取
        int start = (currentPage - 1) * rows;
        /**
         * 在数据库中获取数据
         */
        List<Institution> list = mapper.findByInstitution(start, rows);
        page.setRows(rows);
        page.setCurrentPage(currentPage);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);
        page.setList(list);
        return page;
    }
}
