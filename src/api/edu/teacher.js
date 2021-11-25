import request from '@/utils/request'

export default {
  // 查询讲师数据
  pageQuery(page, limit, teacherQuery) {
    return request({
      url: `/eduService/${page}/${limit}`,
      method: 'post',
      data: teacherQuery
    })
  },
  // 删除讲师数据
  deleteById(id) {
    return request({
      url: `/eduService/deleteId/${id}`,
      method: 'delete'
    })
  },
  // 添加讲师
  saveTeacher(teacher) {
    return request({
      url: `/eduService/add`,
      method: 'post',
      data: teacher
    })
  },
  // 根据id查询讲师
  getById(id) {
    return request({
      url: `/eduService/selectById/${id}`,
      method: 'post'
    })
  },
  // 查询所有讲师
  getList() {
    return request({
      url: `/eduService/getAll`,
      method: 'get'
    })
  },
  // 根据id修改讲师
  updateById(teacher) {
    return request({
      url: `/eduService/update/${teacher.id}`,
      method: 'post',
      data: teacher
    })
  }

}
