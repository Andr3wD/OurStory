<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <a class="navbar-brand">OurStory</a>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="mr-3 nav-item active">
            <b-button to="/" replace>Home</b-button>
          </li>
          <li class="mr-3 nav-item active">
            <b-button to="/about" replace>About</b-button>
          </li>
          <li class="mr-3 nav-item active">
            <b-button to="/global" replace>Global</b-button>
          </li>
        </ul>
      </div>
    </nav>
    <router-view/>
  </div>
</template>

<script>

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

router-link {
  font-weight: bold;
  color: #ffffff;
}

.router-link-exact-active {
  background-color: rgb(9, 175, 111) !important;
}
</style>
