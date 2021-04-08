<template>
  <div>
    <v-container class="max-container">
      <BackNav page-title="목소리 녹음🥳" />
      <v-card class="text-center mt-10">
        <v-avatar class="mt-7"><img src="@/assets/voice.svg" /></v-avatar>
        <v-card-subtitle
          >멋진 목소리를 녹음해 <br />
          <strong>알림으로 사용</strong>하세요!</v-card-subtitle
        >
      </v-card>
      <v-col class="mt-12">
        <v-btn id="record" class="my-2" @click="recordStart(true)" block x-large>
          <span class="text-center my-auto">알람 목소리 녹음하기</span>
          <v-icon v-if="this.flag === false" medium> mdi-record-circle </v-icon>
          <v-icon v-else medium> mdi-pause </v-icon>
        </v-btn>
        <v-btn id="stop" class="my-2" @click="recordStart(false)" block x-large>
          <span class="text-center my-auto">멈춤</span>
          <v-icon medium> mdi-stop </v-icon>
        </v-btn>
        <div id="sound-clips" class="mt-4"></div>
        <v-btn class="my-2 white--text" color="main" @click="sendAudio(voiceFile)" block x-large>
          <span class="text-center my-auto">저장하기</span>
          <v-icon medium> mdi-bookmark </v-icon>
        </v-btn>
      </v-col>
    </v-container>
  </div>
</template>

<script>
import BackNav from '@/base_components/BackNav.vue';
import firebase from 'firebase';

const blobToFile = function blobToFile(theBlob, fileName) {
  //A Blob() is almost a File() - it's just missing the two properties below which we will add
  theBlob.lastModifiedDate = new Date();
  theBlob.name = fileName;
  return theBlob;
};

const firebaseConfig = {
  apiKey: 'AIzaSyDgivPuUhGVB6JnyCBc_HaChKkD0X3-nog',
  authDomain: 'pillgnal.firebaseapp.com',
  projectId: 'pillgnal',
  storageBucket: 'pillgnal.appspot.com',
  messagingSenderId: '179166848786',
  appId: '1:179166848786:web:221fb20c7efd38c65b35cb',
  measurementId: 'G-1NBGW7E5W6',
};

firebase.initializeApp(firebaseConfig);
firebase.analytics();

export default {
  name: 'voice',
  components: {
    BackNav,
  },

  methods: {
    recordStart(arg) {
      if (navigator.mediaDevices) {
        const constraints = {
          audio: true,
        };
        let chunks = [];
        navigator.mediaDevices
          .getUserMedia(constraints)
          .then((stream) => {
            const mediaRecorder = new MediaRecorder(stream);

            if (arg === true) {
              this.flag = true;
              mediaRecorder.start();
              this.mediaRecord = mediaRecorder;
              const record = document.getElementById('record');
              record.classList.add('record');
            } else {
              this.flag = false;
              const mediaRecorder = this.mediaRecord;
              mediaRecorder.stop();
              const record = document.getElementById('record');
              record.classList.remove('record');

              mediaRecorder.onstop = () => {
                const clipName = prompt('오디오 파일 제목을 입력하세요.', new Date());
                const soundClips = document.getElementById('sound-clips');
                const clipContainer = document.createElement('article');
                const clipLabel = document.createElement('p');
                const audio = document.createElement('audio');
                const deleteButton = document.createElement('button');

                clipContainer.classList.add('clip');
                audio.setAttribute('controls', '');
                deleteButton.innerHTML = '삭제';
                deleteButton.classList.add('delete-btn');
                clipLabel.innerHTML = clipName;

                clipContainer.appendChild(audio);
                clipContainer.appendChild(clipLabel);
                clipContainer.appendChild(deleteButton);
                soundClips.appendChild(clipContainer);

                audio.controls = true;

                const blob = new Blob(chunks, {
                  type: 'audio/ogg codecs=opus',
                });

                chunks = [];
                const audioURL = URL.createObjectURL(blob);
                audio.src = audioURL;
                this.audioSrc = audioURL;

                const voiceFile = blobToFile(blob, 'voice.mp3');
                this.voiceFile = voiceFile;
                console.log('변환: ', voiceFile);

                deleteButton.onclick = (e) => {
                  let evtTgt = e.target;
                  evtTgt.parentNode.parentNode.removeChild(evtTgt.parentNode);
                };
              };
              mediaRecorder.ondataavailable = (e) => {
                chunks.push(e.data);
              };
            }
            //
          })
          .catch((err) => {
            console.log('The following error occurred: ' + err);
          });
      }
    },
    sendAudio: function (voiceFile) {
      // console.log('목소리 저장하기', voiceFile)
      const email = 'test@gmail.com';
      // console.log('email: ', email)
      const storageRef = firebase.storage().ref(`${email}_Voices`);
      const task = storageRef.put(voiceFile);
      task.on('state_changed', (s) => {
        console.log((s.bytesTransferred / s.totalBytes) * 100);
      });
      this.getAudio();
    },
    getAudio: function () {
      const email = 'test@gmail.com';
      const getAudioURL = `https://firebasestorage.googleapis.com/v0/b/pillgnal.appspot.com/o/${email}_Voices?alt=media`;
      this.voice = getAudioURL;
      this.$store.dispatch('SAVE_VOICE', getAudioURL);
    },
    getUser: function () {
      this.user = this.$store.getters.user;
    },
  },
  data: function () {
    return {
      // media_recorder: Object,
      mediaRecord: Object,
      flag: false,
      audioSrc: '',
      voiceFile: Object,
      user: Object,
      voice: '',
    };
  },
  created: function () {
    this.getUser();
  },
};
</script>

<style scoped>
.record {
  background-color: #565ca9 !important;
  color: white !important;
}
</style>
