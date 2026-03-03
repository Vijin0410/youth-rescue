package com.rescue.common.result;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rescue.common.dto.PageESDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页响应结构体
 *
 * @author lenvo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {

    private String code;

    private Data<T> data;

    private String msg;

    public static <T> PageResult<T> success(IPage<T> page) {
        PageResult<T> result = new PageResult<>();
        result.setCode(ResultCode.SUCCESS.getCode());

        Data data = new Data<T>();
        data.setList(page.getRecords());
        data.setTotal(page.getTotal());

        result.setData(data);
        result.setMsg(ResultCode.SUCCESS.getMsg());
        return result;
    }

    public static <T> PageResult<T> successEs(PageESDto<T> page) {
        PageResult<T> result = new PageResult<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        Data data = new Data<T>();
        data.setList(page.getRecords());
        data.setTotal(page.getTotal());
        result.setData(data);
        result.setMsg(ResultCode.SUCCESS.getMsg());
        return result;
    }

  @lombok.Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Data<T> implements Serializable {

        private List<T> list;

        private long total;

    }

    public static boolean isSuccess(PageResult<?> result) {
        return result != null && ResultCode.SUCCESS.getCode().equals(result.getCode());
    }

}
