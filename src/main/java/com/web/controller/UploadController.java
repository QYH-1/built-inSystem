package com.web.controller;

import com.web.mapper.UserInformationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/12/17 16:45
 * @describe
 */
@Controller
public class UploadController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserInformationMapper userInformationMapper;

    /**
     * 个人信息上传
     *
     * @return {Result}
     */
    @RequestMapping(value = "/upload/headImg", method = {RequestMethod.POST})
    @ResponseBody
    public Object headImg(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String prefix = "";
        //保存上传
        String userName = (String) session.getAttribute("tname");
        int uId = (int) session.getAttribute("uId");
        logger.info(userName + "---" + uId);
        OutputStream out = null;
        InputStream fileInput = null;

        try {
            if (file != null) {
                String originalName = file.getOriginalFilename();
                prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
                logger.info(prefix);

                String webPath = "D:\\CODE\\IDEA\\web\\src\\main\\resources\\static\\images\\avatarImg";
                String filepath = webPath + "/static" + userName + "." + prefix;
                filepath = filepath.replace("\\", "/");
                File files = new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if (!files.getParentFile().exists()) {
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                String avatarImgUrl = "static" + userName + "." + prefix;
                String urlImage = "../images/avatarImg/" + avatarImgUrl;
                session.setAttribute("avatarImgUrl", urlImage);

                userInformationMapper.headImg(uId, avatarImgUrl);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", map2);
        map2.put("src", "../../../static" + userName + "." + prefix);
        return map;
    }
}
