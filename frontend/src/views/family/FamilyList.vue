<template>
  <v-container>
    <div class="text-h4 mt-5">가족 관리</div>
    <v-row class="mt-12">
      <v-col>
        <v-row justify="center">
          <v-col cols="10" md="6">
            <v-card class="text-center">
              <v-avatar class="mt-7"><img src="@/assets/family.svg" /></v-avatar>
              <v-card-subtitle
                >등록된 가족 구성원이 없습니다. <br />
                <strong>가족 구성원을 등록</strong>해주세요!</v-card-subtitle
              >
            </v-card>
          </v-col></v-row
        >
        <family-add-btn />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import FamilyAddBtn from '../../components/FamilyAddBtn.vue';
export default {
  data: () => ({}),
  components: {
    FamilyAddBtn,
  },
};
</script>
<style scoped></style>
