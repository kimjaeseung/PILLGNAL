import axios from 'axios';
import { API_BASE_URL } from '@/config';
// import router from '@/router/index.js';
import store from '@/store/index.js';

const instance = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Headers': '*',
    'Access-Control-Allow-Credentials': true,
    'Content-Type': 'application/json',
  },
});

export const refreshToken = async () => {
  await instance
    .post('auth/refrash', localStorage['access-token'])
    .then((response) => {
      if (response.data.success === false) {
        store.dispatch('logout');
      } else {
        localStorage.setItem('access-token', response.data.authToken);
      }
    })
    .catch((e) => {
      console.log(e);
      store.dispatch('logout');
    });
};

export const getUser = async () => {
  await instance
    .post('auth/userinfo', localStorage['access-token'])
    .then((response) => {
      store.dispatch('login', response.data.data);
    })
    .catch(() => {
      store.dispatch('logout');
    });
};
