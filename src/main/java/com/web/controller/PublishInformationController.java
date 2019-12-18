package com.web.controller;

import net.minidev.json.JSONObject;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/27 16:58
 * @describe
 */
@Controller
public class PublishInformationController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public JSONObject uploadSourceData(@RequestParam(value = "file", required = false) MultipartFile uploadFile) throws JSONException {
        logger.info("上传文件");

        //得到上传时的文件名
        String fileName = uploadFile.getOriginalFilename();
        logger.info(fileName);

        return null;
    }
}
