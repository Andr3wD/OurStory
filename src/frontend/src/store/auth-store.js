import AuthService from '../services/auth-service'

const key = localStorage.getItem('authKey')
const initState = { loggedIn: false, authKey: null }
if (key) {
  initState.authKey = key
  initState.loggedIn = true // TODO handle when auth key expires.
}

export const auth = {
  state: initState,
  mutations: {
  },
  actions: {
    login ({ commit }, user) {
      return AuthService.submitLogin(user).then(
        response => {
          console.log(response.data)
          // TODO get key (session id) from cookies and commit('loginSuccess',key)
          return Promise.resolve('key here')
        },
        error => {
          commit('loginFailure') // commit a mutation, specifically the 'loginFailure' mutation, to occur.
          return Promise.reject(error)
        }
      )
    },
    logout ({ commit }) {

    },
    register ({ commit }) {

    }
  },
  modules: {
    loginSuccess (state, key) {
      state.loggedIn = true
      state.key = key
    },
    loginFailure (state) {
      state.loggedIn = false
      state.key = null
    }
  }
}
