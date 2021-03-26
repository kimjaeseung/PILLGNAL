<template>
  <v-app style="background-color: #565ca9">
    <v-layout fill-height align-center>
      <v-container>
        <v-row class="ma-0">
          <v-col class="pa-0">
            <v-carousel
              cycle
              hide-delimiter-background
              show-arrows-on-hover
              light
              height="auto"
            >
              <v-carousel-item
                v-for="(tutorial, index) in tutorials"
                :key="index"
              >
                <v-img height="400" :src="tutorial.image_link"></v-img>
                <v-sheet
                  class="text-center mb-16"
                  color="white"
                  height="100%"
                  tile
                >
                  <h2 class="pt-5">{{ tutorial.title }}</h2>
                  <p>{{ tutorial.text }}</p>
                </v-sheet>
              </v-carousel-item>
            </v-carousel>
          </v-col>
        </v-row>
        <v-row class="ma-0">
          <v-col class="pa-0">
            <v-sheet
              class="d-flex justify-center align-center"
              color="white"
              height="100px"
              tile
            >
              <v-btn
                x-large
                class="font-weight-black"
                width="90%"
                color="#565CA9"
                style="color: #ffffff"
                @click="toHome"
                >시작하기</v-btn
              >
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-layout>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      tutorials: [
        {
          title: '처방전을 찍으세요',
          text: '처방전을 찍어서 편하게 복약 관리를 시작하세요',
          image_link: require('@/assets/tutorial/tutorial1.png'),
        },
        {
          title: '알려드려요',
          text: '약 드실 시간을 알려드려요',
          image_link: require('@/assets/tutorial/tutorial2.png'),
        },
        {
          title: '내가 먹는 약 알아보기',
          text: '약을 찍어 편하게 알아보세요',
          image_link: require('@/assets/tutorial/tutorial3.png'),
        },
        {
          title: '가족과 공유',
          text: '처방전과 나의 상태를 가족과 공유할 수 있어요!',
          image_link: require('@/assets/tutorial/tutorial4.png'),
        },
      ],
    };
  },
  methods: {
    toHome() {
      this.$router.push({ name: 'Home' });
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 500px;
}
</style>
