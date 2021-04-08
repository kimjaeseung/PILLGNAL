<template>
  <div>
    <v-container>
      <BackNav page-title="설정😊" />
      <v-row class="mt-5">
        <v-col>
          <v-card @click="router(0)" outlined class="text-center mb-10">
            <v-avatar class="mt-7" tile><img src="@/assets/settings/link.svg" /></v-avatar>
            <v-card-subtitle>가족 연결 관리하기</v-card-subtitle></v-card
          >
          <v-card @click="router(1)" outlined class="text-center">
            <v-avatar class="mt-7" tile><img src="@/assets/settings/clock.svg" /></v-avatar>
            <v-card-subtitle>기본 시간 설정하기</v-card-subtitle></v-card
          >
        </v-col>
        <v-col>
          <v-card @click="router(2)" outlined class="text-center mb-10">
            <v-avatar class="mt-7" tile><img src="@/assets/settings/help.svg" /></v-avatar>
            <v-card-subtitle>Pillgnal 도움말보기</v-card-subtitle></v-card
          >
          <v-card @click="router(3)" outlined class="text-center">
            <v-avatar class="mt-7" tile><img src="@/assets/settings/attention.svg" /></v-avatar>
            <v-card-subtitle>주의 성분 관리</v-card-subtitle></v-card
          >
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-card @click="router(4)" outlined class="card-list">알림 목소리 녹음하기</v-card>
          <v-card @click="router(5)" outlined class="card-list mt-2">Pillgnal 이용약관</v-card>
          <v-card @click="router(6)" outlined class="card-list mt-2">버전 정보 v.0.0.1 </v-card>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-card outlined class="text-center">
            <v-card-actions id="logout" class="text-center card-center" @click="logout">
              로그아웃
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
import BackNav from '@/base_components/BackNav.vue';

export default {
  components: {
    BackNav,
  },
  data: () => ({
    path: ['familylist', 'time', 'tutorial', 'attention', 'voice', 'service'],
  }),
  methods: {
    router: function (n) {
      this.$router.push(this.path[n]);
    },
    logout() {
      this.$store.dispatch('logout');
      this.$router.push('/');
    },
  },
};
</script>
<style scoped>
#logout {
  color: red;
}
.v-card__actions {
  align-items: center;
  display: block;
  padding: 8px;
}
.card-list {
  padding: 8px;
}
.v-avatar {
  padding: 8px;
}
.v-application--wrap {
  display: inline !important;
}
</style>
