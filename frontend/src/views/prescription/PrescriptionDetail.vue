<template>
  <v-app>
    <v-layout fill-height align-center justify-center>
      <v-card
        flat
        class="pb-10 my-5"
        width="100%"
        min-width="350"
        max-width="500"
      >
        <v-card-text>
          <v-container class="pb-0">
            <v-row>
              <v-col class="h4 font-weight-bold black--text mb-5"
                >처방전 목록</v-col
              >
            </v-row>
            <v-row class="pillList">
              <v-col class="px-0"
                ><v-list>
                  <div v-for="(item, index) in 5" :key="index">
                    <prescription-detail-list></prescription-detail-list>
                    <v-divider v-if="index !== 4" class="mt-5"></v-divider>
                  </div>
                </v-list>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
      </v-card>
    </v-layout>
  </v-app>
</template>

<script>
import PrescriptionDetailList from '../../components/prescription/PrescriptionDetailList.vue';
export default {
  components: { PrescriptionDetailList },
  data() {
    return {};
  },
};
</script>

<style scoped>
.v-application {
  background-color: #565ca9;
}
.ellipsis {
  width: 100%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.date {
  color: #9c9c9c;
}
.pillList {
  border: solid 1px Gainsboro;
}
</style>
