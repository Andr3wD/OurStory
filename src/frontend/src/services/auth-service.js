import axios from 'axios' // Axios is used for doing http requests.

// const APIURL = 'http://localhost:8080'

class AuthService {
  submitLogin (user) {
    console.log('making request')
    console.log(user.username + user.password)
    return axios.post('/loginAuth', null, { params: { username: user.username, password: user.password } })
  }

  logout () {
    console.log('making logout request')
    return axios.post('/logout')
  }

  getAuthStatus () {
    console.log('Checking auth status')
    return axios.get('/user/getAuthStatus')
  }
}

export default new AuthService()
