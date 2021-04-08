<template>
  <v-container>
    <NavBar />
    <v-container v-for="(pillList, idx) in pillLists" :key="idx">
      <NewsFeedCard :pill-data="pillList" />
    </v-container>
    <v-container>
      <v-row>
        <div class="d-flex flex-row mx-auto">
          <!-- 카메라 -->
          <camera-mode class="mx-2" />
        </div>
      </v-row>
    </v-container>
  </v-container>
</template>

<script>
import NavBar from '../components/NavBar.vue';
import NewsFeedCard from '@/components/NewsFeedCard.vue';
import CameraMode from '@/components/CameraMode.vue';

export default {
  name: 'Home',
  components: {
    NavBar,
    NewsFeedCard,
    CameraMode,
  },
  data: function () {
    return {
      voice: '',
      pillLists: [
        {
          date: '2021-03-31 12:00',
          isDone: 'waiting',
          pills: [
            {
              pname: '아스코푸정',
              img: require('@/assets/pills/아스코푸정.jpg'),
              cnt: 1,
            },
            {
              pname: '징카민정40mg',
              img: require('@/assets/pills/징카민정40mg.jpg'),
              cnt: 2,
            },
            {
              pname: '진셀몬정',
              img: require('@/assets/pills/진셀몬정.jpg'),
              cnt: 3,
            },
          ],
        },
        {
          date: '2021-03-31 07:30',
          isDone: 'skip',
          pills: [
            {
              pname: '아스코푸정',
              img: require('@/assets/pills/아스코푸정.jpg'),
              cnt: 1,
            },
            {
              pname: '징카민정40mg',
              img: require('@/assets/pills/징카민정40mg.jpg'),
              cnt: 2,
            },
            {
              pname: '진셀몬정',
              img: require('@/assets/pills/진셀몬정.jpg'),
              cnt: 3,
            },
          ],
        },
        {
          date: '2021-03-30 18:00',
          isDone: 'done',
          pills: [
            {
              pname: '아스코푸정',
              img: require('@/assets/pills/아스코푸정.jpg'),
              cnt: 1,
            },
            {
              pname: '징카민정40mg',
              img: require('@/assets/pills/징카민정40mg.jpg'),
              cnt: 2,
            },
            {
              pname: '진셀몬정',
              img: require('@/assets/pills/진셀몬정.jpg'),
              cnt: 3,
            },
          ],
        },
      ],
    };
  },
  methods: {
    getPillList() {},
  },
};
</script>

<style scoped>
.container,
.container-fluid,
.container-sm,
.container-md,
.container-lg,
.container-xl {
  width: 100%;
  padding-right: 8px;
  padding-left: 8px;
  margin-right: auto;
  margin-left: auto;
}
</style>
