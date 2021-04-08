<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="11" md="7">
        <div v-for="user in users" :key="user.name">
          <v-card v-if="!user.isNone">
            <v-list-item two-line>
              <v-avatar><img src="@/assets/settings/help.svg" /></v-avatar>
              <v-list-item-title
                ><strong>{{ user.name }}</strong
                ><br />
                {{ user.phoneNumber }}</v-list-item-title
              >
              <v-card-actions>
                <v-col class="text-right">
                  <v-btn @click="familyRequest()" elevation="0" color="main" class="textcolor"
                    >요청</v-btn
                  ></v-col
                >
              </v-card-actions>
            </v-list-item>
          </v-card>
        </div>
        <v-card v-if="isNone" class="text-center">
          <v-avatar tile class="mt-7"><img src="@/assets/search.svg" /></v-avatar>
          <v-card-subtitle
            >찾으시는 사용자가 없네요. <br />
            <strong>다시 한번 입력</strong>해주세요!</v-card-subtitle
          >
        </v-card>
      </v-col></v-row
    ></v-container
  >
</template>
<script>
import axios from 'axios';
import { API_BASE_URL } from '@/config';
export default {
  data: () => ({
    isNone: true,
    users: [
      {
        name: '',
        phoneNumber: '',
        email: '',
        isNone: true,
      },
    ],
  }),
  created: function () {
    const instance = axios.create({
      baseURL: API_BASE_URL,
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': '*',
        'Access-Control-Allow-Credentials': true,
        'Content-Type': 'application/json',
      },
    });
    instance
      .post('user/phone', { phone: this.phoneNumber })
      .then((res) => {
        var temp = res.data;
        this.users[0].name = temp.name;
        this.users[0].phoneNumber = temp.phone;
        this.users[0].email = temp.email;
        this.users[0].isNone = false;
        this.isNone = false;
      })
      .catch((err) => {
        console.log('찾기 실패', err);
      });
  },
  methods: {
    familyRequest: function () {
      var email = this.$store.getters.user.email;
      if (email == this.users[0].email) {
        alert('본인에게는 가족 요청을 맺을 수 없습니다.');
        this.$router.push('/familyList');
      } else {
        const instance = axios.create({
          baseURL: API_BASE_URL,
          headers: {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': '*',
            'Access-Control-Allow-Credentials': true,
            'Content-Type': 'application/json',
          },
        });
        instance
          .post('family/connect', {
            myEmail: email,
            otherEmail: this.users[0].email,
          })
          .then((res) => {
            console.log(res);
            alert('요청되었습니다.');
            this.$router.push('/familylist');
          })
          .catch((err) => {
            console.log('실패', err);
          });
      }
    },
  },
  props: ['phoneNumber'],
};
</script>
<style scoped>
.textcolor {
  color: white;
}
.v-list-item .v-list-item__title,
.v-list-item .v-list-item__subtitle {
  line-height: 1.2;
  margin-left: 20px;
}
.text-right.col {
  padding: 0px;
}
.v-card__actions {
  padding: 0px;
}
.v-list-item {
  padding-top: 8px;
  padding-bottom: 8px;
}
</style>
