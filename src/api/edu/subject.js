import request from '@/utils/request'

export default {
  // 添加课程分类
  getNestedTreeList() {
    return request({
      url: `/eduService/classList`,
      method: 'get'
    })
  }
}
