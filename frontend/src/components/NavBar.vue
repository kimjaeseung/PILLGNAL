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

      <!-- 유저 변경 -->
      <v-menu
        :close-on-content-click="true" 
        :nudge-width="200"
        offset-x
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            text
            v-bind="attrs"
            v-on="on"
          >
            Name
          </v-btn>
        </template>

        <v-card>
          <v-list>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>John Leider</v-list-item-title>
                <v-list-item-subtitle>Founder of Vuetify</v-list-item-subtitle>
              </v-list-item-content>

            </v-list-item>
          </v-list>
          <v-divider></v-divider>
        <!-- Family member loop -->
          <v-list>
            <v-list-item>
              <v-list-item-action>
                <v-list-item-avatar>
                  <img
                    src="https://cdn.vuetifyjs.com/images/john.jpg"
                    alt="John"
                  >
                </v-list-item-avatar>
              </v-list-item-action>
              <v-list-item-title>Name</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-card>
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
