//import com.alibaba.excel.context.AnalysisContext;
//import com.alibaba.excel.event.AnalysisEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * @Description:
// * @Author: 坚持的力量
// * @Date: 2021/8/18 17:54
// * @Version: 11
// */
//
//public class ExcelListener extends AnalysisEventListener<ReadData> {
//
//    List<ReadData> list = new ArrayList<ReadData>();
//
//    //一行一行去读取excle内容
//    @Override
//    public void invoke(ReadData data, AnalysisContext context) {
//
//        System.out.println("***"+data);
//        list.add(data);
//    }
//
//    //读取excel表头信息
//    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
//        System.out.println("表头信息："+headMap);
//    }
//
//
//    @Override
//    public void doAfterAllAnalysed(AnalysisContext context) {
//
//    }
//}
