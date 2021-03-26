<template>
  <v-app style="background-color: #565ca9">
    <v-layout fill-height align-center>
      <v-container>
        <v-row class="mb-16">
          <v-col></v-col>
          <v-col cols="9">
            <v-img src="@/assets/pillgnal_logo.png"></v-img>
          </v-col>
          <v-col></v-col>
        </v-row>
        <v-row class="mt-10">
          <v-col class="pa-0">
            <v-btn
              class="white--text font-weight-black"
              x-large
              block
              color="#1b2253"
              @click="toLoginPage"
            >
              로그인</v-btn
            >
          </v-col>
        </v-row>
        <v-row class="mt-10">
          <v-col class="pa-0">
            <v-btn
              class="font-weight-black"
              x-large
              block
              color="#FEE500"
              @click="loginWithKakao"
              >카카오 로그인</v-btn
            >
          </v-col>
        </v-row>
        <v-row class="mt-10">
          <v-col class="pa-0">
            <v-btn
              class="font-weight-black"
              x-large
              block
              style="color: #4a509f"
              @click="toRegistPage"
              >회원가입</v-btn
            >
          </v-col>
        </v-row>
      </v-container>
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
.container {
  max-width: 450px;
  width: 70%;
}
</style>
