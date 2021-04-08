<template>
  <v-btn :color="btnColor" elevation="1" class="square-lg" large>
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

<style scoped>
.square-lg {
  width: 100%
}
</style>
