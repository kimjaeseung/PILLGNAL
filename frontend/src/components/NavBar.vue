<template>
  <div>
    <v-toolbar
      class="mt-1"
      elevation="0">
      <!-- 아바타 자리 -->
      <v-avatar
      @click="router(0)">
        <img
          src="https://cdn.vuetifyjs.com/images/john.jpg"
          alt="John"
        >
      </v-avatar>

      <!-- 사용자 이름 자리 -->
      <v-btn text>
        name
      </v-btn>
      <v-spacer></v-spacer>

      <v-btn 
      icon
      @click="router(1)">
        <v-icon>mdi-file-document-outline</v-icon>
      </v-btn>

      <v-btn 
      icon
      @click="router(2)">
        <v-icon>mdi-bell-outline</v-icon>
      </v-btn>

      <v-btn 
      icon
      @click="router(3)">
        <v-icon>mdi-cog</v-icon>
      </v-btn>
    </v-toolbar>
  </div>
</template>
<script>
export default {
  data: () => ({
    items: ['Foo', 'Bar', 'Fizz', 'Buzz'],
    path: ['mypage', 'prescription', 'notification', 'settings'],
  }),
  methods: {
    router: function(n) {
        this.$router.push(this.path[n]);
    }
  }
}
</script>
