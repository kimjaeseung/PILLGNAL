<template>
  <div>
    <v-container>
      <v-row class="mb-3">
        <v-col class="pl-0 my-2 font-weight-bold text-h5"> Sign up </v-col>
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
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
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
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="pa-0">
            <v-text-field
              v-model="user.name"
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
              v-model="user.phone"
              :rules="[phoneNumberRules.required, phoneNumberRules.phoneNumber]"
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
      <v-row>
        <v-col class="px-0"
          ><v-btn
            x-large
            block
            color="primary"
            :disabled="!valid || !termsCheck"
            @click="regist(user)"
            >가입하기</v-btn
          ></v-col
        >
      </v-row>
    </v-container>
  </div>
</template>

<script>
import {
  emailRules,
  passwordRules,
  nameRules,
  phoneNumberRules,
  terms,
} from '@/api/valid.js';
import { regist } from '@/api/user.js';

export default {
  data() {
    return {
      user: {
        email: '',
        password: '',
        name: '',
        phone: '',
      },
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
  methods: {
    regist: regist,
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
