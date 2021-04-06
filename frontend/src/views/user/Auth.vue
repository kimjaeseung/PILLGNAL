<template>
  <div>인증페이지</div>
</template>

<script>
import { getKakaoToken, getKakaoUserInfo } from '@/api/kakaoLogin.js';

export default {
  created() {
    this.getUserInfo();
  },
  methods: {
    async getUserInfo() {
      const code = this.$route.query.code;
      const token = await getKakaoToken(code);
      window.Kakao.Auth.setAccessToken(token.data.access_token, true);
      const kakaoUserData = await getKakaoUserInfo();
      console.log(kakaoUserData);
    },
  },
};
</script>

<style></style>
