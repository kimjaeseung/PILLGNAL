<template>
  <div>
    <!-- 완료 : 회색 disabled -->
    <v-card
      class="mx-auto news-card"
      v-if="pillData.isDone === 'done'"
      elevation="0"
      outlined
      disabled
    >
      <v-card-title class="d-flex flex-row justify-space-between px-5">
        <v-icon left> mdi-alarm-check </v-icon>
        <span> {{ pillData.date }} 알림 </span>
        <v-btn icon @click="show = !show">
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-title>
      <v-card-actions>
        <v-list-item class="grow">
          <BtnSquareLarge btn-color="primary" btn-text="복용 완료!" class="text-white" />
        </v-list-item>
      </v-card-actions>
    </v-card>
    <!-- 대기 : 기본 -->
    <v-card
      class="mx-auto news-card"
      v-else-if="pillData.isDone === 'waiting'"
      elevation="2"
      outlined
    >
      <v-card-title class="d-flex flex-row justify-space-between px-5">
        <v-icon left> mdi-alarm-check </v-icon>
        <span> {{ pillData.date }} 알림 </span>
        <v-btn icon @click="show = !show">
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-title>

      <v-expand-transition>
        <div v-show="show">
          <v-card-text>
            <audio controls>
              <source :src="getVoice.src" type="audio/ogg" />
            </audio>
            <v-card-actions v-for="(item, idx) in pillData.pills" :key="idx">
              <v-list-item-avatar rounded width="65px">
                <v-img class="elevation-6 avatar-pill" :alt="item.pname" :src="item.img"> </v-img>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title>{{ item.pname }}</v-list-item-title>
                <!-- <v-list-item-subheader>{{ item.cnt }}정</v-list-item-subheader> -->
                <v-list-item-subtitle>{{ item.cnt }}정</v-list-item-subtitle>
              </v-list-item-content>
            </v-card-actions>
          </v-card-text>
        </div>
      </v-expand-transition>

      <v-card-actions>
        <v-list-item class="grow d-flex flex-column">
          <BtnSquare btn-color="main" class="white--text my-1" btn-text="복용" />
          <BtnSquare btn-color="test" class="text-white my-1" btn-text="30분 뒤 알림" />
          <BtnSquare btn-color="#f1f1f1" class="my-1" btn-text="건너뛰기" />
        </v-list-item>
      </v-card-actions>
    </v-card>
    <!-- Skip : 붉은색 -->
    <v-card
      class="mx-auto news-card"
      v-else-if="pillData.isDone === 'skip'"
      elevation="0"
      outlined
      disabled
    >
      <v-card-title class="d-flex flex-row justify-space-between px-5">
        <v-icon left> mdi-alarm-check </v-icon>
        <span> {{ pillData.date }} 알림 </span>
        <v-btn icon @click="show = !show">
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-title>

      <v-card-actions>
        <v-list-item class="grow">
          <BtnSquareLarge btn-color="remove_pink" btn-text="Skip!" class="text-white" />
        </v-list-item>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import BtnSquare from '@/base_components/BtnSquare.vue';
import BtnSquareLarge from '@/base_components/BtnSquareLarge.vue';

export default {
  name: 'NewsFeedCard',
  components: {
    BtnSquare,
    BtnSquareLarge,
  },
  props: {
    pillData: Object,
  },
  methods: {

  },
  computed: {
    getVoice() {
      console.log(this.$store.getters.getVoice)
      return this.$store.getters.getVoice;
    },
    getUser() {
      return this.$store.getters.getUser;
    },
  },
  data: () => ({
    show: false,
    voice: '',
  }),
  created: function () {
  },
};
</script>

<style scoped>
.news-card {
  border-radius: 18px;
}
.avatar-pill {
  object-fit: cover;
}
.v-input--selection-controls {
  margin-top: 0 !important;
}
.v-messages {
  min-height: 0 !important;
}
.v-list-item {
  min-height: 20px;
}
.v-application {
  background-color: #f1f1f1;
}
</style>
