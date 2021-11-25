import request from '@/utils/request'

export default {
  //邮箱登录
  submitLogin(loginVo) {
    return request({
      url: `/ucenterService/login`,
      method: 'post',
      data: loginVo
    })
  },
  //微信登录
  weixinLogin() {
    return request({
      url: `/ucenterService/wx/login`,
      method: 'get',
    })
  },
  //根据token获取用户信息
  getLoginInfo() {
    return request({
      url: `/ucenterService/auth/getLoginInfo`,
      method: 'get',
     // headers: {'token': cookie.get('guli_token')}
    })
    //headers: {'token': cookie.get('guli_token')} 
  }
}
