<template>
  <v-container class="my-3">
    <v-row>
      <v-col> <v-img width="100%" aspect-ratio="1" src="@/assets/pillgnal_logo.png"></v-img></v-col>
    </v-row>
    <v-row>
      <v-col class="pl-0 mb-2 caption"> Sign in </v-col>
    </v-row>
    <v-form v-model="valid">
      <v-row>
        <v-col class="pa-0">
          <v-text-field
            v-model="user.email"
            :rules="[emailRules.required, emailRules.email]"
            hint="Invalid e-mail."
            label="E-mail"
            outlined
            flat
            dense
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row class="pa-0">
        <v-col class="pa-0">
          <v-text-field
            v-model="user.password"
            :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[passwordRules.required, passwordRules.min]"
            :type="show ? 'text' : 'password'"
            maxlength="25"
            label="Password"
            hint="At least 8 characters"
            class="input-group--focused"
            @click:append="show = !show"
            outlined
            flat
            dense
          ></v-text-field>
        </v-col>
      </v-row>
    </v-form>
    <v-row class="mt-5">
      <v-col class="pa-0">
        <v-btn large block color="primary" @click="loginBtn">로그인</v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col class="px-0">
        <v-btn
          class="font-weight-black kakaoBtn"
          large
          block
          color="kakao_yellow"
          @click="loginWithKakao"
          >카카오 로그인</v-btn
        >
      </v-col>
    </v-row>
    <v-row class="mt-12 mb-1">
      <v-col class="d-flex justify-center pa-0"
        >Need an account?
        <router-link class="ml-2 font-weight-bold" :to="{ path: 'regist' }"
          >sign up</router-link
        ></v-col
      >
    </v-row>
    <v-snackbar v-model="snackbar"
      >{{ snackbarMessage }}
      <template v-slot:action="{ attrs }">
        <v-btn color="pink" text v-bind="attrs" @click="snackbar = false"> Close </v-btn>
      </template></v-snackbar
    >
  </v-container>
</template>

<script>
import { emailRules, passwordRules } from '@/api/valid.js';
import { login } from '@/api/user.js';
export default {
  methods: {
    async loginBtn() {
      if (!this.valid) {
        this.snackbar = true;
        this.snackbarMessage = '아이디 혹은 비밀번호를 확인해주세요.';
      } else {
        let resp = await login(this.user);
        if (resp == 500) {
          this.snackbarMessage = '이메일 또는 비밀번호가 틀렸습니다.';
        } else if (resp == 1) {
          this.snackbarMessage = '로그인 성공!';
        } else {
          this.snackbarMessage = '통신 오류입니다.';
        }
        this.snackbar = true;
      }
    },
    loginWithKakao() {
      const params = {
        redirectUri: 'http://localhost:8081/auth',
      };
      window.Kakao.Auth.authorize(params);
    },
  },
  data() {
    return {
      user: { email: '', password: '' },
      show: false,
      valid: false,
      emailRules: emailRules,
      passwordRules: passwordRules,
      snackbar: false,
      snackbarMessage: '',
    };
  },
};
</script>

<style></style>
