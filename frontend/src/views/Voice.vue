<template>
  <div>
    <BackNav page-title="VoiceRecord" />
    <div class="max-container">
      <v-btn id="record" class="my-2" @click="recordStart(true)" block x-large>
      <span class="text-center my-auto">알람 목소리 녹음하기</span>
        <v-icon v-if="this.flag === false" medium>
          mdi-record-circle
        </v-icon>
        <v-icon v-else medium>
          mdi-pause
        </v-icon>
      </v-btn>
      <v-btn id="stop" class="my-2" @click="recordStart(false)" block x-large>
        <span class="text-center my-auto">멈춤</span>
        <v-icon medium>
          mdi-stop
        </v-icon>
      </v-btn>
      <div id="sound-clips" class="mt-4"></div>
      <v-btn class="my-2 white--text" color="main" @click="sendAudio(audioSrc)" block x-large>
      <span class="text-center my-auto">저장하기</span>
        <v-icon medium>
          mdi-bookmark
        </v-icon>
      </v-btn>
    </div>
  </div>
</template>

<script>
import BackNav from '@/base_components/BackNav.vue';
import axios from 'axios';

export default {
  name: 'voice',
  components: {
    BackNav,
  },
  data: () => {
    return {
      // media_recorder: Object,
      mediaRecord: Object,
      flag: false,
      audioSrc: '',
    }
  }, 
  methods: {
    recordStart(arg) {
      if (navigator.mediaDevices) {

        const constraints = {
          audio: true
        }
        let chunks = []
        navigator.mediaDevices.getUserMedia(constraints)
        .then(stream => {
          const mediaRecorder = new MediaRecorder(stream)

          if (arg === true) {
            this.flag = true
            mediaRecorder.start()
            this.mediaRecord = mediaRecorder
            const record = document.getElementById("record")
            record.classList.add('record')

          } else {
            this.flag = false
            const mediaRecorder = this.mediaRecord
            mediaRecorder.stop()
            const record = document.getElementById("record")
            record.classList.remove('record')

            mediaRecorder.onstop = () => {

              const clipName = prompt("오디오 파일 제목을 입력하세요.", new Date())
              const soundClips = document.getElementById("sound-clips")
              const clipContainer = document.createElement('article')
              const clipLabel = document.createElement('p')
              const audio = document.createElement('audio')
              const deleteButton = document.createElement('button')

              clipContainer.classList.add('clip')
              audio.setAttribute('controls', '')
              deleteButton.innerHTML = "삭제"
              deleteButton.classList.add('delete-btn')
              clipLabel.innerHTML = clipName

              clipContainer.appendChild(audio)
              clipContainer.appendChild(clipLabel)
              clipContainer.appendChild(deleteButton)
              soundClips.appendChild(clipContainer)

              audio.controls = true
              
              const blob = new Blob(chunks, {
                'type': 'audio/ogg codecs=opus'
              })
              
              chunks = []
              const audioURL = URL.createObjectURL(blob)
              audio.src = audioURL
              this.audioSrc = audioURL

              deleteButton.onclick = e => {
                let evtTgt = e.target
                evtTgt.parentNode.parentNode.removeChild(evtTgt.parentNode)
              }
            }
            mediaRecorder.ondataavailable = e => {
              chunks.push(e.data)
            }
          }
        // 
        }).catch(err => {
          console.log('The following error occurred: ' + err)
        })
      } 
    },
    sendAudio:function(audioSrc) {
      axios.post(
          ``,
          {src: audioSrc },
          {
            headers: 
              {
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Headers": "*",
                "Access-Control-Allow-Credentials": true,
                "Content-Type": 'multipart/form-data',
              }
          }
        ).then((res)=>{
          console.log(res)
          console.log('저장완료')
          this.$router.go(-1);
        })
        .catch((err) =>{
          console.log(err)
        })
    }
  },
  mounted: function () {
    // this.start();
  }
}

</script>

<style scoped>
.record {
  background-color: #565CA9 !important;
  color: white !important;
}
</style>