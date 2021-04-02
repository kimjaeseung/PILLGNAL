<template>
  <div>
    <BackNav page-title="MyPage" />
    <v-container class="max-container">
      <!-- Profile 설정 -->
      <v-row class="mb-2">
        <v-list-item-content>
          <div class="d-flex justify-center">
            <v-btn-toggle
            data-tooltip-text="프로필 변경">
              <v-btn>
                <v-avatar
                  width="75"
                  height="75"
                  color="brown"
                  class="d-flex"
                  @click="toProfileImage()"
                >
                  <v-img :src="user.src"></v-img>
                </v-avatar>
              </v-btn>
            </v-btn-toggle>
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
        <h3 class="mb-5">WeekMission</h3>
        <!-- <div class="text-h3 mb-5">이번주 미션</div> -->
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
                  v-if="weekday.cnt >= 2"
                  class="good"
                  :src="weekday.img"></v-img>

                  <v-img 
                  v-else-if="weekday.cnt >= 1"
                  class="medium"
                  :src="weekday.img"></v-img>

                  <v-img 
                  v-if="weekday.cnt === 0"
                  class="bad"
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
import BackNav from '@/base_components/BackNav.vue';

export default {
  components: {
    Reward,
    BackNav
  },
  methods: {
    toProfileImage() {
      this.$router.push({ name: 'ProfileImage' });
    },
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
.v-data-table >>> .v-data-table__wrapper {
  overflow-x: hidden !important;
}
.v-btn-toggle .v-btn {
  border: none !important;
  background-color: #fff !important;
  height: 75px !important;
}
.v-btn-toggle:active {
  border: 0 !important;
  transform: translateY(4px);
}
[data-tooltip-text]:hover {
	position: relative;
}
[data-tooltip-text]:hover:after {
	content: attr(data-tooltip-text);

  position: absolute;
	top: 100%;
	left: 0;

  background-color: rgba(0, 0, 0, 0.8);
	color: #FFFFFF;
	font-size: 12px;

	z-index: 9999;
}
.bad {
  opacity: 0.3;
}
.medium {
  /* opacity: 0.6; */
  -webkit-filter: opacity(1) drop-shadow(0 0 0 #565CA9);
  filter: opacity(.5) drop-shadow(0 0 0 #565CA9);
}
.good {
  -webkit-filter: opacity(1) drop-shadow(0 0 0 #565CA9);
  filter: opacity(.7) drop-shadow(0 0 0 blue);
}
</style>