package com.generator.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.generator.domain.EduSubject;
import com.generator.domain.excel.ExcelSubjectData;
import com.generator.domain.listener.EduSubjectExcelListener;
import com.generator.domain.subject.SubjectNestedVo;
import com.generator.domain.subject.SubjectVo;
import com.generator.mapper.EduSubjectMapper;
import com.generator.service.EduSubjectService;
import com.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject>
implements EduSubjectService{

    @Resource
    private EduSubjectMapper eduSubjectMapper;

    @Override
    public void importSubjectData(MultipartFile file, EduSubjectService eduSubjectService) {

        try {

            InputStream inputStream = file.getInputStream();
            System.out.println(file.getContentType());
            EasyExcel.read(inputStream, ExcelSubjectData.class,
                    new EduSubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20002, "添加课程分类失败");
        }
    }

    @Override
    public List<SubjectNestedVo> nestedList() {
        //最终要的到的数据列表
        ArrayList<SubjectNestedVo> subjectNestedVoArrayList = new ArrayList<>();

        //获取一级分类数据记录
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        List<EduSubject> subjects = eduSubjectMapper.selectList(queryWrapper);

        //填充一级分类vo数据
        int count = subjects.size();
        for (int i = 0; i < count; i++) {
            EduSubject subject = subjects.get(i);

            //创建一级类别vo对象
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(subject,subjectNestedVo);

            //获取二级分类数据记录
            QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("parent_id", subject.getId());
            List<EduSubject> subjects2 = eduSubjectMapper.selectList(queryWrapper2);
            //填充二级分类vo数据
            ArrayList<SubjectVo> subjectVoArrayList = new ArrayList<>();
            for (int j = 0; j < subjects2.size(); j++) {

                EduSubject subSubject2 = subjects2.get(j);
                //创建二级类别vo对象
                SubjectVo subjectVo = new SubjectVo();
                BeanUtils.copyProperties(subSubject2,subjectVo);

                //添加数据
                subjectVoArrayList.add(subjectVo);
            }
            //添加二级分类数据
            subjectNestedVo.setChildren(subjectVoArrayList);
            //添加一级分类数据
            subjectNestedVoArrayList.add(subjectNestedVo);
        }


        return subjectNestedVoArrayList;
    }
}




