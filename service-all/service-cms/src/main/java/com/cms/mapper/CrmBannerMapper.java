package com.cms.mapper;

import com.cms.config.MybatisRedisCache;
import com.cms.domain.CrmBanner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * @author asus
 * @Entity com.cms.domain.CrmBanner
 */
@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface CrmBannerMapper extends BaseMapper<CrmBanner> {

}




