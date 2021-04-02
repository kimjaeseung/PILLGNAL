<template>
  <div>
    <!-- 완료 : 회색 disabled -->
    <v-card
      class="mx-auto"
      max-width="400"
      v-if="pillData.isDone === 'done'"
      color="#dfdfdf"
      disabled
    >
      <v-card-title class="d-flex flex-row justify-space-between px-5">
        <v-icon
          left
        >
          mdi-alarm-check
        </v-icon>
          <span> {{ pillData.date }} 알림 </span>
        <v-btn
          icon
          @click="show = !show"
        >
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
        </v-card-title>

      <v-card-actions>
        <v-list-item class="grow">
          <BtnSquare 
            btn-color="primary" 
            btn-text="복용"
          />
          <v-spacer></v-spacer>
          <BtnSquare 
            btn-color="sub" 
            btn-text="건너뛰기"
          />
          <v-spacer></v-spacer>
          <BtnSquare 
            btn-color="test"
            btn-text="30분 뒤 알림"
            class="text-white"
          />
        </v-list-item>
      </v-card-actions>
    </v-card>
    <!-- 대기 : 기본 -->
    <v-card
      class="mx-auto"
      max-width="400"
      v-else-if="pillData.isDone === 'waiting'"
    >
      <v-card-title class="d-flex flex-row justify-space-between px-5">
        <v-icon
          left
        >
          mdi-alarm-check
        </v-icon>
          <span> {{ pillData.date }} 알림 </span>
        <v-btn
          icon
          @click="show = !show"
        >
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
        </v-card-title>

        <v-expand-transition>
        <div v-show="show">
          <v-card-text>
            <v-card-actions 
            v-for="(item, idx) in pillData.pills"
            :key="idx"
            >
              <v-list-item-avatar 
              rounded
              width="65px"
              >
                <v-img
                  class="elevation-6 avatar-pill"
                  :alt="item.pname"
                  :src="item.img"
                >
                </v-img>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title>{{ item.pname }}</v-list-item-title>
                <!-- <v-list-item-subheader>{{ item.cnt }}정</v-list-item-subheader> -->
                <v-list-item-subtitle>{{ item.cnt }}정</v-list-item-subtitle>
              </v-list-item-content>
            </v-card-actions>
          </v-card-text>
        </div>
      </v-expand-transition>
      
      <v-card-actions>
        <v-list-item class="grow">
          <BtnSquare 
            btn-color="primary" 
            btn-text="복용"
          />
          <v-spacer></v-spacer>
          <BtnSquare 
            btn-color="sub" 
            btn-text="건너뛰기"
          />
          <v-spacer></v-spacer>
          <BtnSquare 
            btn-color="test"
            btn-text="30분 뒤 알림"
            class="text-white"
          />
        </v-list-item>
      </v-card-actions>
    </v-card>
    <!-- Skip : 붉은색 -->
    <v-card
      class="mx-auto"
      max-width="400"
      v-else-if="pillData.isDone === 'skip'"
      color="remove_pink"
      disabled
    >
      <v-card-title class="d-flex flex-row justify-space-between px-5">
        <v-icon
          left
        >
          mdi-alarm-check
        </v-icon>
          <span> {{ pillData.date }} 알림 </span>
        <v-btn
          icon
          @click="show = !show"
        >
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
        </v-card-title>

      
      <v-card-actions>
        <v-list-item class="grow">
          <BtnSquare 
            btn-color="primary" 
            btn-text="복용"
          />
          <v-spacer></v-spacer>
          <BtnSquare 
            btn-color="sub" 
            btn-text="건너뛰기"
          />
          <v-spacer></v-spacer>
          <BtnSquare 
            btn-color="test"
            btn-text="30분 뒤 알림"
            class="text-white"
          />
        </v-list-item>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import BtnSquare from "@/base_components/BtnSquare.vue";

export default {
  name: 'NewsFeedCard',
  components: {
    BtnSquare
  },
  props: {
    pillData: Object,
  },
  data: () => ({
    show: false,
    
  }),
}
</script>

<style scoped>
.avatar-pill {
  object-fit: cover;
}
.v-input--selection-controls {
  margin-top: 0 !important;
}
.v-messages {
  min-height: 0 !important;
}
</style>