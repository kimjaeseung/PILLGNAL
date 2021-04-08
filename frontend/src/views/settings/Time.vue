<template>
  <v-container>
    <BackNav page-title="시간 설정😊" />
    <v-row class="mt-5">
      <v-col>
        <v-card @click="showModal(0)" outlined class="card-list text-center">
          <v-card-subtitle class="text-left"
            ><v-avatar tile><img src="@/assets/time/dawn.svg" /></v-avatar>아침</v-card-subtitle
          >
          <v-card-title class="text-center">{{ times[0] }}</v-card-title>
        </v-card>

        <v-card @click="showModal(1)" outlined class="card-list mt-2">
          <v-card-subtitle class="text-left"
            ><v-avatar tile><img src="@/assets/time/sun.svg" /></v-avatar>점심</v-card-subtitle
          ><v-card-title>{{ times[1] }}</v-card-title></v-card
        >
        <v-card @click="showModal(2)" outlined class="card-list mt-2">
          <v-card-subtitle class="text-left"
            ><v-avatar tile><img src="@/assets/time/sunset.svg" /></v-avatar>저녁</v-card-subtitle
          ><v-card-title>{{ times[2] }}</v-card-title></v-card
        >
        <time-picker :dialog="dialog" @close="close" @value="values" />
      </v-col>
    </v-row>
    <v-row> </v-row>
  </v-container>
</template>
<script>
import TimePicker from '../../components/TimePicker.vue';
import BackNav from '@/base_components/BackNav.vue';
import axios from 'axios';
import { API_BASE_URL } from '@/config';
const API_URL = API_BASE_URL;

export default {
  data: () => ({
    times: ['07:00', '12:00', '19:00'],
    selected: '',
    dialog: false,
    value: null,
    user: Object,
  }),
  methods: {
    router: function (n) {
      this.$router.push(this.path[n]);
    },
    close(isDialog) {
      this.dialog = isDialog;
    },
    showModal(time) {
      this.dialog = true;
      this.selected = time;
    },
    values(value) {
      this.times[this.selected] = value;
      const email = this.user.email
      //axios POST user/breakfast
      const instance = axios.create({
        baseURL: API_URL,
        headers: {
          'Access-Control-Allow-Origin': '',
          'Access-Control-Allow-Headers': '',
          'Access-Control-Allow-Credentials': true,
          'Content-Type': 'application/json',
        }
      })
      if (this.selected === 0) {
        instance.post(
          'user/breakfast', { "email": email, "time": value}
        ).then((res) => {
          console.log(res)
        }).catch((err) => {
          console.log(err)
        })
      } else if (this.selected === 1) {
          instance.post(
          'user/lunch', { "email": email, "time": value}
        ).then((res) => {
          console.log(res)
        }).catch((err) => {
          console.log(err)
        })
      } else {
          instance.post(
          'user/dinner', { "email": email, "time": value}
        ).then((res) => {
          console.log(res)
        }).catch((err) => {
          console.log(err)
        })
      }
    },
    getUser() {
      this.user = this.$store.getters.getUser;
    },
  },
  components: {
    TimePicker,
    BackNav,
  },
  created: function () {
    this.getUser();
  }
};
</script>
<style scoped>
#logout {
  color: red;
}
.v-card__actions {
  align-items: center;
  display: block;
  padding: 8px;
}
.card-list {
  padding: 8px;
}
.v-avatar {
  padding: 8px;
}
.v-card__title {
  text-align: right !important;
  display: inherit;
  padding-right: 50px;
  font-size: 2rem;
}
.v-avatar {
  margin-right: 20px !important;
}
</style>
