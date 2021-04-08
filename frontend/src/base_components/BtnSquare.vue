<template>
  <v-btn class="square-md" :color="btnColor" elevation="0" medium>
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
.square-md {
  width: 100%
}
</style>
