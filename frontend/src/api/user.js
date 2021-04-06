import axios from 'axios';
import { API_BASE_URL } from '@/config';
import router from '@/router/index.js';

const instance = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Headers': '*',
    'Access-Control-Allow-Credentials': true,
    'Content-Type': 'application/json',
  },
});

export const regist = async (user) => {
  let resp = '';
  await instance
    .post('user/signup', JSON.stringify(user))
    .then((response) => {
      if (response.data.success === true) resp = 'true';
      else {
        resp = response.data.error;
      }
    })
    .catch(() => {
      resp = '통신 오류입니다';
    });
  return resp;
};

export const login = async (user) => {
  let resp = 0;
  await instance
    .post('user/login', JSON.stringify(user))
    .then((resp) => {
      console.log(resp.data.data);
      router.push('/home');
    })
    .catch((e) => {
      if (e.response === undefined) resp = 0;
      else resp = e.response.status;
    });

  return resp;
};
