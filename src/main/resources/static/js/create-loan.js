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
            let fee = this.fee / 100
            console.log(fee)

            axios.post("/api/loan/", "name=" + this.loanName + "&maxAmount=" + this.maxAmount + "&payments=" + this.payments + "&fee=" + fee)
                .then(res => alert("created"))
        }
    }
})


app.mount("#app")