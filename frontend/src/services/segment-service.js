import axios from 'axios' // Axios is used for doing http requests.

// const APIURL = 'http://localhost:8080'

class SegmentService {
  getSegments (name) {
    return axios.get('/story/getSegments', { params: { title: name } })
  }

  submitSegment (message, storyTitle) {
    return axios.post('/segment/addSegment', { message: message, storyTitle: storyTitle })
  }
}

export default new SegmentService()
