<template>
  <div class="max-container">
    <BackNav page-title="ProfileImage" />
    <notifications />
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
                @click="[fileSave(), dialog = false]"
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
    </v-container>
  </div>
</template>

<script>
import axios from 'axios';
import BackNav from '@/base_components/BackNav.vue';
// import Snackbar from '@/components/Snackbar.vue';
const API_BASE_URL = 'http://localhost:8080/';
const email = 'test@gmail.com'

const dataURLtoFile = (dataurl, fileName) => {
  var arr = dataurl.split(','),
    mime = arr[0].match(/:(.*?);/)[1],
    bstr = atob(arr[1]), 
    n = bstr.length, 
    u8arr = new Uint8Array(n);
      
  while(n--){
    u8arr[n] = bstr.charCodeAt(n);
  }
  return new File([u8arr], fileName, {type:mime});
}

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
        require('@/assets/avatars/woman2.png'),
        require('@/assets/avatars/woman.png'),
        require('@/assets/avatars/profilelady.png'), 
        require('@/assets/avatars/profileman.png'),
        require('@/assets/avatars/hacker.png'),
        require('@/assets/avatars/man.png'), 
        // man
      ],
      selectedImg: 0,
      value: '',
      file: null,
      dialog: false,
      notifications: false,
      sound: true,
      widgets: false,
      noImg: true,
    }
  },
  methods: {
    fileSave: function () {
      let profile = '';
      var formData = new FormData();
      if (this.file !== null) {
        profile = this.file;
        formData.append('file', profile)
      } else if (this.value !== 0) {
        profile = this.avatarLists[this.value];
        // console.log('here', this.value, profile)
        var tmp_profile = dataURLtoFile(profile,'img.png');
        // var formData = new FormData();
        profile = tmp_profile;
        formData.append('file', profile)
      }
      // console.log(profile);
      // console.log('formData: ', formData.getAll('file'));
      /* axios 저장 API */
      const instance = axios.create({
        baseURL: API_BASE_URL,
        headers: {
          'Access-Control-Allow-Origin': '',
          'Access-Control-Allow-Headers': '',
          'Access-Control-Allow-Credentials': true,
          'Content-Type': 'multipart/form-data',
        }
      })
      if (profile.length !== 0) {
        instance.post(
          `user/profile?email=${email}`, formData
        ).then((res)=>{
          // console.log(res)
          // console.log(res.data)
          // console.log(res.data.data)
          this.$store.commit('SET_USER_PROFILE', res.data.data)
          // VUEX로 SET_USER의 imageUrl값 변경시키기
          this.$notify({ type: 'success', text: '프로필 이미지 변경'});
          this.$router.go(-1);
        })
        .catch((err) =>{
          console.log(err)
          this.$notify({ type: 'warn', text: '프로필 이미지 변경 실패'});
        })
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