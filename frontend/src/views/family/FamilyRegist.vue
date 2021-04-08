<template>
  <v-container>
    <back-Nav page-title="가족 등록🥰" />
    <v-row class="mt-12">
      <v-col>
        <div class="text-caption text-center">
          <strong>동의를 얻은 가족의 핸드폰 번호를 입력해주세요.</strong>
        </div>

        <v-row justify="center">
          <v-col cols="11" class="mt-5">
            <VuePhoneNumberInput v-model="phoneNumber" />
          </v-col>
        </v-row>

        <v-row justify="center">
          <v-col cols="9" class="mt-5">
            <v-card outlined class="text-caption text-center card-padding textarea"
              >가족회원 추가는 앱을 통한 <strong>동의 요청 및 승인 과정</strong>을 거쳐야 합니다.
              <br />
              동의를 얻을 가족회원님의 핸드폰에서 앱을 켜고 동의 요청을 승낙하세요.
            </v-card>
          </v-col>
        </v-row>
        <confirm-cancel-btn @cancel="cancel" @confirm="confirm" />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import ConfirmCancelBtn from '@/components/ConfirmCancelBtn.vue';
import VuePhoneNumberInput from 'vue-phone-number-input';
import BackNav from '@/base_components/BackNav.vue';
import 'vue-phone-number-input/dist/vue-phone-number-input.css';
export default {
  data: () => ({
    phoneNumber: '',
  }),
  components: {
    VuePhoneNumberInput,
    ConfirmCancelBtn,
    BackNav,
  },
  methods: {
    confirm: function () {
      this.$router.push({ name: 'FamilyRegistList', params: { phoneNumber: this.phoneNumber } });
    },
    cancel: function () {
      this.$router.go(-1);
    },
  },
};
</script>
<style scoped>
.card-padding {
  padding: 12px;
}
.textarea {
  background-color: #fafafa;
}
.textcolor {
  color: white;
}
</style>
