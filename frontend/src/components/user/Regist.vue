<template>
  <div>
    <v-container>
      <v-row class="mb-3">
        <v-col class="pl-0 caption"> Sign up </v-col>
      </v-row>
      <v-form v-model="valid" class="mb-10">
        <v-row>
          <v-col class="pa-0">
            <v-text-field
              v-model="user.email"
              :rules="[emailRules.required, emailRules.email]"
              hint="Invalid e-mail."
              label="E-mail"
              outlined
              dense
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
              dense
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
              dense
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="pa-0">
            <VuePhoneNumberInput
              v-model="user.phone"
              @update="updatePhoneNumber"
            ></VuePhoneNumberInput>
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
    <v-btn
      x-large
      block
      tile
      color="primary"
      :disabled="!valid || !termsCheck || !phoneIsValid"
      @click="registBtn(user)"
      >가입하기</v-btn
    >
    <v-snackbar v-model="snackbar"
      >{{ snackbarMessage }}.
      <template v-slot:action="{ attrs }">
        <v-btn color="pink" text v-bind="attrs" @click="snackbar = false">
          Close
        </v-btn>
      </template></v-snackbar
    >
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
import VuePhoneNumberInput from 'vue-phone-number-input';
import 'vue-phone-number-input/dist/vue-phone-number-input.css';

export default {
  components: {
    VuePhoneNumberInput,
  },
  data() {
    return {
      user: {
        email: '',
        password: '',
        name: '',
        phone: '',
      },
      phoneIsValid: false,
      termsCheck: false,
      show: false,
      valid: false,
      emailRules: emailRules,
      passwordRules: passwordRules,
      nameRules: nameRules,
      phoneNumberRules: phoneNumberRules,
      terms: terms,
      snackbar: false,
      snackbarMessage: '',
    };
  },
  methods: {
    async registBtn() {
      const resp = await regist(this.user);
      if (resp === 'true') this.$router.push('/');
      else {
        this.snackbarMessage = resp;
        this.snackbar = true;
      }
    },
    updatePhoneNumber(data) {
      this.phoneIsValid = data.isValid;
    },
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
.theme--light.v-text-field--outline:not(.v-input--is-focused):not(.v-input--has-state)
  > .v-input__control
  > .v-input__slot:hover {
  border-color: silver;
}
</style>
