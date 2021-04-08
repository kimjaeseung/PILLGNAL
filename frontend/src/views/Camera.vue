<template>
  <v-app>
    <v-container>
      <v-row>
        <v-col>
          <div class="web-camera-container">
            <!-- <div class="camera-button">
              <v-btn
                type="button"
                class="button is-rounded"
                :class="{ 'is-primary': !isCameraOpen, 'is-danger': isCameraOpen }"
                @click="toggleCamera"
              >
                <span v-if="!isCameraOpen">Open Camera</span>
                <span v-else>Close Camera</span>
              </v-btn>
            </div> -->

            <div v-show="isCameraOpen && isLoading" class="camera-loading">
              <ul class="loader-circle">
                <li></li>
                <li></li>
                <li></li>
              </ul>
            </div>

            <v-col>
              <div
                v-if="isCameraOpen"
                v-show="!isLoading"
                class="camera-box"
                :class="{ flash: isShotPhoto }"
              >
                <v-col class="mb-2">
                  <v-card flat class="text-caption text-center card-padding textarea">
                    <v-avatar tile class="mt-5"><img src="@/assets/prescription.svg" /></v-avatar>
                    <v-card-subtitle>
                      <strong>{{ cameraMode }}</strong
                      >사진을 찍어주세요.
                    </v-card-subtitle>
                  </v-card>
                </v-col>
                <div class="camera-shutter" :class="{ flash: isShotPhoto }"></div>

                <video v-show="!isPhotoTaken" class="video" ref="camera" autoplay></video>

                <canvas v-show="isPhotoTaken" class="canvas" id="photoTaken" ref="canvas"></canvas>
              </div>
            </v-col>

            <div v-if="isCameraOpen && !isLoading" class="camera-shoot">
              <v-btn class="button" @click="takePhoto" outlined color="main"
                ><v-icon v-if="!isPhotoTaken">mdi-camera</v-icon>
                <v-icon v-if="isPhotoTaken">mdi-backup-restore</v-icon>
              </v-btn>
            </div>
          </div>
          <div v-if="isPhotoTaken && isCameraOpen" class="camera-download">
            <v-btn @click="imgSave()" color="main" elevation="0" block class="white--text"
              >저장하기</v-btn
            >
          </div>
        </v-col></v-row
      >
    </v-container>
  </v-app>
</template>

