<template>
  <div>
    <NavBar />
    <v-container v-for="(pillList, idx) in pillLists" :key="idx">
      <NewsFeedCard :pill-data="pillList" />
    </v-container>
    <v-container>
      <v-row>
        <div class="d-flex flex-row mx-auto">
        <!-- 카메라 -->
          <camera-mode class="mx-2"/>
        <!-- 녹음 -->
          <v-btn color="remove_pink" class="mx-2 white--text text-center" rounded large
          @click="toVoice()">
            <v-icon class="picture">mdi-record</v-icon>
            녹음
          </v-btn>
        </div>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import NavBar from '../components/NavBar.vue';
import NewsFeedCard from '@/components/NewsFeedCard.vue';
import CameraMode from '@/components/CameraMode.vue';
// import Voice from '@/components/Voice.vue';

export default {
  name: 'Home',
  components: {
    NavBar,
    NewsFeedCard,
    CameraMode,
    // Voice
  },
  data: function () {
    return {
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
    toVoice() {
      this.$router.push({ name: 'voice' });
    },
  }
};
</script>

<style scoped></style>
