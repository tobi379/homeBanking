const app = Vue.createApp ({
    data() {
        return {
            accounts : [],
        }
    },
    created() {
        const urlParams = new URLSearchParams(window.location.search);
        const myParam = urlParams.get('id');
        // console.log(myParam)

        axios.get('/api/accounts/' + myParam)
        .then(res => {
            this.accounts = res.data
            this.accounts.transactions.sort((a,b) => parseInt(b.id - a.id))
            console.table(this.accounts)
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
        }
    }
})

app.mount("#app")