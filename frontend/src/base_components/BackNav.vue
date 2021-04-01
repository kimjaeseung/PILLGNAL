<template>
  <div>
    <v-toolbar
      class="mt-1"
      elevation="0">
      <!-- 아바타 자리 -->
      <v-btn 
      icon
      @click="routeBack()">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <v-spacer></v-spacer>      
        <h3> {{ pageTitle }} </h3>
      <v-spacer></v-spacer>      
    </v-toolbar>
  </div>
</template>

<script>
export default {
  props: {
    pageTitle: String,
  },
  methods: {
    routeBack: function () {
      this.$router.go(-1);
    }
  },
   data: () => {
     return {
     }
   }
}
</script>

<style>

</style>