<script>
import axios from 'axios';
import { API_BASE_URL } from '@/config';
export default {
  data: () => ({
    isCameraOpen: true,
    isPhotoTaken: false,
    isShotPhoto: false,
    isLoading: true,
    link: '#',
    data: '',
  }),
  props: ['cameraMode'],
  components: {},
  created() {
    this.createCameraElement();
    console.log(this.cameraMode);
  },
  methods: {
    toggleCamera() {
      if (this.isCameraOpen) {
        this.isCameraOpen = false;
        this.isPhotoTaken = false;
        this.isShotPhoto = false;
        this.stopCameraStream();
      } else {
        this.isCameraOpen = true;
        this.createCameraElement();
      }
    },

    createCameraElement() {
      this.isLoading = true;

      const constraints = (window.constraints = {
        audio: false,
        video: true,
      });

      navigator.mediaDevices
        .getUserMedia(constraints)
        .then((stream) => {
          this.isLoading = false;
          this.$refs.camera.srcObject = stream;
        })
        .catch((error) => {
          this.isLoading = false;
          console.log(error);
          alert("May the browser didn't support or there is some errors.");
        });
    },

    stopCameraStream() {
      let tracks = this.$refs.camera.srcObject.getTracks();

      tracks.forEach((track) => {
        track.stop();
      });
    },

    takePhoto() {
      if (!this.isPhotoTaken) {
        this.isShotPhoto = true;

        const FLASH_TIMEOUT = 50;

        setTimeout(() => {
          this.isShotPhoto = false;
        }, FLASH_TIMEOUT);
      }

      this.isPhotoTaken = !this.isPhotoTaken;

      var canvas = document.querySelector('.canvas');
      var video = document.querySelector('.video');
      canvas.width = video.videoWidth;
      canvas.height = video.videoHeight;
      const context = canvas.getContext('2d');
      context.drawImage(video, 0, 0, video.videoWidth, video.videoHeight);
      var datas = document.getElementById('photoTaken').toDataURL('image/jpeg');

      //Usage example:
      var file = this.dataURLtoFile(datas, 'temp.jpg');
      console.log('파일원본', file);

      var formData = new FormData();
      formData.append('file', file);

      console.log(formData);
      const instance = axios.create({
        baseURL: API_BASE_URL,
        headers: {
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Headers': '*',
          'Access-Control-Allow-Credentials': true,
          'Content-Type': 'multipart/form-data',
        },
      });

      if (this.cameraMode == '처방전') {
        instance
          .post('api/v1/prescription', formData)
          .then((res) => {
            console.log(res);
            this.data = res;
          })
          .catch((err) => {
            console.log('실패', err);
          });
      }
      if (this.cameraMode == '알약') {
        instance
          .post('api/v1/pill', formData)
          .then((res) => {
            console.log(res);
            this.data = res;
          })
          .catch((err) => {
            console.log('실패', err);
          });
      }
      if (this.cameraMode == undefined) {
        alert('카메라 모드를 선택해주세요.');
        this.$router.push('/home');
      }
    },

    imgSave() {
      this.$router.push({ name: 'PillMethod', params: { prescription: this.data } });
    },
    dataURLtoFile(dataurl, fileName) {
      var arr = dataurl.split(','),
        mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]),
        n = bstr.length,
        u8arr = new Uint8Array(n);

      while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
      }

      return new File([u8arr], fileName, { type: mime });
    },
  },
};
</script>

<style lang="scss" scoped>
body {
  display: flex;
  justify-content: center;
}

.video,
.canvas {
  width: 100%;
  height: auto;
}

.web-camera-container {
  margin-top: 2rem;
  margin-bottom: 2rem;
  padding-bottom: 1rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 1px solid #ccc;
  border-radius: 4px;
  height: auto;

  .camera-button {
    margin-bottom: 2rem;
  }

  .camera-box {
    .camera-shutter {
      opacity: 0;
      background-color: #fff;

      &.flash {
        opacity: 1;
      }
    }
  }

  .camera-shoot {
    margin: 1rem 0;

    button {
      height: 60px;
      width: 60px;
      display: flex;
      align-items: center;
      justify-content: center;
      border-radius: 100%;

      img {
        height: 35px;
        object-fit: cover;
      }
    }
  }

  .camera-loading {
    overflow: hidden;
    height: 100%;
    position: absolute;
    width: 100%;
    min-height: 150px;
    margin: 3rem 0 0 -1.2rem;

    ul {
      height: 100%;
      position: absolute;
      width: 100%;
      z-index: 999999;
      margin: 0;
    }

    .loader-circle {
      display: block;
      height: 14px;
      margin: 0 auto;
      top: 50%;
      left: 100%;
      transform: translateY(-50%);
      transform: translateX(-50%);
      position: absolute;
      width: 100%;
      padding: 0;

      li {
        display: block;
        float: left;
        width: 10px;
        height: 10px;
        line-height: 10px;
        padding: 0;
        position: relative;
        margin: 0 0 0 4px;
        background: #999;
        animation: preload 1s infinite;
        top: -50%;
        border-radius: 100%;

        &:nth-child(2) {
          animation-delay: 0.2s;
        }

        &:nth-child(3) {
          animation-delay: 0.4s;
        }
      }
    }
  }

  @keyframes preload {
    0% {
      opacity: 1;
    }
    50% {
      opacity: 0.4;
    }
    100% {
      opacity: 1;
    }
  }
}
</style>
