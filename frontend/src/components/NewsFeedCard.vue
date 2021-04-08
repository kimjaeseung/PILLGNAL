<template>
  <div>
    <!-- 완료 : 회색 disabled -->
    <v-card
      class="mx-auto news-card"
      v-if="pillData.isDone === 'done'"
      elevation="0"
      outlined
      disabled
    >
      <v-card-title class="d-flex flex-row justify-space-between px-5">
        <v-icon left> mdi-alarm-check </v-icon>
        <span> {{ pillData.date }} 알림 </span>
        <v-btn icon @click="show = !show">
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-title>
      <v-card-actions>
        <v-list-item class="grow">
          <BtnSquareLarge btn-color="primary" btn-text="복용 완료!" class="text-white" />
        </v-list-item>
      </v-card-actions>
    </v-card>
    <!-- 대기 : 기본 -->
    <v-card
      class="mx-auto news-card"
      v-else-if="pillData.isDone === 'waiting'"
      elevation="2"
      outlined
    >
      <v-card-title class="d-flex flex-row justify-space-between px-5">
        <v-icon left> mdi-alarm-check </v-icon>
        <span> {{ pillData.date }} 알림 </span>
        <v-btn icon @click="show = !show">
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-title>

      <v-expand-transition>
        <div v-show="show">
          <v-card-text>
            <!-- <audio controls v-if="this.flag === false">
              <source :src="getVoice.src" type="audio/ogg" />
            </audio>
            <audio controls autoplay v-else>
              <source :src="getVoice.src" type="audio/ogg" />
            </audio> -->
            <audio controls autoplay>
              <source :src="getVoice.src" type="audio/ogg" />
            </audio>
            <v-card-actions v-for="(item, idx) in pillData.pills" :key="idx">
              <v-list-item-avatar rounded width="65px">
                <v-img class="elevation-6 avatar-pill" :alt="item.pname" :src="item.img"> </v-img>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title>{{ item.pname }}</v-list-item-title>
                <!-- <v-list-item-subheader>{{ item.cnt }}정</v-list-item-subheader> -->
                <v-list-item-subtitle>{{ item.cnt }}정</v-list-item-subtitle>
              </v-list-item-content>
            </v-card-actions>
          </v-card-text>
        </div>
      </v-expand-transition>

      <v-card-actions>
        <v-list-item class="grow d-flex flex-column">
          <BtnSquare btn-color="main" class="white--text my-1" btn-text="복용" />
          <BtnSquare btn-color="test" class="text-white my-1" btn-text="30분 뒤 알림" />
          <BtnSquare btn-color="#f1f1f1" class="my-1" btn-text="건너뛰기" />
        </v-list-item>
      </v-card-actions>
    </v-card>
    <!-- Skip : 붉은색 -->
    <v-card
      class="mx-auto news-card"
      v-else-if="pillData.isDone === 'skip'"
      elevation="0"
      outlined
      disabled
    >
      <v-card-title class="d-flex flex-row justify-space-between px-5">
        <v-icon left> mdi-alarm-check </v-icon>
        <span> {{ pillData.date }} 알림 </span>
        <v-btn icon @click="show = !show">
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-title>

      <v-card-actions>
        <v-list-item class="grow">
          <BtnSquareLarge btn-color="remove_pink" btn-text="Skip!" class="text-white" />
        </v-list-item>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import BtnSquare from '@/base_components/BtnSquare.vue';
import BtnSquareLarge from '@/base_components/BtnSquareLarge.vue';
import axios from 'axios'
import { API_BASE_URL } from '@/config';
const API_URL = API_BASE_URL;

const instance = axios.create({
  baseURL: API_URL,
  headers: {
    'Access-Control-Allow-Origin': '',
    'Access-Control-Allow-Headers': '',
    'Access-Control-Allow-Credentials': true,
    'Content-Type': 'application/json',
  }
})

export default {
  name: 'NewsFeedCard',
  components: {
    BtnSquare,
    BtnSquareLarge,
  },
  props: {
    pillData: Object,
  },
  methods: {
    getUser() {
      this.user = this.$store.getters.getUser;
    },
    getTime() {
      const email = this.user.email
      instance.post(
        `user/time/${email}/`
        ).then((res) => {
          if (this.pillData.isDone === 'waiting') {
            this.times.breakfast = res.data.breakfast
            this.times.lunch = res.data.lunch
            this.times.dinner = res.data.dinner
            console.log('times :', this.times)
            console.log('times lunch :', this.times.lunch)
            console.log('times dinner :', this.times.dinner)
            this.voiceAlarm();
          }
        }).catch((err) => {
          console.log(err)
        })
    },
    voiceAlarm: function () {
      if (this.pillData.isDone === 'waiting') {
        // 현재 시간 = sum
        let nowTime = new Date()
        let hours = nowTime.getHours()
        let mins = nowTime.getMinutes()
        let secs = nowTime.getSeconds()
        let sum = hours*100+mins*10+secs
        // 아침/점심/저녁 알람 시간 = sum_time
        console.log('sum: ', sum)
        let tmp = this.times.lunch.split(':')
        let sum_time = 0;
        for (let i=0; i<tmp.length ; i++) {
          if (i == 0 ){ 
            sum_time += tmp[i]*100
          } else if ( i == 1) {
            sum_time += tmp[i]*10
          } else {
            sum_time += tmp[i]*1
          }
        }
        if (sum > sum_time) {
          this.flag = true
        }

      }
    }
  },
  computed: {
    getVoice() {
      console.log(this.$store.getters.getVoice)
      return this.$store.getters.getVoice;
    },
  },
  data: () => ({
    show: false,
    voice: '',
    now: '',
    times: {
      'breakfast': '',
      'lunch': '',
      'dinner': '',
    },
    flag: false,
  }),
  created: function () {
    this.getUser();
    this.getTime();
  },
};
</script>

<style scoped>
.news-card {
  border-radius: 18px;
}
.avatar-pill {
  object-fit: cover;
}
.v-input--selection-controls {
  margin-top: 0 !important;
}
.v-messages {
  min-height: 0 !important;
}
.v-list-item {
  min-height: 20px;
}
.v-application {
  background-color: #f1f1f1;
}
</style>
