<template>
  <v-btn :color="btnColor" elevation="0">
    {{ btnText }}
  </v-btn>
</template>

<script>
export default {
  props: {
    btnColor: String,
    btnText: String,
  },
  data: () => {
    return {};
  },
};
</script>

<style></style>
