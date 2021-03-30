<template>
  <v-container>
    <h3 class="my-5">Reward</h3>
    <v-row>
      <section
      v-for="(reward, idx) in rewardLists"
      :key="idx"
      class="col-4 text-center"
      >
        <v-card
        v-if="reward[1] === false"
        class="elevation-0"
        >
          <v-avatar
          width="75"
          height="75"
          >
            <v-img 
            cover
            class="overlay"
            :src="reward[0]"
            ></v-img>
          </v-avatar>
        </v-card>
        <v-card
        v-else
        class="elevation-0"
        >
          <v-avatar
          width="75"
          height="75"
          >
            <v-img 
            contain
            
            :src="reward[0]"
            ></v-img>
          </v-avatar>
        </v-card>
      </section>
    </v-row>
  </v-container>
</template>

<script>
export default {
  computed: {
    myBestReward: () => {
      const Lists = this.rewardLists;
      console.log(Lists);
      let best = '';
      for (let index = 0; index < Lists.length; index++) {
        const elem = Lists[index];
        if (elem[1] === true) {
          best = index;
        } else {
          break
        }
      }
      return best
    }
  },
  data: () => {
    return {
      rewardLists: [
        [require('@/assets/rewards/welcome.png'), true],
        [require('@/assets/rewards/toddler.png'), true],
        [require('@/assets/rewards/kids.png'), false],
        [require('@/assets/rewards/adult.png'), false],
        [require('@/assets/rewards/pro.png'), false],
        [require('@/assets/rewards/champion.png'), false],
      ]
    }
  },
  mounted: function () {
    // this.myBestReward();
  }
}
</script>

<style scoped>
.overlay {
  filter: brightness(30%);
}

</style>