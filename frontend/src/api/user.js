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

export const regist = (user) => {
  instance.post('user/signup', JSON.stringify(user)).then(() => {
    router.push('/');
  });
};
