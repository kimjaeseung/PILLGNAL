<template>
  <div class="max-container">
    <BackNav page-title="Notification"/>
    <List :notify-items="items" />
  </div>
</template>

<script>
import List from '@/base_components/List.vue';
import BackNav from '@/base_components/BackNav.vue';

export default {
  components: {
    List,
    BackNav
  },
  data: () => {
    return {
      items: [
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
          title: 'OOO님이 점심 약을 복용하셨습니다.',
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
          title: 'OOO님이 점심 약을 건너뛰기하셨습니다.',
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
          title: 'OOO님이 점심 약을 복용하셨습니다.',
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
          title: 'OOO님이 점심 약을 복용하셨습니다.',
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/5.jpg',
          title: 'OOO님이 점심 약을 복용하셨습니다.',
        },
      ]
    }
  },
}
</script>

<style>

</style>