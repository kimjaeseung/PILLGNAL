<template>
  <v-row>
    <v-col cols="12">
      <v-card class="mt-4" outlined>
        <v-card-title>
          <span class="title font-weight-bold">처방전 이름</span>
        </v-card-title>
        <v-col>
          <v-card outlined>
            <v-card-actions v-for="(item, idx) in pills" :key="idx" class="text-center">
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
            v-model="value"
            :items="items"
            chips
            large-chips
            label="복용 시간"
            multiple
            color="main"
          ></v-autocomplete>
        </v-col>
        <v-col class="d-flex justify-space-between align-center">
          <v-autocomplete
            id="time"
            v-model="time"
            :items="times"
            :label="time"
            color="main"
          ></v-autocomplete>
          <v-btn elevation="0" color="main" class="white--text" @click="showTimer()"
            >직접입력</v-btn
          >
        </v-col>
      </v-card>
      <v-card class="mt-4" height="100px" outlined>
        <v-row class="mt-6">
          <v-col align-self="center" class="text-center">
            <v-icon>mdi-plus</v-icon>
          </v-col></v-row
        >
      </v-card>
    </v-col>
    <v-col
      ><v-btn block color="main" large elevation="0" class="white--text" @click="confirm()"
        >완료</v-btn
      >
      <time-picker :dialog="dialog" @close="close" @value="values" /> </v-col
  ></v-row>
</template>

<script>
import TimePicker from '@/components/TimePicker.vue';
export default {
  components: {
    TimePicker,
  },
  data: () => ({
    pills: [
      {
        pname: '아스코푸정',
        img: require('@/assets/pills/아스코푸정.jpg'),
        cnt: 1,
      },
    ],
    dialog: false,
    select: null,
    items: ['아침', '점심', '저녁'],
    times: ['식후 30분', '식전 30분', '식후 바로'],
    time: null,
    value: null,
  }),
  methods: {
    confirm() {
      this.$router.push('/prescription');
    },
    showTimer() {
      this.dialog = true;
    },
    close(isDialog) {
      this.dialog = isDialog;
    },
    values(value) {
      this.time = value;
      console.log(this.time);
    },
  },
};
</script>
<style scoped>
.mdi:before,
.mdi-set {
  color: #565ca9;
}
.v-autocomplete >>> .primary--text {
  color: #565ca9;
  caret-color: #565ca9;
}
.v-text-field >>> .v-input__control {
  color: inherit;
  padding-right: 12px;
}
</style>
