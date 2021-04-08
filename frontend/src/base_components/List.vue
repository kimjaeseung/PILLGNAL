<template>
  <v-list>
    <v-list-item
    v-for="(item, index) in notifyItems"
    :key="index"
    >
      <v-list-item-avatar>
        <v-img :src="item.avatar"></v-img>
      </v-list-item-avatar>
      <v-list-item-content>
        <v-list-item-title v-html="item.title"></v-list-item-title>
        <!-- <v-list-item-subtitle v-html="item.subtitle"></v-list-item-subtitle> -->
      </v-list-item-content>
    </v-list-item>
  </v-list>
</template>

<script>
export default {
  name: 'List',
  props: {
    notifyItems: Array,
  }
}
</script>

<style>

</style>