<template>
  <div>
    <NavBar />
    <v-container>
      <NewsFeedCard />
    </v-container>
  </div>
</template>

<script>
import NavBar from "../components/NavBar.vue";
import NewsFeedCard from "../components/NewsFeedCard.vue";

export default {
  components: {
    NavBar,
    NewsFeedCard,
  }
}
</script>

<style scoped>

</style>