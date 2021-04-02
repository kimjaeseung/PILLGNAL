<template>
  <v-app>
    <v-container>
      <v-row>
        <v-col>
          <div class="web-camera-container">
            <div class="camera-button">
              <v-btn
                type="button"
                class="button is-rounded"
                :class="{ 'is-primary': !isCameraOpen, 'is-danger': isCameraOpen }"
                @click="toggleCamera"
              >
                <span v-if="!isCameraOpen">Open Camera</span>
                <span v-else>Close Camera</span>
              </v-btn>
            </div>

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
                <div class="camera-shutter" :class="{ flash: isShotPhoto }"></div>

                <video v-show="!isPhotoTaken" class="video" ref="camera" autoplay></video>

                <canvas v-show="isPhotoTaken" class="canvas" id="photoTaken" ref="canvas"></canvas>
              </div>
            </v-col>

            <div v-if="isCameraOpen && !isLoading" class="camera-shoot">
              <v-btn class="button" @click="takePhoto" outlined color="main"
                ><v-icon>mdi-camera</v-icon></v-btn
              >
            </div>

            <div v-if="isPhotoTaken && isCameraOpen" class="camera-download">
              <a
                id="downloadPhoto"
                download="my-photo.jpg"
                class="button"
                role="button"
                @click="downloadImage"
              >
                Download
              </a>
            </div>
          </div>
        </v-col></v-row
      >
    </v-container>
  </v-app>
</template>
<script>
export default {
  data() {
    return {
      isCameraOpen: false,
      isPhotoTaken: false,
      isShotPhoto: false,
      isLoading: false,
      link: '#',
    };
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
      // 이미지 찍은거 데이터 URL axios로 보내면 됨!
      console.log(document.getElementById('photoTaken').toDataURL('image/jpeg'));
    },

    downloadImage() {
      const download = document.getElementById('downloadPhoto');
      const canvas = document
        .getElementById('photoTaken')
        .toDataURL('image/jpeg')
        .replace('image/jpeg', 'image/octet-stream');
      download.setAttribute('href', canvas);
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
  padding: 2rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 1px solid #ccc;
  border-radius: 4px;

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
