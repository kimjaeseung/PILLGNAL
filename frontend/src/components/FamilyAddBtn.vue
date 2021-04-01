<template>
  <v-container>
    <v-row justify="center" class="mt-12">
      <v-col cols="10" md="6">
        <v-btn @click="familyAdd()" block elevation="0" large color="sub">가족 추가</v-btn>
      </v-col></v-row
    ></v-container
  >
</template>
<script>
export default {
  data: () => ({}),
  components: {},
  methods: {
    familyAdd: function () {
      this.$router.push('/familyregist');
    },
  },
};
</script>
<style scoped></style>
