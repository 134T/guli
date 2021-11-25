package com.generator.domain.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.generator.domain.EduSubject;
import com.generator.domain.excel.ExcelSubjectData;
import com.generator.service.EduSubjectService;
import com.servicebase.exception.GuliException;

import java.util.Map;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/8/20 15:20
 * @Version: 11
 */
public class EduSubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    public EduSubjectService eduSubjectService;

    public EduSubjectExcelListener() {
    }

    /**
     * @Description:创建有参构造，传递eduSubjectService用于操作数据库
     **/
    public EduSubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }



    /**
     * @Description:判断一级分类是否重复
     **/
    private EduSubject existOneSubject(EduSubjectService eduSubjectService, String name){

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id",0);
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }
    /**
     * @Description:判断二级是否重复
     **/
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id",pid);
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }

    /**
     * @Description:一行一行读取excel内容: 意思是一次只读一个对象
     **/
    @Override
    public void invoke(ExcelSubjectData data, AnalysisContext context) {
        if (data == null) {
            throw new GuliException(20001, "添加失败");
        }
//      添加一级分类
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService, data.getOneSubjectName());
        if (existOneSubject == null) {
            existOneSubject = new EduSubject();
            existOneSubject.setTitle(data.getOneSubjectName());
            existOneSubject.setParentId("0");
            eduSubjectService.save(existOneSubject);
        }

//       获取一级分类的id值
        String pid = existOneSubject.getId();

//       添加二级分类
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService, data.getTwoSubjectName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setTitle(data.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            eduSubjectService.save(existTwoSubject);
        }

    }

    /**
     * @Description:读取excel表头信息
     **/
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    /**
     * @Description:读取完成后执行
     **/
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
