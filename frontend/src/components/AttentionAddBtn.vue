<template>
  <v-container>
    <v-row justify="center" class="mt-12">
      <v-col cols="10">
        <v-btn @click="AttentionAdd()" block elevation="0" large color="sub">주의 성분 추가</v-btn>
      </v-col></v-row
    ></v-container
  >
</template>
<script>
export default {
  data: () => ({}),
  components: {},
  methods: {
    AttentionAdd: function () {
      this.$router.push('/attentionregist');
    },
  },
};
</script>
<style scoped></style>
