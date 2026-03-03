package com.rescue.common.dto;

import lombok.Data;

import java.util.List;

/**
 * TODO description...
 *
 * @author: NIEYG
 * @create-date: 2024/8/5 17:01
 */
@Data
public class PageESDto<T> {
    /**
     * 总数
     */
    private Long total;
    /**
     * 当前页码
     */
    private Integer current = 1;
    /**
     * 每页数量
     */
    private Integer pageSize = 10;
    /**
     * 数据
     */
    private List<T> records;
}
