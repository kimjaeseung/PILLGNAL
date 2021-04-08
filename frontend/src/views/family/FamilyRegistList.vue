<template>
  <v-container>
    <back-Nav page-title="가족 등록🥰" />
    <v-row class="mt-12">
      <v-col>
        <family-card :phoneNumber="phoneNumber" />
        <family-add-btn /> </v-col
    ></v-row>
  </v-container>
</template>
<script>
import FamilyAddBtn from '../../components/FamilyAddBtn.vue';
import FamilyCard from '../../components/FamilyCard.vue';
import BackNav from '@/base_components/BackNav.vue';

export default {
  data: () => ({}),
  components: {
    FamilyAddBtn,
    FamilyCard,
    BackNav,
  },
  created() {},
  props: ['phoneNumber'],
};
</script>
