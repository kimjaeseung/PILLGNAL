<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="11" md="7">
        <div v-for="user in users" :key="user.name">
          <v-card v-if="!user.isNone">
            <v-list-item two-line>
              <v-avatar><img src="@/assets/settings/help.svg" /></v-avatar>
              <v-list-item-title
                ><strong>{{ user.name }}</strong
                ><br />
                {{ phoneNumber }}</v-list-item-title
              >
              <v-card-actions>
                <v-col class="text-right">
                  <v-btn @click="familyRequest()" elevation="0" color="main" class="textcolor"
                    >요청</v-btn
                  ></v-col
                >
              </v-card-actions>
            </v-list-item>
          </v-card>
        </div>
        <v-card v-if="isNone" class="text-center">
          <v-avatar tile class="mt-7"><img src="@/assets/search.svg" /></v-avatar>
          <v-card-subtitle
            >찾으시는 사용자가 없네요. <br />
            <strong>다시 한번 입력</strong>해주세요!</v-card-subtitle
          >
        </v-card>
      </v-col></v-row
    ></v-container
  >
</template>
<script>
export default {
  data: () => ({
    isNone: true,
    users: [
      {
        name: '김싸피',
        phoneNumber: '010-6666-6666',
        isNone: true,
      },
      {
        name: '이지은',
        phoneNumber: '010-3333-3333',
        isNone: true,
      },
      {
        name: '임지호',
        phoneNumber: '010-5555-5555',
        isNone: true,
      },
    ],
  }),
  created: function () {
    for (var i = 0; i < this.users.length; i++) {
      if (this.users[i].phoneNumber == this.phoneNumber) {
        this.users[i].isNone = false;
        this.isNone = false;
      }
    }
  },
  methods: {
    familyRequest: function () {
      alert('요청되었습니다.');
      this.$router.push('/familyList');
    },
  },
  props: ['phoneNumber'],
};
</script>
<style scoped>
.textcolor {
  color: white;
}
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
