<template>
  <v-layout fill-height align-center justify-center>
    <v-card class="pt-10 my-5" width="100%" min-width="350" max-width="500">
      <v-container>
        <v-row>
          <v-col class="pa-0 mx-0">
            <VuePhoneNumberInput
              size="lg"
              v-model="phone"
              @update="updatePhoneNumber"
            ></VuePhoneNumberInput>
          </v-col>
        </v-row>
        <v-row class="mt-10">
          <v-col class="pa-0 mx-0">
            <v-btn
              class="font-weight-black mb-8"
              large
              block
              color="kakao_yellow"
              :disabled="!phoneIsValid"
              @click="registBtn"
              >카카오로 회원가입</v-btn
            >
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </v-layout>
</template>

<script>
import { getKakaoToken, getKakaoUserInfo } from '@/api/kakaoLogin.js';
import { regist, login } from '@/api/user.js';
import VuePhoneNumberInput from 'vue-phone-number-input';
import 'vue-phone-number-input/dist/vue-phone-number-input.css';

export default {
  components: {
    VuePhoneNumberInput,
  },

  data() {
    return {
      user: {},
      phone: '',
      phoneIsValid: false,
    };
  },
  async created() {
    let kakaoData = await this.getUserInfo();
    if (kakaoData === false) this.$router.push('/');
    else {
      this.user.email = kakaoData.kakao_account.email;
      this.user.password = String(kakaoData.id);
      await login(this.user);
      this.user.name = kakaoData.kakao_account.profile.nickname;
    }
  },
  methods: {
    async registBtn() {
      this.user.phone = this.phone;
      console.log(this.user);
      const resp = await regist(this.user);
      if (resp === 'true') this.$router.push('/');
      await login(this.user);
    },
    async getUserInfo() {
      const code = this.$route.query.code;
      const token = await getKakaoToken(code);
      if (token.data === undefined) {
        return false;
      }
      window.Kakao.Auth.setAccessToken(token.data.access_token, true);
      const kakaoUserData = await getKakaoUserInfo();
      return kakaoUserData;
    },
    updatePhoneNumber(data) {
      this.phoneIsValid = data.isValid;
    },
  },
};
</script>

<style scoped>
.layout {
  background-color: #565ca9 !important;
}
.container {
  max-width: 450px;
  width: 70%;
}
</style>
