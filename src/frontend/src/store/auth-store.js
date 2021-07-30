import AuthService from '../services/auth-service'

const initRoles = { isAdmin: false, isMod: false, isUser: false, isAnon: true }
const initUsername = 'NONE'
const initState = { loggedIn: false, userData: { roles: initRoles, username: initUsername } }

export const auth = {
  state: initState,
  namespaced: true,
  mutations: {
    loginSuccess (state, data) {
      state.loggedIn = true
      state.userData.roles = data.roles
      state.userData.username = data.username
    },
    loginFailure (state, data) {
      state.loggedIn = false
      state.userData.roles = data.roles
      state.userData.username = data.username
    },
    logoutSuccess (state) {
      state.loggedIn = false
      state.userData.roles = initRoles
      state.userData.username = initUsername
    }
  },
  actions: {
    login ({ commit }, user) {
      return AuthService.submitLogin(user).then(
        response => { // Login was accepted
          console.log(response) // TODO remove eventually
          commit('loginSuccess', response.data)
          return Promise.resolve()
        }).catch(err => { // Thanks to https://dev.to/zelig880/how-to-catch-the-body-of-an-axios-error-4lk0
        commit('loginFailure', err.response.data) // commit a mutation, specifically the 'loginFailure' mutation, to occur.
        if (err.response.data !== undefined) {
          return Promise.reject(err.response)
        } else {
          return Promise.reject(err)
        }
      })
    },
    logout ({ commit }) {
      return AuthService.logout().then(
        response => { // logout was accepted
          console.log(response) // TODO remove eventually
          commit('logoutSuccess', response.data)
          return Promise.resolve()
        })
    },
    register ({ commit }) {

    }
  }
}

AuthService.getAuthStatus().then(
  response => {
    auth.state.userData.roles = response.data.roles
    auth.state.userData.username = response.data.username
    auth.state.loggedIn = !auth.state.userData.roles.isAnon
  }
)
