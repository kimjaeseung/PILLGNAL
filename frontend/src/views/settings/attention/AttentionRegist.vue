<template>
  <v-container>
    <div class="text-h4 mt-5">주의 성분 검색</div>
    <v-row class="mt-12">
      <v-col>
        <v-row justify="center">
          <v-col cols="11" class="d-flex justify-space-between align-center">
            <v-autocomplete
              v-model="value"
              :items="items"
              prepend-inner-icon="mdi-pill"
              color="main"
            ></v-autocomplete>
            <v-btn elevation="0" color="main" class="white--text" @click="attentionlistAdd()"
              ><v-icon>mdi-plus</v-icon></v-btn
            ></v-col
          >
          <div v-for="li in list" :key="li" :cols="10">
            <v-col>
              <v-card>
                <v-card-title class="text-center">{{ li }}</v-card-title>
              </v-card>
            </v-col>
          </div>
        </v-row>
        <confirm-cancel-btn @cancel="cancel" @confirm="confirm" />
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import ConfirmCancelBtn from '@/components/ConfirmCancelBtn.vue';
export default {
  data: () => ({
    value: null,
    list: [],
    items: ['타르색소', '안식향산나트륨', '아황산나트륨', '아질산나트륨', '코치닐 색소'],
  }),
  components: {
    ConfirmCancelBtn,
  },
  methods: {
    attentionlistAdd() {
      var flag = true;
      for (var i = 0; i < this.list.length; i++) {
        if (this.list[i] == this.value) {
          flag = false;
        }
      }
      if (flag) {
        this.list.push(this.value);
      }
    },
    confirm() {
      // axios로 주의 성분 넘겨주기
      this.$router.push('/attention');
    },
    cancel() {
      this.$router.push('/attention');
    },
  },
};
</script>
<style scoped>
.v-text-field >>> .v-input__control {
  color: inherit;
  padding-right: 12px;
}
</style>
