<template>
  <div class="text-center">
    <v-bottom-sheet v-model="sheet">
      <template v-slot:activator="{ on, attrs }">
        <v-btn color="main" v-bind="attrs" v-on="on" class="white--text" rounded large>
          <v-icon class="picture">mdi-camera</v-icon>사진 찍기
        </v-btn>
      </template>
      <v-list class="text-center">
        <v-subheader><strong>촬영 모드</strong>를 선택해주세요.</v-subheader>
        <v-list-item v-for="tile in tiles" :key="tile.title" @click="sheet = false">
          <!-- <v-list-item-avatar>
            <v-avatar size="32px" tile>
              <img
                :src="`https://cdn.vuetifyjs.com/images/bottom-sheets/${tile.img}`"
                :alt="tile.title"
              />
            </v-avatar>
          </v-list-item-avatar> -->
          <v-list-item-title>{{ tile.title }}</v-list-item-title>
        </v-list-item>
        <v-col>
          <v-btn @click="cancel()" block elevation="0" large color="sub">취소</v-btn>
        </v-col></v-list
      >
    </v-bottom-sheet>
  </div>
</template>
<script>
export default {
  data: () => ({
    sheet: false,
    tiles: [
      { img: 'keep.png', title: '알약 찍기💊' },
      { img: 'inbox.png', title: '처방전, 약 봉투 찍기📃' },
    ],
  }),
  methods: {
    cancel() {
      this.sheet = false;
    },
  },
};
</script>
<style scoped>
.picture {
  padding-right: 8px;
}
.v-subheader {
  display: inline-flex;
}
</style>
