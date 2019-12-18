package com.web.modle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/13 14:59
 * @describe layui数据表格返回数据处理类
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LayuiTableResultUtil {
    /**
     * 状态编码
     */
    private Integer code = 200;
    /**
     * 返回的信息
     */
    private String msg;
    /**
     * 总记录数
     */
    private Integer totals;
    /**
     * 返回的数据
     */
    private List<?> data;


}
