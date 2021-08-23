const app = Vue.createApp({

    data() {
        return {
            cards: [],
            cardsDebito: [],
            cardsCredito: [],
            type: "",
            color: "",
            showDebit: true,
            showCredit: true,
        }

    },

    created() {
        axios.get("/api/clients/current")
            .then(res => {
                console.log(res)
                this.clientes = res.data
                this.cards = res.data.cards
                this.cardsDebito = this.cards.filter(e => e.type == 'DEBIT')
                this.cardsCredito = this.cards.filter(e => e.type == 'CREDIT')
                // console.log(this.cardsDebito)
                // console.log(this.cardsCredito)
            })
            .catch(function (error) {
                // handle error
                console.log(error);
            })


    },
    methods: {
        logout () {
            axios.post('/api/logout')
              .then(res => window.location.href = "/index.html")
          },
        addCard() {
            axios.post('/api/clients/current/cards', "cardColor=" + this.color + "&cardType=" + this.type,
                {
                    headers: {
                        'content-type': 'application/x-www-form-urlencoded',
                    },
                }
            )
                .then(res => window.location.href = "/cards.html")
        },
        fecha(fecha) {
            fecha = fecha.split("").splice(0, 7).join("")
            return fecha
        },
        deleteCard(id) {
            axios.put("/api/clients/current/cards", "id=" + id)
                .then(resp => {
                    console.log("borrada")
                    location.reload()
                })
        },
    },

})


app.mount("#app")