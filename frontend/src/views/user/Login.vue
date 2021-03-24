<template>
  <v-app>
    <v-layout fill-height align-center>
      <v-container>
        <v-row class="mb-3">
          <v-col class="text-center"> <h1>로그인</h1> </v-col>
        </v-row>
        <v-row>
          <v-col class="pa-0">
            <v-text-field
              v-model="email"
              :rules="[emailRules.required, emailRules.email]"
              hint="Invalid e-mail."
              label="E-mail"
              outlined
              flat
              solo-inverted
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
              label="Password"
              hint="At least 8 characters"
              class="input-group--focused"
              @click:append="show = !show"
              outlined
              count
              flat
              solo-inverted
            ></v-text-field>
          </v-col>
        </v-row>
      </v-container>
    </v-layout>
    <v-btn x-large tile color="primary">로그인</v-btn>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      email: '',
      password: '',
      name: '',
      phoneNumber: '',
      show: false,
      emailRules: {
        required: (value) => !!value || 'Required.',
        counter: (value) => value.length <= 20 || 'Max 20 characters',
        email: (value) => {
          const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
          return pattern.test(value) || 'Invalid e-mail.';
        },
      },
      passwordRules: {
        required: (value) => !!value || 'Required.',
        min: (v) => v.length >= 8 || 'Min 8 characters',
      },
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
