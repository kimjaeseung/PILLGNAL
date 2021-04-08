<template>
  <v-app>
    <v-layout fill-height align-center justify-center>
      <v-card
        class="d-flex justify-center"
        width="50%"
        min-width="350"
        max-width="500"
      >
        <transition name="fade" mode="out-in">
          <router-view></router-view>
        </transition>
      </v-card>
    </v-layout>
  </v-app>
</template>

<script>
export default {
  components: {},
};
</script>

<style scoped>
.v-application {
  background-color: #565ca9;
}
.container {
  max-width: 450px;
  width: 70%;
}
.fade-enter-active,
.fade-leave-active {
  transition-property: opacity;
  transition-duration: 0.25s;
}

.fade-enter-active {
  transition-delay: 0.25s;
}

.fade-enter,
.fade-leave-active {
  opacity: 0;
}
</style>
