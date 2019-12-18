package com.web.modle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/11 16:22
 * @describe 公告
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Announcement {
    /**
     * Id
     */
    private Long aid;
    /**
     * 公告名称
     */
    private String aName;
    /**
     * 发布人
     */
    private String aPublisher;
    /**
     * 发布时间
     */
    private String aTime;
    /**
     * 发布人所在部门
     */
    private String aDepartment;
    /**
     * 发布人身份
     */
    private String aIdentity;
    /**
     * 公告内容
     */
    private String aContent;
    /**
     * 板块
     */
    private String aPlate;
}
