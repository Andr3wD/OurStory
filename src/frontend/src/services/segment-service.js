import axios from 'axios' // Axios is used for doing http requests.

const APIURL = 'http://localhost:8080'

class SegmentService {
  getSegments (name) {
    return axios.get(APIURL + '/story/getSegments', { params: { title: name } })
  }
}

export default new SegmentService()
