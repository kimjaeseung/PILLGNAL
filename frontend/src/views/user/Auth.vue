<template>
  <v-layout fill-height align-center justify-center>
    <v-card
      flat
      class="pa-10 my-5"
      width="100%"
      min-width="350"
      max-width="500"
    >
      <v-container>
        <v-row>
          <v-col class="pa-0">
            <v-form v-model="valid">
              <v-text-field
                v-model="phone"
                :rules="[
                  phoneNumberRules.required,
                  phoneNumberRules.phoneNumber,
                ]"
                hint="Please include hyphens"
                label="Phone number"
                outlined
              ></v-text-field>
            </v-form>
          </v-col>
        </v-row>
        <v-row class="mt-5">
          <v-col class="pa-0">
            <v-btn
              class="font-weight-black kakaoBtn"
              x-large
              block
              color="kakao_yellow"
              :disabled="!valid"
              @click="registBtn"
              >카카오 회원가입</v-btn
            >
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </v-layout>
</template>

<script>
import { phoneNumberRules } from '@/api/valid.js';
import { getKakaoToken, getKakaoUserInfo } from '@/api/kakaoLogin.js';
import { regist, login } from '@/api/user.js';

export default {
  data() {
    return {
      user: {},
      phone: '',
      valid: false,
      phoneNumberRules: phoneNumberRules,
    };
  },
  async created() {
    let kakaoData = await this.getUserInfo();
    this.user.email = kakaoData.kakao_account.email;
    this.user.password = String(kakaoData.id);

    await login(this.user);
    this.user.name = kakaoData.kakao_account.profile.nickname;
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
      window.Kakao.Auth.setAccessToken(token.data.access_token, true);
      const kakaoUserData = await getKakaoUserInfo();
      return kakaoUserData;
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
