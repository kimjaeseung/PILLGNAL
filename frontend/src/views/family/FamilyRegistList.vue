<template>
  <v-container>
    <div class="text-h4 mt-5">가족 등록</div>
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
export default {
  data: () => ({}),
  components: {
    FamilyAddBtn,
    FamilyCard,
  },
  props: ['phoneNumber'],
};
</script>
