<template>
  <div class="max-container">
    <v-toolbar class="mt-1" elevation="0">
      <!-- 아바타 자리 -->
      <v-btn icon @click="routeBack()">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <v-spacer></v-spacer>
      <h3 class="my-auto font">{{ pageTitle }}</h3>
      <v-spacer></v-spacer>
    </v-toolbar>
  </div>
</template>

<script>
export default {
  props: {
    pageTitle: String,
  },
  methods: {
    routeBack: function () {
      this.$router.go(-1);
    },
  },
  data: () => {
    return {};
  },
};
</script>

<style scoped>
.font {
  font-family: 'GmarketSansMedium' !important;
}
@font-face {
  font-family: 'GmarketSansMedium';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff')
    format('woff');
  font-weight: normal;
  font-style: normal;
}
</style>
