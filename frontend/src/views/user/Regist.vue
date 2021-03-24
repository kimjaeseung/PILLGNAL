<template>
  <v-app>
    <v-layout fill-height align-center>
      <v-container>
        <v-row class="mb-3">
          <v-col class="text-center"> <h1>회원가입</h1> </v-col>
        </v-row>
        <v-form v-model="valid">
          <v-row>
            <v-col class="pa-0">
              <v-text-field
                v-model="email"
                :rules="[emailRules.required, emailRules.email]"
                hint="Invalid e-mail."
                label="E-mail"
                outlined
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col class="pa-0">
              <v-text-field
                v-model="password"
                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[passwordRules.required, passwordRules.min]"
                :type="show ? 'text' : 'password'"
                maxlength="25"
                label="Password"
                hint="At least 8 characters"
                class="input-group--focused"
                @click:append="show = !show"
                outlined
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col class="pa-0">
              <v-text-field
                v-model="name"
                :rules="[nameRules.required, nameRules.min]"
                counter
                maxlength="25"
                hint="At least 2 characters"
                label="User name"
                outlined
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col class="pa-0">
              <v-text-field
                v-model="phoneNumber"
                :rules="[
                  phoneNumberRules.required,
                  phoneNumberRules.phoneNumber,
                ]"
                hint="Please include hyphens"
                label="Phone number"
                outlined
              ></v-text-field>
            </v-col>
          </v-row>
        </v-form>
        <v-row>
          <v-col class="px-0">
            <v-card height="200px">
              <v-card-title>회원약관</v-card-title>
              <v-card-text>{{ terms }}</v-card-text></v-card
            >
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="6" class="pa-0"
            ><v-checkbox v-model="termsCheck" label="동의합니다."></v-checkbox
          ></v-col>
        </v-row>
      </v-container>
    </v-layout>
    <v-btn x-large tile color="primary" :disabled="!valid || !termsCheck"
      >가입하기</v-btn
    >
  </v-app>
</template>

<script>
import {
  emailRules,
  passwordRules,
  nameRules,
  phoneNumberRules,
  terms,
} from '@/api/valid.js';

export default {
  data() {
    return {
      email: '',
      password: '',
      name: '',
      phoneNumber: '',
      termsCheck: false,
      show: false,
      valid: false,
      emailRules: emailRules,
      passwordRules: passwordRules,
      nameRules: nameRules,
      phoneNumberRules: phoneNumberRules,
      terms: terms,
    };
  },
};
</script>

<style scoped>
.v-card {
  overflow: auto;
}
.container {
  max-width: 500px;
  width: 80%;
}
</style>
