package com.web.modle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/8 16:54
 * @describe 新闻
 */
@Data
@AllArgsConstructor //所有参数构造函数
@NoArgsConstructor //没有参数的构造函数
@Builder  //构造一个对象 用法User.builder().id(11).username("111").build();
public class News {
    /**
     * Id
     */
    private Long nid;
    /**
     * 新闻名称
     */
    private String newsName;
    /**
     * 发布人
     */
    private String nPublisher;
    /**
     * 发布时间
     */
    private String nTime;
    /**
     * 发布人所在部门
     */
    private String nDepartment;
    /**
     * 发布人身份
     */
    private String nIdentity;
    /**
     * 公告内容
     */
    private String nContent;
    /**
     * 板块
     */
    private String nPlate;
}
