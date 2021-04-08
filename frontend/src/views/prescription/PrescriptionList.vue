<template>
  <v-app>
    <v-layout fill-height align-center justify-center>
      <v-card
        flat
        class="pb-10 my-5"
        width="90%"
        min-width="350"
        max-width="500"
      >
        <v-card-text class="pa-0">
          <v-container class="pa-0">
            <v-row class="mt-5">
              <v-col class="h4 font-weight-bold black--text">처방전 목록</v-col>
            </v-row>
            <v-row class="mt-5">
              <v-col class="pb-0">진행중인 처방전</v-col>
            </v-row>
            <v-row v-for="(info, index) in infos" :key="index">
              <v-col>
                <prescription-item :info="info"></prescription-item>
              </v-col>
            </v-row>
            <v-row class="mt-10">
              <v-col class="pb-0">종료된 처방전</v-col>
            </v-row>
            <v-row v-for="(info, index) in old_infos" :key="index">
              <v-col>
                <prescription-item
                  class="transparent"
                  :info="info"
                ></prescription-item>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
      </v-card>
    </v-layout>
  </v-app>
</template>

<script>
import PrescriptionItem from '../../components/prescription/PrescriptionItem.vue';
export default {
  components: { PrescriptionItem },
  data() {
    return {
      infos: [],
      old_infos: [],
    };
  },

  created() {
    console.log(this.$store.getters.user);
    // 리스트 받아오는 액시오스 수행
    let data = [
      { id: 1, title: '처방전1', registDay: '2021-04-06' },
      { id: 2, title: '처방전2', registDay: '2021-04-07' },
      { id: 3, title: '처방전3', registDay: '2021-04-08' },
      { id: 3, title: '처방전3', registDay: '2021-04-05' },
    ];

    let today = new Date();

    for (let i = 0; i < data.length; i++) {
      data[i].compareDay = new Date(data[i].registDay);
      // axios로 약 전부 불러와서 가장 기간이 긴 약이 현재보다 이전 시간이면 old
      let maxDayCount = 5;
      let endDateObj = new Date(data[i].registDay);
      endDateObj.setDate(endDateObj.getDate() + maxDayCount);
      data[i].endDay = endDateObj;
      data[i].term = maxDayCount;
      let diff = Math.ceil(
        (today.getTime() - data[i].compareDay.getTime()) / (1000 * 3600 * 24)
      );
      data[i].eatenTerm = diff > maxDayCount ? maxDayCount + 1 : diff;
    }

    data.sort((a, b) => {
      if (a.compareDay > b.compareDay) return -1;
      else if (a.compareDay == b.compareDay) return 0;
      else return 1;
    });

    for (let i = 0; i < data.length; i++) {
      if (today > data[i].endDay) this.old_infos.push(data[i]);
      else this.infos.push(data[i]);
    }

    console.log(data);
  },
};
</script>

<style></style>

<style scoped>
.container {
  max-width: 500px;
  width: 80%;
}
.v-application {
  background-color: #565ca9;
}
.transparent {
  opacity: 0.65;
}
</style>
