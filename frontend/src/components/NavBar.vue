<template>
  <div class="max-container">
    <v-toolbar
      class="mt-1"
      elevation="0">
      <!-- 아바타 자리 -->
      <v-btn icon>
        <v-avatar
        @click="router(0)">
          <img
            src="https://cdn.vuetifyjs.com/images/john.jpg"
            alt="John"
          >
        </v-avatar>
      </v-btn>
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
        <!-- Family member loop -->
          <v-list>
            <v-list-item 
            v-for="(member, idx) in familyMembers"
            :key="idx"
            >
              <v-list-item-action>
                <v-list-item-avatar>
                  <img
                    :src="member.src"
                    :alt="member.name"
                  >
                </v-list-item-avatar>
              </v-list-item-action>
              <v-list-item-title>{{ member.name }}</v-list-item-title>
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
    familyMembers: [
      {
        src: 'https://cdn.vuetifyjs.com/images/john.jpg',
        name: 'John Doe',
        email: 'john.doe@doe.com',
      },
      {
        src: 'https://cdn.vuetifyjs.com/images/john.jpg',
        name: 'John Doe2',
        email: 'john2.doe@doe.com',
      },
    ]
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

.v-toolbar__content > .v-btn.v-btn--icon:first-child {
  margin-left: 0 !important;
}
</style>