<template>
  <v-card
    class="mx-auto"
    color="#26c6da"
    dark
    max-width="400"
  >
    <v-card-title>
      <v-icon
        left
      >
        mdi-twitter
      </v-icon>
      <span class="title font-weight-light"> oo일 점심에 드셔야 할 약</span>
    </v-card-title>
    <v-card-actions 
    v-for="(item, idx) in pills"
    :key="idx"
    >
      <v-list-item class="grow">
        <v-list-item-avatar color="grey darken-3">
          <v-img
            tile
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
        <v-btn>
          복용
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn>
          건너뛰기
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn>
          다시 알림
        </v-btn>
      </v-list-item>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
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