import axios from 'axios';
import { API_BASE_URL } from '@/config';
const instance = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Headers': '*',
    'Access-Control-Allow-Credentials': true,
    'Content-Type': 'application/json',
  },
  //   withCredentials: true,
});

export const regist = (user) => {
  console.log(JSON.stringify(user));
  instance.post('user/signup', JSON.stringify(user)).then(() => {
    console.log('가입성공');
  });
};
