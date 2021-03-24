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
      <v-menu
        bottom
        right
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            text
            v-bind="attrs"
            v-on="on"
          >
            <v-icon>name</v-icon>
          </v-btn>
        </template>

        <v-list>
          <v-list-item
            v-for="(menu, i) in menus"
            :key="i"
          >
            <v-avatar>
              <img
                :src="menu.src"
                :alt="menu.title"
              >
            </v-avatar>
            <span class="mx-2">{{ menu.title }}</span>
          </v-list-item>
        </v-list>
      </v-menu>
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
    menus: [
        { title: 'Click Me 1', src: "https://cdn.vuetifyjs.com/images/john.jpg" },
        { title: 'Click Me 2', src: "https://cdn.vuetifyjs.com/images/john.jpg" },
        { title: 'Click Me 3', src: "https://cdn.vuetifyjs.com/images/john.jpg" },
        { title: 'Click Me 4', src: "https://cdn.vuetifyjs.com/images/john.jpg" },
      ],
  }),
  methods: {
    router: function(n) {
        this.$router.push(this.path[n]);
    }
  }
}
</script>
<style scoped>
.flex-0 {
  display: flex;
}
</style>
