<template>
  <v-container>
    <div class="text-h4 mt-5">가족 관리</div>
    <v-row class="mt-12">
      <v-col>
        <v-row justify="center">
          <v-col cols="11">
            <v-card v-if="isNone" class="text-center">
              <v-avatar class="mt-7"><img src="@/assets/family.svg" /></v-avatar>
              <v-card-subtitle
                >등록된 가족 구성원이 없습니다. <br />
                <strong>가족 구성원을 등록</strong>해주세요!</v-card-subtitle
              >
            </v-card>
            <div v-for="user in family" :key="user.name">
              <v-card v-if="!isNone" outlined class="mt-1">
                <v-list-item two-line>
                  <v-avatar v-if="!user.imageUrl"
                    ><img src="@/assets/settings/help.svg"
                  /></v-avatar>
                  <v-avatar v-if="user.imageUrl"><img src="user.imageUrl" /></v-avatar>
                  <v-list-item-title
                    ><strong>{{ user.name }}</strong
                    ><br />
                    {{ user.phone }}</v-list-item-title
                  >
                  <v-card-actions>
                    <v-col class="text-right">
                      <v-btn
                        @click="familyDisconnect()"
                        elevation="0"
                        small
                        color="deep-orange"
                        class="white--text"
                        >연결 끊기</v-btn
                      ></v-col
                    >
                  </v-card-actions>
                </v-list-item>
              </v-card>
            </div>
          </v-col></v-row
        >
        <family-add-btn />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import FamilyAddBtn from '../../components/FamilyAddBtn.vue';
import axios from 'axios';
import { API_BASE_URL } from '@/config';
export default {
  data: () => ({
    family: [],
    isNone: true,
  }),
  components: {
    FamilyAddBtn,
  },
  created() {
    var email = this.$store.getters.user.email;
    const instance = axios.create({
      baseURL: API_BASE_URL,
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': '*',
        'Access-Control-Allow-Credentials': true,
        'Content-Type': 'application/json',
      },
    });
    instance
      .get(`family/${email}/`)
      .then((res) => {
        console.log(res.data.data);
        for (var i = 0; i < res.data.data.length; i++) {
          this.family.push(res.data.data[i]);
        }
        this.isNone = false;
      })
      .catch((err) => {
        console.log('실패', err);
      });
  },
  methods: {
    familyDisconnct() {},
  },
};
</script>
<style scoped>
.v-list-item .v-list-item__title,
.v-list-item .v-list-item__subtitle {
  line-height: 1.2;
  margin-left: 20px;
}
.text-right.col {
  padding: 0px;
}
.v-card__actions {
  padding: 0px;
}
.v-list-item {
  padding-top: 8px;
  padding-bottom: 8px;
}
</style>
