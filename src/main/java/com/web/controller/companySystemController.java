package com.web.controller;

import com.web.modle.Institution;
import com.web.modle.LayuiTableResultUtil;
import com.web.modle.Page;
import com.web.service.companySystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/19 11:35
 * @describe
 */
@Controller
public class companySystemController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private companySystemService service;

    /**
     * 给出所有的用户列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/InstitutionList")
    @ResponseBody
    public LayuiTableResultUtil InstitutionList(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                                @RequestParam(required = false, defaultValue = "10") int pageSize) {
        logger.info("获取制度列表");
        //实例化封装类
        LayuiTableResultUtil list = new LayuiTableResultUtil();
        try {
            logger.info(String.valueOf(pageNum));
            logger.info(String.valueOf(pageSize));

            list = service.getALLSystem(pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("用户列表查询异常！", e);
        }
        logger.info(String.valueOf(list));
        return list;
    }
    /**
     * 跳转到全部新闻页，并实现分页
     *
     * @param currentPage 当前页
     * @param model
     * @return
     */
    @RequestMapping(value = "/companySystem")
    public String findNewsList(String currentPage, Model model) {
        if (currentPage == null) {
            currentPage = "1";
        }
        String rows = "10";
        Page<Institution> byInstitution = service.findByInstitution(currentPage, rows);
        model.addAttribute("InstitutionList", byInstitution);
        return "companySystem";
    }
}
