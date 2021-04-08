<template>
  <v-row>
    <v-col cols="12">
      <v-col cols="11" class="d-flex justify-space-between align-center">
        <v-text-field
          placeholder="처방전 이름"
          class="prescriptionName"
          single-line
          clearable
          v-model="title"
          :disabled="disabled"
        ></v-text-field>
        <v-icon @click="edit()" v-show="show">mdi-pencil</v-icon>
      </v-col>
      <v-col v-for="(item, idx) in pills" :key="idx" class="tt">
        <v-card class="mt-2" outlined>
          <v-col>
            <v-card outlined>
              <v-card-actions class="text-center">
                <v-list-item class="grow">
                  <v-list-item-avatar tile width="100px">
                    <v-img class="elevation-6" :alt="item.pillname" :src="img"> </v-img>
                  </v-list-item-avatar>

                  <v-list-item-content>
                    <v-list-item-title>{{ item.pillname }}</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-card-actions>
            </v-card>
          </v-col>
          <v-col cols="12">
            <v-autocomplete
              single-line
              v-model="value"
              :items="items"
              chips
              large-chips
              label="복용 시간"
              placeholder="언제 복용하시나요?"
              multiple
              color="main"
              @click="on(idx)"
            ></v-autocomplete>
          </v-col>
          <v-col class="d-flex justify-space-between align-center">
            <v-autocomplete
              class="time"
              v-model="time"
              :items="times"
              :label="time"
              placeholder="몇 시에 복용하시나요?"
              color="main"
              @click="on(idx)"
            ></v-autocomplete>
            <v-btn elevation="0" color="main" class="white--text" @click="showTimer()"
              >직접입력</v-btn
            >
          </v-col>
          <time-picker :dialog="dialog" @close="close" @value="values" />

          <v-col class="d-flex justify-space-between align-center">
            <v-text-field type="number" v-model="item.count" label="1회 투약량"></v-text-field>
            <v-text-field type="number" v-model="item.daycount" label="일 투여횟수"></v-text-field>
            <v-text-field v-model="item.volume" label="총 투여횟수"></v-text-field>
          </v-col>
          <v-col class="d-flex justify-space-between align-center">
            <v-text-field
              class="time"
              v-model="item.startday"
              label="복용 시작일"
              disabled
            ></v-text-field>
            <v-btn @click="showDatePicker(0, idx)" elevation="0" color="test" class="white--text"
              >복용 시작일</v-btn
            >
          </v-col>
          <v-col class="d-flex justify-space-between align-center">
            <v-text-field
              class="time"
              v-model="item.endday"
              label="복용 마감일"
              disabled
            ></v-text-field>
            <v-btn @click="showDatePicker(1, idx)" elevation="0" color="test" class="white--text"
              >복용 마감일</v-btn
            >
          </v-col>
        </v-card>
      </v-col>
      <v-card class="mt-4" height="100px" outlined>
        <v-card-actions @click="cardAdd()">
          <v-row class="mt-6">
            <v-col align-self="center" class="text-center">
              <v-icon>mdi-plus</v-icon>
            </v-col></v-row
          ></v-card-actions
        >
      </v-card>
    </v-col>
    <v-col
      ><v-btn block color="main" large elevation="0" class="white--text" @click="confirm()"
        >완료</v-btn
      >
      <date-picker
        :dateDialog="dateDialog"
        @dateClose="dateClose"
        @dateValue="dateValues"
      /> </v-col
  ></v-row>
</template>

