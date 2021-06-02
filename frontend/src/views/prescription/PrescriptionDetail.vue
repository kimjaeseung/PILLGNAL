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
                  <div v-for="(info, index) in infos" :key="index">
                    <prescription-detail-list
                      :info="info"
                    ></prescription-detail-list>
                    <v-divider
                      v-if="index !== infos.length - 1"
                      class="mt-5"
                    ></v-divider>
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
import { API_BASE_URL } from '@/config';
import axios from 'axios';

export default {
  components: { PrescriptionDetailList },
  data() {
    return {
      infos: [],
    };
  },
  async created() {
    console.log(this.$route.params.presNo);
    const instance = axios.create({
      baseURL: API_BASE_URL,
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': '*',
        'Access-Control-Allow-Credentials': true,
        'Content-Type': 'application/json',
      },
    });
    let user = this.$store.getters.user;
    await instance
      .get('prescription/' + user.email + '/' + this.$route.params.presNo)
      .then((response) => {
        this.infos = response.data.data;
      });

    //axios로 끌어오기
    // let data = [
    //   {
    //     morning: true,
    //     afternoon: true,
    //     night: true,
    //     name: '광동플레리토로마이신정25',
    //     count: 3,
    //     daycount: 5,
    //     startday: '2021-04-02',
    //     endday: '2021-04-10',
    //     pillUrl:
    //       'https://dbscthumb-phinf.pstatic.net/3323_000_9/20171126022112845_RQZOH3G3T.jpg/A11A4290B001503.jpg?type=m250&wm=N',
    //   },
    // ];
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
