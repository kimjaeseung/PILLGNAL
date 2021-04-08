import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: {},
    voice: {},
    time: {}
  },
  getters: {
    user: ({ user }) => user,
    getVoice: function (state) {
      return state.voice;
    },
    getUser: function (state) {
      return state.user;
    }
  },
  mutations: {
    SET_USER(state, user) {
      state.user = user;
    },
    SET_USER_PROFILE(state, imageUrl) {
      state.user.imageUrl = imageUrl;
    },
    SET_USER_VOICE(state, src) {
      state.voice.src = src;
    },
    SET_TIME(state, time) {
      state.time.breakfast = time.breakfast
      state.time.lunch = time.lunch
      state.time.dinner = time.dinner
    }
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
    SAVE_VOICE({ commit }, audioUrl) {
      // console.log('action>>>>>', audioUrl)
      commit('SET_USER_VOICE', audioUrl)
    },
    SAVE_TIME({ commit }, time) {
      commit('SET_TIME', time)
    }
  },
  modules: {},
});
