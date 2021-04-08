<template>
  <v-row justify="center">
    <b-modal v-model="isDialog" size="lg" centered max-width="600px" @ok="handleOk">
      <b-calendar v-model="dateValue" @context="onContext" locale="Kr">
        <div class="justify-center">
          <b-button size="sm" variant="outline-danger" v-if="dateValue" @click="clearTime">
            초기화
          </b-button>
        </div>
      </b-calendar>
    </b-modal></v-row
  >
</template>

<script>
export default {
  data() {
    return {
      isDialog: false,
      dateValue: null,
    };
  },
  methods: {
    clearTime() {
      this.dateValue = '';
    },
    handleOk() {
      this.$emit('dateValue', this.dateValue);
    },
    onContext(ctx) {
      this.dateValue = ctx.selectedYMD;
    },
  },
  watch: {
    dateDialog: function () {
      this.isDialog = this.dateDialog;
      this.$emit('dateClose', this.isDialog);
    },

    isDialog: function () {
      this.$emit('dateClose', this.isDialog);
    },
  },
  props: {
    dateDialog: Boolean,
  },
};
</script>
<style scoped>
.d-inline-flex {
  display: inline !important;
}
.modal-body {
  text-align: center;
}
</style>
