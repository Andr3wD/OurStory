import axios from 'axios' // Axios is used for doing http requests.

class SectionService {
  getSections () {
    return axios.get('/getSections')
  }
}

export default new SectionService()
