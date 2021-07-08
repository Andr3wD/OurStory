import axios from 'axios' // Axios is used for doing http requests.

const APIURL = 'http://localhost:8080'

class AuthService {
  submitLogin (user) {
    return axios.post(APIURL + '/user/login', { username: user.username, password: user.password })
  }
}

export default new AuthService()
