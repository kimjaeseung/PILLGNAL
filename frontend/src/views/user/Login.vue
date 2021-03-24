<template>
  <v-app>
    <v-layout fill-height align-center>
      <v-container>
        <v-row class="mb-3">
          <v-col class="text-center"> <h1>로그인</h1> </v-col>
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
                flat
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
                flat
              ></v-text-field>
            </v-col>
          </v-row>
        </v-form>
        <v-row class="mt-5">
          <v-col class="pa-0">
            <v-btn x-large block outlined color="primary" :disabled="!valid"
              >로그인</v-btn
            >
          </v-col>
        </v-row>
      </v-container>
    </v-layout>
  </v-app>
</template>

<script>
import { emailRules, passwordRules } from '@/api/valid.js';

export default {
  data() {
    return {
      email: '',
      password: '',
      show: false,
      valid: false,
      emailRules: emailRules,
      passwordRules: passwordRules,
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
