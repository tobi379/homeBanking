const app = Vue.createApp({


    data() {
        return {
            loanName: "",
            payments: "",
            maxAmount: "",
            fee: ""
        }

    },

    created() {

    },

    methods: {
        logout () {
            axios.post('/api/logout')
              .then(res => window.location.href = "/index.html")
        },
        createLoan() {
            let interes = this.fee / 100

            axios.post("/api/loan/", "name=" + this.loanName + "&maxAmount=" + this.maxAmount + "&payments=" + this.payments + "&fee=" + interes)
                .then(res => alert("created"))
        }
    }
})


app.mount("#app")