import axios from 'axios' // Axios is used for doing http requests.

// const APIURL = 'http://localhost:8080'

class AuthService {
  getUserStats () {
    console.log('making userStats request')
    return axios.get('/admin/userStats')
  }
}

export default new AuthService()
