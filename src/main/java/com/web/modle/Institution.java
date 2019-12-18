package com.web.modle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/19 11:40
 * @describe 制度实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Institution {
    /**
     * Id
     */
    private Long iId;
    /**
     * 名称
     */
    private String iName;
    /**
     * 发布人
     */
    private String iPublisher;

    /**
     * 发布人所在部门
     */
    private String iDepartment;
    /**
     * 发布人身份
     */
    private String iIdentity;
    /**
     * 内容
     */
    private String iContent;
    /**
     * 发布时间
     */
    private String iTime;
    /**
     * 类型
     */
    private String iType;
    /**
     * 修改时间
     */
    private String iUpdateTime;

}
