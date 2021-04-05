<template>
  <div class="max-container">
    <BackNav page-title="ProfileImage" />
    <v-container>
      <v-row>
        <section
        v-for="(avatar, idx) in avatarLists"
        :key="idx"
        class="col-4 text-center"
        >
          <v-card
          class="elevation-0"
          >
          <v-btn-toggle
          v-model="value"
          >
            <v-btn
            :value="`${ idx }`"
            >
              <v-avatar
              width="75"
              height="75"
              >
                <v-img 
                cover
                :src="avatar"
                ></v-img>
              </v-avatar>
            </v-btn>
          </v-btn-toggle>
          </v-card>
        </section>
      </v-row>
      <v-container>
        <v-row class="mt-10">
          <!-- dialog -->
          <v-dialog
            v-model="dialog"
            fullscreen
            hide-overlay
            transition="dialog-bottom-transition"
          >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              class="white--text font-weight-black"
              x-large
              block
              color="main"
              v-bind="attrs"
              v-on="on"
              >
                앨범에서 사진 업로드</v-btn>
          </template>
          <v-card>
            <v-toolbar
              dark
              color="main"
            >
              <v-btn
                icon
                dark
                @click="dialog = false"
              >
                <v-icon>mdi-close</v-icon>
              </v-btn>
              <v-toolbar-title>앨범 사진 찾기</v-toolbar-title>
              <v-spacer></v-spacer>
            </v-toolbar>
            <v-list>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>프로필 이미지</v-list-item-title>
                  <v-list-item-subtitle>멋진 프로필 사진을 찾아보세요!</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
            </v-list>
            <v-divider></v-divider>
            <v-container>
              <v-file-input
                label="앨범에서 선택하기"
                filled
                prepend-icon="mdi-camera"
                v-model="file"
              ></v-file-input>
              <v-btn
                class="white--text font-weight-black"
                x-large
                block
                color="main"
                @click="[albumFileSave(), dialog = false]"
                >
                저장하기</v-btn>
            </v-container>
          </v-card>
        </v-dialog>
      </v-row>
      <v-row class="mt-10">
        <v-col class="pa-0">
          <v-btn
            class="white--text font-weight-black"
            x-large
            block
            color="remove_pink"
            @click="fileSave()"
            >저장하기</v-btn>
        </v-col>
      </v-row>
      <v-row class="mt-10">
        <v-col class="pa-0">
          <v-btn
            class="font-weight-black"
            x-large
            block
            color="sub"
            @click="routeBack()"
            >돌아가기</v-btn>
        </v-col>
      </v-row>
      </v-container>
      <!-- <Snackbar 
      v-if="this.noImg === true"
      /> -->
    </v-container>
  </div>
</template>

<script>
import BackNav from '@/base_components/BackNav.vue';
// import Snackbar from '@/components/Snackbar.vue';

export default {
  components: {
    BackNav,
    // Snackbar
  },
  watch: {

  },
  data: () => {
    return {
      avatarLists: [
        require('@/assets/avatars/p1.svg'),
        require('@/assets/avatars/p2.svg'),
        require('@/assets/avatars/p3.svg'), 
        require('@/assets/avatars/p1.svg'),
        require('@/assets/avatars/p2.svg'),
        require('@/assets/avatars/p3.svg'), 
      ],
      selectedImg: 0,
      value: 0,
      file: null,
      dialog: false,
      notifications: false,
      sound: true,
      widgets: false,
      noImg: true,
    }
  },
  methods: {
    albumFileSave: function () {
      console.log(this.file);
    },
    fileSave: function () {
      let profile = '';
      if (this.file !== null) {
        profile = this.file;
      } else if (this.value !== 0) {
        profile = `@/assets/avatars/${this.value}`;
      }
      console.log(profile);
      /* axios 저장 API

      */
      if (profile.length !== 0) {
        this.$router.go(-1);
      } else {
        const alertMsg = '프로필 이미지가 선택되지 않았습니다.';
        window.alert(alertMsg);
      }
    }, 
    routeBack: function () {
      this.$router.go(-1);
    }
  },
}
</script>

<style scoped>
.v-btn-toggle .v-btn {
  border: none !important;
  background-color: #fff !important;
  height: 75px !important;
}
.v-btn-toggle:active {
  border: 0 !important;
  transform: translateY(4px);
}
</style>