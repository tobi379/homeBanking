const app = Vue.createApp ({
    data() {
        return {
          clients : [],
          accounts : [],
          loans : [],
          totalAccounts : "",
        }
    },
    created() {
        axios.get('/api/clients/current')
          .then(res => {
            this.clients = res.data
            this.totalAccounts = this.clients.accounts.length
            this.accounts = this.clients.accounts
            this.accounts.sort((a, b) => a.id - b.id)
            this.loans = res.data.loans
            this.loans.sort((a, b) => a.idClientLoan - b.idClientLoan)
            // console.table(this.clients)
            // console.log(this.totalAccounts)
            // console.table(this.accounts)
            // console.log(this.loans)
          })
          .catch(function (error) {
            // handle error
            console.log(error);
          })
    },
    methods : {
        date (date) {
          return new Date(date).toLocaleDateString('es-ar')
        },
        logout () {
          axios.post('/api/logout')
            .then(res => window.location.href = "/index.html")
        },
        AddAccount(type) {
            axios.post('/api/clients/current/accounts', "type=" + type,
            {
                headers: { 'content-type': 'application/x-www-form-urlencoded' }
            })
            location.reload()
        },
        deleteAccount(id) {
            axios.post("/api/accounts", "number=" + id)
            location.reload()
        }
    }
})

app.mount("#app")