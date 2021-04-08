<template>
  <v-container>
    <back-Nav page-title="주의 성분 관리🥽" />
    <v-row class="mt-12">
      <v-col>
        <v-row justify="center">
          <v-col cols="10">
            <v-card class="text-center">
              <v-avatar tile class="mt-7"><img src="@/assets/attention/drugs.svg" /></v-avatar>
              <v-card-subtitle
                >주의해야할 성분이 있으신가요? <br />
                <strong>주의 성분을 검색해 등록</strong>해주세요!</v-card-subtitle
              >
            </v-card>
          </v-col></v-row
        >
        <attention-add-btn />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import AttentionAddBtn from '@/components/AttentionAddBtn.vue';
import BackNav from '@/base_components/BackNav.vue';
export default {
  data: () => ({}),
  components: {
    AttentionAddBtn,
    BackNav,
  },
};
</script>
<style scoped></style>
