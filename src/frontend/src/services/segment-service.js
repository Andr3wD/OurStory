import axios from 'axios' // Axios is used for doing http requests.

const APIURL = 'http://localhost:8080'

class SegmentService {
  getSegments () {
    return axios.get('/getSegments')
  }

  getFirstSegment () {
    return axios.get(APIURL + '/test/first')
  }
}

export default new SegmentService()
