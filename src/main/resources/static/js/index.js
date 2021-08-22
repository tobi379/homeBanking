const app = Vue.createApp ({
    data() {
        return {
          // Log In
          email : "",
          password : "",

          // Sing Up
          name : "",
          lastName : "",
          newEmail : "",
          newPassword : "",
        }
    },
    methods : {
      login() {
        axios.post('/api/login', "email=" + this.email + "&password=" + this.password, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
          .then(response => window.location.href = "/accounts.html")
          .catch(err => console.table('Usuario o Contraseña incorrecto'))
      },
      signup () {
        axios.post('/api/clients',"firstName=" + this.name + "&lastName=" + this.lastName + "&email=" + this.newEmail + "&password=" + this.newPassword,{headers:{'content-type':'application/x-www-form-urlencoded'}})
          .then(res => {
            axios.post('/api/login', "email=" + this.newEmail + "&password=" + this.newPassword, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
              .then(response => window.location.href = "/accounts.html")
          })
      }
    }
})

app.mount("#app")