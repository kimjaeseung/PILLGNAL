<template>
  <div>
    <v-row justify="center" class="mt-12">
      <v-col cols="10">
        <v-btn @click="confirm(confirm)" block elevation="0" large color="main" class="white--text"
          >확인</v-btn
        >
      </v-col></v-row
    >
    <v-row justify="center">
      <v-col cols="10">
        <v-btn @click="cancel(cancel)" block outlined large color="main">취소</v-btn>
      </v-col>
    </v-row>
  </div>
</template>
<script>
export default {
  methods: {
    confirm(msg) {
      this.$emit('confirm', msg);
    },
    cancel(msg) {
      this.$emit('cancel', msg);
    },
  },
};
</script>
