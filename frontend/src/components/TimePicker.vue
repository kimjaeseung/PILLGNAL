<template>
  <v-row justify="center">
    <b-modal v-model="isDialog" size="lg" centered max-width="600px" @ok="handleOk">
      <b-time v-model="value" show-seconds locale="ko">
        <div class="justify-center">
          <b-button size="sm" variant="outline-danger" v-if="value" @click="clearTime">
            초기화
          </b-button>
          <b-button size="sm" variant="outline-primary" class="ml-auto" @click="setNow">
            지금 시간으로 설정하기
          </b-button>
        </div>
      </b-time>
    </b-modal></v-row
  >
</template>

<script>
export default {
  data() {
    return {
      isDialog: false,
      value: null,
    };
  },
  methods: {
    setNow() {
      const now = new Date();
      // Grab the HH:mm:ss part of the time string
      this.value = now.toTimeString().slice(0, 8);
    },
    clearTime() {
      this.value = '';
    },
    handleOk() {
      this.$emit('value', this.value);
    },
  },
  watch: {
    dialog: function () {
      this.isDialog = this.dialog;
      this.$emit('close', this.isDialog);
    },

    isDialog: function () {
      this.$emit('close', this.isDialog);
    },
  },
  props: {
    dialog: Boolean,
  },
};
</script>
<style scoped>
.d-inline-flex {
  display: inline !important;
}
</style>
