import com.alibaba.excel.EasyExcel;
import com.generator.EduApplication;
import com.generator.domain.Course;
import com.generator.domain.EduTeacher;
import com.generator.domain.excel.ExcelSubjectData;
import com.generator.domain.listener.EduSubjectExcelListener;
import com.generator.mapper.EduSubjectMapper;
import com.generator.mapper.EduTeacherMapper;
import com.generator.service.CourseService;
import com.generator.service.EduSubjectService;
import com.servicebase.exception.GuliException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/10/28 21:58
 * @Version: 11
 */
@SpringBootTest(classes = EduApplication.class)
public class TeacherTest {

    @Resource
    private EduTeacherMapper eduTeacherMapper;
    @Resource
    private EduSubjectMapper eduSubjectMapper;
    @Resource
    private EduSubjectService eduSubjectService;
    @Resource
    private CourseService courseService;
    @Test
    public void updateTime() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        EduTeacher eduTeacher = new EduTeacher();
        eduTeacher.setId("11");
        eduTeacher.setName("晴天3");
        eduTeacher.setIntro("高级讲师简介3");
        eduTeacher.setCareer("高级讲师资历3");
        eduTeacher.setLevel(1);
        eduTeacher.setAvatar("http://ww4.sinaimg.cn/large/7a8aed7bgw1et11xp5wwij20hs0qotb2.jpg");
        eduTeacher.setSort(10);
        eduTeacher.setGmtCreate(format.parse("2019-10-30 11:53:03"));
        eduTeacher.setGmtModified(format.parse("2019-10-30 11:53:03"));
        eduTeacherMapper.insert(eduTeacher);
//        eduTeacherMapper.updateById(eduTeacher);
    }

    @Test
    void exceptionTest(){
        try {

            int a = 10/0;
        }catch(Exception e) {

            throw new GuliException(20001,"出现自定义异常");
        }
    }

    @Test
    public void test() throws IOException {




        EasyExcel.read("D:\\idea\\03.xls", ExcelSubjectData.class,
                new EduSubjectExcelListener(eduSubjectService)).sheet().doRead();
//            ImportParams params = new ImportParams();
//            params.setTitleRows(3);
//            params.setHeadRows(2);
//        List<EduSubject> eduSubjects = eduSubjectMapper.selectList(null);
//        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("课程分类大全", "课程1"),
//                EduSubject.class, eduSubjects);
//        workbook.createSheet("what");
//        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\goole_down\\big.xls"));
//        workbook.write(fileOutputStream);
//        fileOutputStream.close();
//        workbook.close();

    }

    @Test
    public void courseTest(){
        List<Course> list = courseService.list();
        list.forEach(System.out::println);

    }
}
