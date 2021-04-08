<template>
  <v-app id="back">
    <v-container>
      <BackNav page-title="복약 용법 설정😎" />
      <howto-eat-card :prescription="prescription" />
    </v-container>
  </v-app>
</template>
<script>
import HowtoEatCard from '../components/HowtoEatCard.vue';
import BackNav from '@/base_components/BackNav.vue';
export default {
  data: () => ({}),
  components: {
    HowtoEatCard,
    BackNav,
  },
  props: ['prescription'],
  methods: {},
  created() {
    console.log(this.prescription);
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
