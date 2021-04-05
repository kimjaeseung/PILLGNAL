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
      await getKakaoToken(code);
      const kakaoUserData = await getKakaoUserInfo();
      console.log(kakaoUserData);
    },
  },
};
</script>

<style></style>
