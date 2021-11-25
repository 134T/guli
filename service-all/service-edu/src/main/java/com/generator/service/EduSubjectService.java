package com.generator.service;

import com.generator.domain.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.generator.domain.subject.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 * @author asus
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * @Description:导入课程分类excel
     **/
    void importSubjectData(MultipartFile file, EduSubjectService eduSubjectService);

    List<SubjectNestedVo> nestedList();
}
