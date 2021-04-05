<template>
  <v-btn
    rounded
    :color="btnColor"
  >
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
    return {
    }
  }
}
</script>

<style>

</style>