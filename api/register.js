import request from '@/utils/request'

export default {
  //根据手机号码发送短信
  getMobile(qq) {
    return request({
      url: `/emailService/send/${qq}`,
      method: 'get'
    })
  },
  //用户注册
  submitRegister(formItem) {
    return request({
      url: `/ucenterService/register`,
      method: 'post',
      data: formItem
    })
  }
}

