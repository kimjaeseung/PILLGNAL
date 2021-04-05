<template>
  <v-app>
    <v-layout fill-height align-center justify-center>
      <v-card class="d-flex justify-center">
        <v-container class="mt-16 mb-5 mx-16">
          <v-row class="mb-10">
            <v-col class="text-center text-h4 font-weight-bold"> 로그인 </v-col>
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
                ></v-text-field>
              </v-col>
            </v-row>
          </v-form>
          <v-row class="mt-10">
            <v-col class="pa-0">
              <v-btn x-large block outlined color="primary" :disabled="!valid"
                >로그인</v-btn
              >
            </v-col>
          </v-row>
          <v-row class="mt-16 mb-3">
            <v-col class="d-flex justify-center pa-0"
              >Need an account?
              <router-link class="ml-2" :to="{ path: 'regist' }"
                >sign up</router-link
              ></v-col
            >
          </v-row>
        </v-container>
      </v-card>
    </v-layout>
  </v-app>
</template>

<script>
import { emailRules, passwordRules } from '@/api/valid.js';

export default {
  data() {
    return {
      user: { email: '', password: '' },
      show: false,
      valid: false,
      emailRules: emailRules,
      passwordRules: passwordRules,
    };
  },
};
</script>

<style scoped>
.v-application {
  background-color: #565ca9;
}
</style>
