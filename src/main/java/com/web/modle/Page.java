package com.web.modle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author qyh
 * @version 1.0
 * @date 2019/11/12 11:35
 * @describe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Page<T> {
    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 每页显示的数据
     */
    private int rows;
    /**
     * 当前页数
     */
    private int currentPage;
    /**
     * 每页存放的数据
     */
    private List<T> list;
}
