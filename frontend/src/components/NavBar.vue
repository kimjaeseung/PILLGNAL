<template>
  <div>
    <v-system-bar
      dark
      lights-out
    >
      <v-spacer></v-spacer>
      <v-icon>mdi-wifi-strength-3</v-icon>
      <v-icon>mdi-signal-cellular-outline</v-icon>
      <v-icon>mdi-battery</v-icon>
      <span>12:30</span>
    </v-system-bar>
    <v-toolbar
      elevation="0">
      <!-- 아바타 자리 -->
      <v-avatar>
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

      <v-btn icon>
        <v-icon>mdi-file-document-outline</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-bell-outline</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-cog</v-icon>
      </v-btn>
    </v-toolbar>
  </div>
</template>
<script>
export default {
  data: () => ({
      items: ['Foo', 'Bar', 'Fizz', 'Buzz'],
    }),
}
</script>