<script>
import axios from 'axios';
import { API_BASE_URL } from '@/config';
import DatePicker from '@/components/DatePicker.vue';
import TimePicker from '@/components/TimePicker.vue';
export default {
  components: {
    DatePicker,
    TimePicker,
  },
  data: () => ({
    presciption: ['d', 'd'],
    img: require('@/assets/pills/아스코푸정.jpg'),
    title: '',
    idx: '',
    isChanged: false,
    pills: [],
    dialog: false,
    dateDialog: false,
    select: null,
    items: ['아침', '점심', '저녁'],
    times: ['식후 30분', '식전 30분', '식후 바로'],
    time: null,
    value: null,
    disabled: true,
    show: true,
    mode: '',
  }),
  created() {
    console.log(this.presciption[1]);
    for (var i = 0; i < this.presciption.length; i++) {
      this.pills.push({
        afternoon: false,
        afternoontime: '',
        count: null,
        daycount: null,
        endday: '',
        morning: false,
        morningtime: '',
        night: false,
        nighttime: '0',
        pillname: '',
        startday: '',
        volumn: '',
      });
      this.pills[i].pillname = this.presciption[i];
    }
  },
  methods: {
    on(idx) {
      this.idx = idx;
    },
    confirm() {
      var email = this.$store.getters.user.email;
      var pilllist = [];

      for (var i = 0; i < this.pills.length; i++) {
        pilllist.push(this.pills[i]);
      }

      console.log(email, pilllist, this.title);
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
        .post('/pill/create', {
          email: email,
          pilllist: pilllist,
          title: this.title,
        })
        .then((res) => {
          console.log(res);
          this.data = res;
          this.$router.push('/prescription');
        })
        .catch((err) => {
          console.log('실패', err);
        });
    },

    showTimer() {
      this.dialog = true;
    },
    showDatePicker(n, idx) {
      this.dateDialog = true;
      this.mode = n;
      this.idx = idx;
    },
    close(isDialog) {
      this.dialog = isDialog;
    },
    dateClose(isDialog) {
      this.dateDialog = isDialog;
    },
    values(value) {
      this.time = value;
      this.realTime = value;
    },
    dateValues(value) {
      if (this.mode == 0) {
        this.pills[this.idx].startday = value;
        this.isChanged = true;
      } else {
        this.pills[this.idx].endday = value;
        this.isChanged = false;
      }
    },
    edit() {
      this.disabled = false;
      this.show = false;
    },
    onContext(ctx) {
      this.context = ctx;
      console.log(this.context);
    },
    cardAdd() {
      this.$router.push('/prescription');
    },
  },
  watch: {
    isChanged: function () {
      if (this.pills[this.idx].startday > this.pills[this.idx].endday) {
        if (this.pills[this.idx].endday != '') {
          alert('마감일보다 시작일이 느릴 수 없습니다');
          this.pills[this.idx].startday = '';
          this.pills[this.idx].endday = '';
        }
      }
    },
    time: function () {
      if (this.time == this.times[0]) {
        this.pills[this.idx].afternoontime = '30';
        this.pills[this.idx].morningtime = '30';
        this.pills[this.idx].nighttime = '30';
      } else if (this.time == this.times[1]) {
        this.pills[this.idx].afternoontime = '-30';
        this.pills[this.idx].morningtime = '-30';
        this.pills[this.idx].nighttime = '-30';
      } else if (this.time == this.times[2]) {
        this.pills[this.idx].afternoontime = '0';
        this.pills[this.idx].morningtime = '0';
        this.pills[this.idx].nighttime = '0';
      }
    },
    value: function () {
      for (var i = 0; i < this.value.length; i++) {
        this.pills[this.idx].morning = false;
        this.pills[this.idx].afternoon = false;
        this.pills[this.idx].night = false;
        if (this.value[i] == '아침') {
          this.pills[this.idx].morning = true;
        }
        if (this.value[i] == '점심') {
          this.pills[this.idx].afternoon = true;
        }
        if (this.value[i] == '저녁') {
          this.pills[this.idx].night = true;
        }
      }
    },
  },
};
</script>

<style scoped>
.tt {
  padding-left: 2px;
  padding-right: 2px;
}
.prescriptionName.v-input >>> input {
  font-size: 1.5em;
}
.mdi:before,
.mdi-set {
  color: #565ca9;
}
.v-autocomplete >>> .primary--text {
  color: #565ca9;
  caret-color: #565ca9;
}
.time.v-text-field >>> .v-input__control {
  color: inherit;
  padding-right: 12px;
}
.v-card__subtitle,
.v-card__text,
.v-card__title {
  padding: 8px;
}
</style>
