<template>
  <v-app>
    <v-layout fill-height align-center justify-center>
      <v-card>
        <v-container class="mb-10 mt-10">
          <v-row class="mb-10 justify-center">
            <v-col class="mb-10">
              <v-img src="@/assets/pillgnal_logo.png"></v-img>
            </v-col>
          </v-row>
          <v-row class="mt-10">
            <v-col class="pa-0">
              <v-btn
                class="white--text font-weight-black"
                x-large
                block
                color="main_dark"
                @click="toLoginPage"
              >
                로그인</v-btn
              >
            </v-col>
          </v-row>
          <v-row class="mt-10">
            <v-col class="pa-0">
              <v-btn
                class="font-weight-black kakaoBtn"
                x-large
                block
                color="kakao_yellow"
                @click="loginWithKakao"
                >카카오 로그인</v-btn
              >
            </v-col>
          </v-row>
          <v-row class="mt-10">
            <v-col class="pa-0">
              <v-btn
                class="font-weight-black registBtn"
                x-large
                block
                @click="toRegistPage"
                >회원가입</v-btn
              >
            </v-col>
          </v-row>
        </v-container>
      </v-card>
    </v-layout>
  </v-app>
</template>

<script>
export default {
  methods: {
    toLoginPage() {
      this.$router.push({ name: 'Login' });
    },
    toRegistPage() {
      this.$router.push({ name: 'Regist' });
    },
    loginWithKakao() {
      const params = {
        redirectUri: 'http://localhost:8080/auth',
      };
      window.Kakao.Auth.authorize(params);
    },
  },
};
</script>

<style scoped>
.v-application {
  background-color: #565ca9;
}
.kakaoBtn {
  color: #4f4a13;
}
.registBtn {
  color: #4a509f;
}
.container {
  max-width: 450px;
  width: 70%;
}
</style>
