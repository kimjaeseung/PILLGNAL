import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: {},
  },
  getters: {
    user: ({ user }) => user
  },
  mutations: {
    SET_USER(state, user) {
      state.user = user;
    }
  },
  actions: {
    login({ commit }, user) {
      // axios
      // request: user(아이디, 비밀번호)
      // response: user(아이디, 이름, 전화번호), token
      // 성공했을 때,
      // localStorage에 access-token 저장
      // user state 저장
      let token = 'test';
      localStorage.setItem('access-token', token);
      commit('SET_USER', user);
    },
    getUserByToken({ commit }) {
      // axios
      // request: token
      // response: user(아이디, 이름, 전화번호)
      // 성공했을 때,
      // user state 저장
      // commit('SET_USER');
      let token = localStorage['access-token']
      if (token || token !== '') return;
      //임시 모듈
      if (token != 'test') return;
      let user = { id: "temp_id", name: "temp_name", phoneNumber: "전화번호" };
      
      commit('SET_USER', user);
    },
    logout({ commit }) {
      localStorage.clear();
      commit('SET_USER', null);
    }
  },
  modules: {},
});
