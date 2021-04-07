import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);

export default new Vuetify({
  theme: {
    themes: {
      light: {
        primary: '#1b2253',
        main: '#565CA9',
        sub: '#F4F4F4',
        main_dark: '#1b2253',
        kakao_yellow: '#FEE500',
        remove_pink: '#FE8F83',
        medicine_cycle_grey: '#DFDFDF',
        test: '#46443E',
        medicine_warning: '#FCD462',
      },
    },
  },
});
