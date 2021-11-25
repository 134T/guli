import request from '@/utils/request'
export default {
  getPageList(page, limit, searchObj) {
    return request({
      url: `/eduService/front/course/${page}/${limit}`,
      method: 'post',
      data: searchObj
    })
  },
  // 获取课程一级分类
  getNestedTreeList2() {
    return request({
      url: `/eduService/classList`,
      method: 'get'
    })
  },
  getByCourseId(courseId) {
    return request({
        url: `/eduService/front/course/${courseId}`,
        method: 'get'
    })
  }
}