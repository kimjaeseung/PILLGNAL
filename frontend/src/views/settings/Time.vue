<template>
  <v-container>
    <div class="text-h4 mt-5">설정</div>
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
export default {
  data: () => ({
    times: ['07:00', '12:00', '19:00'],
    selected: '',
    dialog: false,
    value: null,
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
      console.log(value);
    },
  },
  components: {
    TimePicker,
  },
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
