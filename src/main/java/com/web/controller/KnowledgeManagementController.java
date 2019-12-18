package com.web.controller;

import com.web.modle.LayuiTableResultUtil;
import com.web.service.KnowledgeManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/12/16 16:30
 * @describe 知识管理界面控制器
 */
@Controller
public class KnowledgeManagementController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private KnowledgeManagementService service;
    /**
     * 给出所有的制度
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/companySystemListData")
    @ResponseBody
    public LayuiTableResultUtil Institution(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                         @RequestParam(required = false, defaultValue = "10") int pageSize) {
        logger.info("获取制度列表");
        //实例化封装类
        LayuiTableResultUtil list = new LayuiTableResultUtil();
        try {
            logger.info(String.valueOf(pageNum));
            logger.info(String.valueOf(pageSize));

            list = service.getALLInstitution(pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("制度列表查询异常！", e);
        }
        logger.info(String.valueOf(list));
        return list;
    }
    /**
     * 根据条件查询人员并分页
     *
     * @param iName
     * @param iDepartment
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/effectiveInstitution")
    @ResponseBody
    public LayuiTableResultUtil effectiveInstitution(String iName, String iDepartment, @RequestParam(required = false, defaultValue = "1") int pageNum,
                                              @RequestParam(required = false, defaultValue = "10") int pageSize) {

        logger.info("查找用户");
        logger.info(iName);
        logger.info(iDepartment);
        //实例化封装类
        LayuiTableResultUtil list = new LayuiTableResultUtil();
        //根据部门查找人
        if (iName.equals("") && iDepartment.equals("")) {
            try {
                list = service.getALLInstitution(pageNum, pageSize);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("用户列表查询异常！", e);
            }
        } else if (iName.equals("") && !iDepartment.equals("")) {
            try {
                list = service.getDepartmentInstitution(pageNum, pageSize, iDepartment);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("用户列表查询异常！", e);
            }
        } else {
            try {
                list = service.getINameInstitution(iName);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("用户列表查询异常！", e);
            }
        }
        return list;
    }
}
