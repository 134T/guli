import request from '@/utils/request'

const api_name = '/eduService/course'

export default {
  //    添加课程信息
  saveCourseInfo(courseInfo) {
    return request({
      url: `${api_name}/save-course-info`,
      method: 'post',
      data: courseInfo
    })
  },
  //  通过id获取课程信息
  getCourseInfoById(id) {
    return request({
      url: `${api_name}/course-info/${id}`,
      method: 'get'
    })
  },
  //  通过id修改课程信息
  updateCourseInfoById(courseInfo) {
    return request({
      url: `${api_name}/update-course-info/${courseInfo.id}`,
      method: 'put',
      data: courseInfo
    })
  },
  getPageList(page, limit, searchObj) {
    return request({
      url: `${api_name}/${page}/${limit}`,
      method: 'get',
      params: searchObj
    })
  },
  removeById(id) {
    return request({
      url: `${api_name}/${id}`,
      method: 'delete'
    })
  }
}
