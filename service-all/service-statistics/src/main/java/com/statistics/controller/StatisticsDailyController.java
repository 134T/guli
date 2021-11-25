package com.statistics.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.commonutils.ResultMessage;
import com.statistics.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author asus
 * @since 2021-11-19
 */
@Api(tags = "网站统计日数据")
@RestController
@RequestMapping("/statisticsService")
public class StatisticsDailyController {

    @Resource
    private StatisticsDailyService dailyService;
    @PostMapping("{day}")
    public ResultMessage createStatisticsByDate(@PathVariable String day) {
        dailyService.createStatisticsByDay(day);
        return ResultMessage.ok();
    }

    @GetMapping("show-chart/{begin}/{end}/{type}")
    public ResultMessage showChart(@PathVariable String begin,@PathVariable String end,@PathVariable String type){
        Map<String, Object> map = dailyService.getChartData(begin, end, type);
        return ResultMessage.ok().data(map);
    }
}

