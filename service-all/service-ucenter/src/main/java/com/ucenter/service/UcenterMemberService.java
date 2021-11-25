package com.ucenter.service;

import com.ucenter.domain.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ucenter.domain.vo.LoginVo;
import com.ucenter.domain.vo.RegisterVo;

/**
 *
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    UcenterMember getLoginInfo(String memberId);

    UcenterMember getByOpenid(String openid);

    Integer countRegisterByDay(String day);
}
