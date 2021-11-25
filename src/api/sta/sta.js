import request from '@/utils/request'

const api_name = '/statisticsService'
export default {

  createStatistics(day) {
    return request({
      url: `${api_name}/${day}`,
      method: 'post'
    })
  },
  showChart(searchObj) {
    return request({
      url: `${api_name}/show-chart/${searchObj.begin}/${searchObj.end}/${searchObj.type}`,
      method: 'get'
    })
  }
}
