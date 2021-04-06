import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: {},
  },
  getters: {
    user: ({ user }) => user,
  },
  mutations: {
    SET_USER(state, user) {
      state.user = user;
    },
  },
  actions: {
    login({ commit }, user) {
      console.log('action>>>>>', user);
      commit('SET_USER', user);
    },
    logout({ commit }) {
      localStorage.removeItem('access-token');
      commit('SET_USER', null);
    },
  },
  modules: {},
});
