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
            <v-row v-for="index in 3" :key="index">
              <v-col>
                <prescription-item :info="infos[0]"></prescription-item>
              </v-col>
            </v-row>
            <v-row class="mt-10">
              <v-col class="pb-0">종료된 처방전</v-col>
            </v-row>
            <v-row v-for="index in 3" :key="index">
              <v-col>
                <prescription-item
                  class="transparent"
                  :info="infos[0]"
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
      infos: [
        {
          title: '환절기 피부질환',
          date: '2020.05.30',
        },
      ],
    };
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
