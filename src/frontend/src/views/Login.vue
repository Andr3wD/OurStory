<template>
  <div class="container">
    <b-form class="login-form" @onSubmit="onSubmit">
      <b-form-group label="Username">
        <b-form-input placeholder="Username Here" v-model="form.username" required/>
      </b-form-group>
      <b-form-group label="Password">
        <b-form-input placeholder="Password Here" v-model="form.password" type="password" required/>
      </b-form-group>
      <div v-if="this.feedback" class="alert alert-danger">{{feedback}}</div>
      <b-button type="submit" variant="primary">Submit</b-button>
    </b-form>

  </div>
</template>

<script>
export default {
  data () {
    return {
      form: {
        username: '',
        password: ''
      },
      feedback: 'TEST TODO REMOVE'
    }
  },
  methods: {
    onSubmit (e) {
      // TODO make axios request to backend for login with username and password as params.
      this.$store.dispatch('auth/login', this.form).then(
        response => {
          // Redirect to home page.
        },
        error => {
          // Show error feedback message.
          this.feedback = error.data
        }
      )
    }
  }

}
</script>

<style>
.login-form {
  margin: auto;
  padding-top: 100px;
  width: 350px;
}
</style>
