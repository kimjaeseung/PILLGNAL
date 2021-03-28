<template>
  <v-card
    class="mx-auto"
    max-width="400"
  >
    <v-card-title>
      <v-icon
        left
      >
        mdi-twitter
      </v-icon>
      <span class="title font-weight-light"> oo일 점심</span>
    </v-card-title>
    <v-card-actions 
    v-for="(item, idx) in pills"
    :key="idx"
    >
      <v-list-item class="grow">
        <v-list-item-avatar 
        rounded
        color="grey darken-3">
          <v-img
            class="elevation-6"
            :alt="item.pname"
            :src="item.img"
          >
          </v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>{{item.pname}}</v-list-item-title>
        </v-list-item-content>

        <v-row
          align="center"
          justify="end"
        >
          <v-icon class="mr-1">
            mdi-pill
          </v-icon>
          <span class="subheading">{{ item.cnt }}</span>
        </v-row>
      </v-list-item>
    </v-card-actions>
    <v-card-actions>
      <v-list-item class="grow">
        <BtnSquare 
          btn-color="primary" 
          btn-text="복용"
        />
        <v-spacer></v-spacer>
        <BtnSquare 
          btn-color="light" 
          btn-text="건너뛰기"
        />
        <v-spacer></v-spacer>
        <BtnSquare 
          btn-color="warning" 
          btn-text="30분 뒤 알림"
        />
      </v-list-item>
    </v-card-actions>
  </v-card>
</template>

<script>
import BtnSquare from "@/base_components/BtnSquare.vue";

export default {
  components: {
    BtnSquare
  },
  data: () => ({
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
    ]
  }),
}
</script>

<style>

</style>