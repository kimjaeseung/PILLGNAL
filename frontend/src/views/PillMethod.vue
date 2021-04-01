<template>
  <v-app id="back">
    <v-container>
      <div class="text-h4 mt-5">복약 용법 설정</div>
      <howto-eat-card /> </v-container
  ></v-app>
</template>
<script>
import HowtoEatCard from '../components/HowtoEatCard.vue';
export default {
  data: () => ({
    path: ['familylist', 'time', 'tutorial', 'attention', 'service'],
  }),
  components: {
    HowtoEatCard,
  },
  methods: {
    router: function (n) {
      this.$router.push(this.path[n]);
    },
  },
};
</script>
<style scoped>
#back {
  background-color: #fafafa;
}
#logout {
  color: red;
}
.v-card__actions {
  align-items: center;
  display: block;
  padding: 8px;
}
.card-list {
  padding: 8px;
}
.v-avatar {
  padding: 8px;
}
</style>
