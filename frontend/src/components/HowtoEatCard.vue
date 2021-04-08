<template>
  <v-row>
    <v-col cols="12">
      <v-col cols="11" class="d-flex justify-space-between align-center">
        <v-text-field
          placeholder="처방전 이름"
          class="prescriptionName"
          single-line
          clearable
          :disabled="disabled"
        ></v-text-field>
        <v-icon @click="edit()" v-show="show">mdi-pencil</v-icon>
      </v-col>

      <v-card class="mt-2" outlined v-for="(item, idx) in pills" :key="idx">
        <v-col>
          <v-card outlined>
            <v-card-actions class="text-center">
              <v-list-item class="grow">
                <v-list-item-avatar tile width="100px">
                  <v-img class="elevation-6" :alt="item.pname" :src="item.img"> </v-img>
                </v-list-item-avatar>

                <v-list-item-content>
                  <v-list-item-title>{{ item.pname }}</v-list-item-title>
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
          ></v-autocomplete>
          <v-btn elevation="0" color="main" class="white--text" @click="showTimer()"
            >직접입력</v-btn
          >
        </v-col>
        <time-picker :dialog="dialog" @close="close" @value="values" />

        <v-col class="d-flex justify-space-between align-center">
          <v-text-field v-model="onepill" label="1회 투약량"></v-text-field>
          <v-text-field v-model="daypill" label="일 투여횟수"></v-text-field>
          <v-text-field v-model="allpill" label="총 투여횟수"></v-text-field>
        </v-col>
        <v-col class="d-flex justify-space-between align-center">
          <v-text-field
            class="time"
            v-model="dateStart"
            label="복용 시작일"
            disabled
          ></v-text-field>
          <v-btn @click="showDatePicker(0)" elevation="0" color="test" class="white--text"
            >복용 시작일</v-btn
          >
        </v-col>
        <v-col class="d-flex justify-space-between align-center">
          <v-text-field class="time" v-model="dateEnd" label="복용 마감일" disabled></v-text-field>
          <v-btn @click="showDatePicker(1)" elevation="0" color="test" class="white--text"
            >복용 마감일</v-btn
          >
        </v-col>
      </v-card>
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
import DatePicker from '@/components/DatePicker.vue';
import TimePicker from '@/components/TimePicker.vue';
export default {
  components: {
    DatePicker,
    TimePicker,
  },
  data: () => ({
    presciption: '',
    pills: [
      {
        pname: '아스코푸정',
        img: require('@/assets/pills/아스코푸정.jpg'),
        cnt: 1,
      },
      {
        pname: '아스코푸정',
        img: require('@/assets/pills/아스코푸정.jpg'),
        cnt: 1,
      },
    ],
    dialog: false,
    dateDialog: false,
    select: null,
    items: ['아침', '점심', '저녁'],
    times: ['식후 30분', '식전 30분', '식후 바로'],
    time: null,
    value: null,
    disabled: true,
    show: true,
    dateStart: '',
    dateEnd: '',
    mode: '',
    onepill: '',
    daypill: '',
    allpill: '',
    realTime: '',
  }),
  methods: {
    confirm() {
      this.$router.push('/prescription');
    },
    showTimer() {
      this.dialog = true;
    },
    showDatePicker(n) {
      this.dateDialog = true;
      this.mode = n;
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
        this.dateStart = value;
      } else {
        this.dateEnd = value;
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
    dateStart: function () {
      if (this.dateStart > this.dateEnd) {
        if (this.dateEnd != '') {
          alert('마감일보다 시작일이 느릴 수 없습니다');
          this.dateStart = '';
          this.dateEnd = '';
        }
      }
    },
    dateEnd: function () {
      if (this.dateStart > this.dateEnd) {
        alert('마감일보다 시작일이 느릴 수 없습니다');
        this.dateStart = '';
        this.dateEnd = '';
      }
    },
    time: function () {
      if (this.time == this.times[0]) {
        this.realTime = 30;
      } else if (this.time == this.times[1]) {
        this.realTime = -30;
      } else if (this.time == this.times[2]) {
        this.realTime = 0;
      }
    },
  },
};
</script>

<style scoped>
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
