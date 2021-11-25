package com.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cms.domain.CrmBanner;
import com.cms.service.CrmBannerService;
import com.commonutils.ResultMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/9 15:20
 * @Version: 11
 */
@Api(tags = "后台管理接口")
@RestController
@RequestMapping("/banner")
public class CrmController {

    @Resource
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取Banner分页列表")
    @GetMapping("{page}/{limit}")
    public ResultMessage index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {

        Page<CrmBanner> pageParam = new Page<>(page, limit);
        bannerService.page(pageParam);
        return ResultMessage.ok().data("items", pageParam.getRecords()).data("total", pageParam.getTotal());
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public ResultMessage get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return ResultMessage.ok().data("item", banner);
    }

    @ApiOperation(value = "新增Banner")
    @PostMapping("save")
    public ResultMessage save(@RequestBody CrmBanner banner) {
        bannerService.save(banner);
        return ResultMessage.ok();
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public ResultMessage updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return ResultMessage.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public ResultMessage remove(@PathVariable String id) {
        bannerService.removeById(id);
        return ResultMessage.ok();
    }

    @ApiOperation(value = "获取所有的banner")
    @GetMapping("getAllBanner")
    public ResultMessage index() {
        List<CrmBanner> list = bannerService.list();
        return ResultMessage.ok().data("bannerList", list);
    }
}
