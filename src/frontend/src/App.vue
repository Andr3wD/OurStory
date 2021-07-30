<template>
  <div id="app">
    <b-navbar toggleable="lg" variant="dark" type="dark">
      <b-navbar-brand href="/">OurStory</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>

        <b-navbar-nav>
          <b-button class="mr-3" to="/" replace>Home</b-button>
          <b-button class="mr-3" to="/about" replace>About</b-button>
          <b-button class="mr-3" to="/global" replace>Global</b-button>
          <b-button class="mr-3" to="/admin" replace>Admin</b-button>
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
          <b-button class="mr-3" to="/login" v-if="!this.$store.state.auth.loggedIn" replace>Login/Register</b-button>
          <b-button class="mr-3" @click="logoutButton" v-if="this.$store.state.auth.loggedIn" replace>Logout</b-button>
        </b-navbar-nav>

      </b-collapse>
    </b-navbar>
    <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App',
  created () {
    console.log('auth')
    console.log(this.$store.state.auth.loggedIn)
  },
  methods: {
    logoutButton (e) {
      console.log('logout')
      this.$store.dispatch('auth/logout').then(
        response => {
          // Redirect to home page.
          console.log('INSIDe')
        },
        error => {
          console.log('ERR INSIDE')
          console.log(error)
        }
      )
    }
  }
}

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  background-color: whitesmoke;
  color: #2c3e50;
  height: 100vh;
}

router-link {
  font-weight: bold;
  color: #ffffff;
}

.router-link-exact-active {
  background-color: rgb(9, 175, 111) !important;
}
</style>
