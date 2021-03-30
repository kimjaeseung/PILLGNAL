<template>
  <div>
    <v-container>
      <!-- Profile 설정 -->
      <v-row class="my-3">
        <v-list-item-content>
          <div class="d-flex justify-center">
            <v-avatar
              width="75"
              height="75"
              color="brown"
              class="d-flex"
            >
              <v-img :src="user.src"></v-img>
            </v-avatar>
            <div class="d-flex mx-5 flex-column text-center my-auto">
              <h3>{{ user.fullName }}</h3>
              <p class="caption mt-1 mb-0">
                {{ user.email }}
              </p>
            </div>
          </div>
        </v-list-item-content>
      </v-row>
      <!-- WeekMission -->
      <v-container>
        <h3 class="mb-5">이번주 미션</h3>
        <v-simple-table
          dense
          max-width="350"
        >
          <thead>
            <tr>
              <th class="text-center border-none">월</th>
              <th class="text-center border-none">화</th>
              <th class="text-center border-none">수</th>
              <th class="text-center border-none">목</th>
              <th class="text-center border-none">금</th>
              <th class="text-center border-none">토</th>
              <th class="text-center border-none">일</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td
                v-for="(weekday, idx) in weekMission"
                :key="idx"
                class="tableImg"
              >
                <v-avatar>
                  <v-img 
                  :src="weekday.img"></v-img>
                </v-avatar>
              </td>
            </tr>
          </tbody>
        </v-simple-table>
      </v-container>
      <!-- Reward -->
      <Reward />
    </v-container>
  </div>
</template>

<script>
import Reward from '@/components/Reward.vue';

export default {
  components: {
    Reward,
  },
  data: () => ({
    user: {
      src: 'https://cdn.vuetifyjs.com/images/john.jpg',
      fullName: 'John Doe',
      email: 'john.doe@doe.com',
    },
    weekMission: [ 
      {img: require('@/assets/pillgu.png'), cnt: 3},
      {img: require('@/assets/pillgu.png'), cnt: 2},
      {img: require('@/assets/pillgu.png'), cnt: 1},
      {img: require('@/assets/pillgu.png'), cnt: 0},
      {img: require('@/assets/pillgu.png'), cnt: 0},
      {img: require('@/assets/pillgu.png'), cnt: 0},
      {img: require('@/assets/pillgu.png'), cnt: 0},
    ]
  }),
}
</script>

<style scoped>
.profile-card {
  max-width: 350px;
}
.border-none {
  border: none !important;
}
.tableImg {
  object-fit: cover;
  max-width: 38px;
  padding: 0 !important;
}
.v-data-table__wrapper {
  overflow-x: hidden !important;
}
</style>