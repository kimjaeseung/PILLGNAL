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
          <v-tooltip 
          bottom
          >
            <template v-slot:activator="{ on, attrs }">
              <v-avatar
              width="75"
              height="75"
              v-bind="attrs"
              v-on="on"
              >
                <v-img 
                contain
                :src="reward[0]"
                ></v-img>
              </v-avatar>
            </template>
            <span>{{ reward[2] }}</span>
          </v-tooltip>
        </v-card>
      </section>
    </v-row>
  </v-container>
</template>

<script>
export default {
  computed: {
  },
  data: () => {
    return {
      rewardLists: [
        [require('@/assets/rewards/welcome.png'), true, "첫 방문을 환영합니다!"],
        [require('@/assets/rewards/toddler.png'), true, "첫 복약 완료!"],
        [require('@/assets/rewards/kids.png'), false, "3일 연속 성공!"],
        [require('@/assets/rewards/adult.png'), false, "1주일 All Clear"],
        [require('@/assets/rewards/pro.png'), false, "올바른 습관 형성!"],
        [require('@/assets/rewards/champion.png'), false, "건강 Champion!"],
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