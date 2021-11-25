import request from '@/utils/request'

const api_name = '/vodService'

export default {

  removeById(id) {
    return request({
      url: `${api_name}/${id}`,
      method: 'delete'
    })
  },
  uploadVideo(file) {
    return request({
      url: `${api_name}/upload`,
      method: 'post',
      data: file
    })
  }
}
