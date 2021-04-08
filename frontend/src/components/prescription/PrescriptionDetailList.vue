<template>
  <v-list-item three-line>
    <v-container class="pa-0">
      <v-row align="center">
        <v-col class="ml-2">
          <v-row>
            <v-img
              max-width="150px"
              class="rounded"
              :aspect-ratio="16 / 9"
              :src="info.pillUrl"
            ></v-img>
          </v-row>
          <v-row>
            <v-card
              width="100%"
              flat
              class="rounded-pill text-center mt-1 white--text"
              color="green"
              >처방약
            </v-card>
          </v-row>
        </v-col>
        <v-col class="ml-2" cols="8">
          <v-container>
            <v-row>
              <v-col class="pr-0">
                <v-row class="font-weight-bold">
                  <div class="ellipsis">{{ info.name }}</div>
                </v-row>
                <v-row class="caption">
                  <div class="date">
                    {{ dateFormChange(info.startday) }} ~
                    {{ dateFormChange(info.endday) }}
                  </div>
                  <div class="ml-1 font-weight-medium">| 총 30일</div>
                </v-row>
                <v-row class="caption font-weight-medium">
                  {{ info.count }}정 | 하루 {{ info.daycount }}번 | 매일
                </v-row>
                <v-row class="caption mt-5">
                  <v-card
                    flat
                    class="mr-2 px-2 font-weight-bold"
                    color="medicine_cycle_grey"
                    v-if="info.morning"
                    >아침</v-card
                  >
                  <v-card
                    flat
                    class="mr-2 px-2 font-weight-bold"
                    color="medicine_cycle_grey"
                    v-if="info.afternoon"
                    >점심</v-card
                  >
                  <v-card
                    flat
                    class="mr-2 px-2 font-weight-bold"
                    color="medicine_cycle_grey"
                    v-if="info.night"
                    >저녁</v-card
                  >
                </v-row>
              </v-col>
              <v-col cols="1" class="d-flex align-center">
                <v-icon>mdi-chevron-right</v-icon>
              </v-col>
            </v-row>
          </v-container>
        </v-col>
      </v-row>
    </v-container>
  </v-list-item>
</template>

<script>
export default {
  props: { info: Object },
  methods: {
    dateFormChange(str) {
      let yy = str.substr(2, 2);
      let mm = str.substr(5, 2);
      let dd = str.substr(8, 2);
      return yy + '.' + mm + '.' + dd;
    },
  },
};
</script>

<style></style>
