package com.commonutils.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/18 0:22
 * @Version: 11
 */
@Data
public class CourseInfoCommon {
    private String id;
    private String title;
    private String cover;
    private BigDecimal price;

}
