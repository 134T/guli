package com.generator.domain.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/3 15:14
 * @Version: 11
 */
@Data
public class SubjectNestedVo {
    private String id;
    private String title;
    private List<SubjectVo> children = new ArrayList<>();
}
