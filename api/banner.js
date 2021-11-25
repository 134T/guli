import request from '@/utils/request'

export default {
  getList() {
    return request({
      url: `/banner/getAllBanner`,
      method: 'get'
    })
  }
}
