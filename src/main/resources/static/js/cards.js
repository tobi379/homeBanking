const app = Vue.createApp({

    data() {
        return {
            name: "",
            lname: "",
            cards: [],
            cardsDebito: [],
            cardsCredito: [],
            showDebit: true,
            showCredit: true,
            type: "",
            color: "",
        }

    },

    created() {
        axios.get("/api/clients/current")
            .then(res => {
                console.log(res)
                this.clientes = res.data
                this.name = this.clientes.firstName
                this.lname = this.clientes.lastName
                this.cards = res.data.cards
                this.cards.sort((a, b) => a.id - b.id)
                console.log(this.cards)
                this.cardsDebito = this.cards.filter(e => e.type == 'DEBIT')
                console.log(this.cardsDebito)
                this.cardsCredito = this.cards.filter(e => e.type == 'CREDIT')
                console.log(this.cardsCredito)
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
        overdue(date) { 
            let hoy = new Date().toJSON().slice(0, 10).split('-').join('-');
            if (date.valueOf() < hoy.valueOf()) {
                console.log(hoy, date); return true
            }
        }
    },

})


app.mount("#app")