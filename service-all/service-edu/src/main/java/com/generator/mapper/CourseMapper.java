package com.generator.mapper;

import com.generator.domain.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.generator.domain.course.CoursePublishVo;
import com.generator.domain.front.CourseWebVo;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author asus
 * @since 2021-11-03
 */
public interface CourseMapper extends BaseMapper<Course> {




    @Select(" SELECT\n" +
            "        c.title,\n" +
            "        c.cover,\n" +
            "        c.lesson_num AS lessonNum,\n" +
            "        CONVERT(c.price, DECIMAL(8,2)) AS price,\n" +
            "        s1.title AS subjectLevelOne,\n" +
            "        s2.title AS subjectLevelTwo,\n" +
            "        t.name AS teacherName\n" +
            "    FROM\n" +
            "        edu_course c\n" +
            "        LEFT JOIN edu_teacher t ON c.teacher_id = t.id\n" +
            "        LEFT JOIN edu_subject s1 ON c.subject_parent_id = s1.id\n" +
            "        LEFT JOIN edu_subject s2 ON c.subject_id = s2.id\n" +
            "    WHERE\n" +
            "        c.id = #{id}")
    CoursePublishVo selectCoursePublishVoById(String id);

    @Select("SELECT\n" +
            "    c.id,\n" +
            "    c.title,\n" +
            "    CONVERT(c.price, DECIMAL(8,2)) AS price,\n" +
            "    c.lesson_num AS lessonNum,\n" +
            "    c.cover As cover,\n" +
            "    c.buy_count AS buyCount,\n" +
            "    c.view_count AS viewCount,\n" +
            "    cd.description,\n" +
            "\n" +
            "    t.id AS teacherId,\n" +
            "    t.name AS teacherName,\n" +
            "    t.intro,\n" +
            "    t.avatar,\n" +
            "    \n" +
            "    s1.id AS subjectLevelOneId,\n" +
            "    s1.title AS subjectLevelOne,\n" +
            "    s2.id AS subjectLevelTwoId,\n" +
            "    s2.title AS subjectLevelTwo\n" +
            "\n" +
            "  FROM\n" +
            "    edu_course c\n" +
            "    LEFT JOIN edu_course_description cd ON c.id = cd.id\n" +
            "    LEFT JOIN edu_teacher t ON c.teacher_id = t.id\n" +
            "    LEFT JOIN edu_subject s1 ON c.subject_parent_id = s1.id\n" +
            "    LEFT JOIN edu_subject s2 ON c.subject_id = s2.id\n" +
            "  WHERE\n" +
            "    c.id = #{courseId}")
    CourseWebVo selectInfoWebById(String courseId);
}
