import AuthService from '../services/auth-service'

const key = localStorage.getItem('authKey')
const initState = { loggedIn: false, authKey: null }
if (key) {
  initState.authKey = key
  initState.loggedIn = true // TODO handle when auth key expires.
}

export const auth = {
  state: initState,
  namespaced: true,
  mutations: {
    loginSuccess (state, key) {
      state.loggedIn = true
      state.key = key
    },
    loginFailure (state) {
      state.loggedIn = false
      state.key = null
    }
  },
  actions: {
    login ({ commit }, user) {
      return AuthService.submitLogin(user).then(
        response => {
          console.log(response)
          // TODO get key (session id) from cookies and commit('loginSuccess',key)
          return Promise.resolve('key here')
        }).catch(e => {
        console.log(e)
        commit('loginFailure') // commit a mutation, specifically the 'loginFailure' mutation, to occur.
        return Promise.reject(e)
      })
    },
    logout ({ commit }) {

    },
    register ({ commit }) {

    }
  }
}
