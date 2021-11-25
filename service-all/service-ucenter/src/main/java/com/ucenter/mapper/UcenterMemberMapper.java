package com.ucenter.mapper;

import com.ucenter.domain.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity com.ucenter.domain.UcenterMember
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer selectRegisterCount(String day);
}




