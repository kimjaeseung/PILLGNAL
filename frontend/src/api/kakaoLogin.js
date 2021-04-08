import axios from 'axios';

const kakaoHeader = {
  Authorization: '9e9c6cc7c8a6ad9a6443a1ec95ec9646',
  'Content-type': 'application/x-www-form-urlencoded;charset=utf-8',
};
const getKakaoToken = async (code) => {
  console.log('loginWithKakao');
  try {
    const data = {
      grant_type: 'authorization_code',
      client_id: '9fc93242ee808cc757e6344fcab689b4',
      redirect_uri: 'http://localhost:8081/auth',
      code: code,
    };
    const queryString = Object.keys(data)
      .map((k) => encodeURIComponent(k) + '=' + encodeURIComponent(data[k]))
      .join('&');
    const result = await axios.post(
      'https://kauth.kakao.com/oauth/token',
      queryString,
      { headers: kakaoHeader }
    );
    return result;
  } catch (e) {
    return e;
  }
};
const getKakaoUserInfo = async () => {
  let data = '';
  await window.Kakao.API.request({
    url: '/v2/user/me',
    success: function (response) {
      data = response;
    },
    fail: function (error) {
      console.log(error);
    },
  });
  return data;
};

export { getKakaoToken, getKakaoUserInfo };
