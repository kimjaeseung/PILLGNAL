import Vue from "vue";
import Vuetify from "vuetify/lib/framework";

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        themes: {
            light: {
                primary: '#1b2253',
                main: '#565CA9',
                sub: '#F4F4F4',
                test: '#d8ac9c'
            }
        }
    }

});
