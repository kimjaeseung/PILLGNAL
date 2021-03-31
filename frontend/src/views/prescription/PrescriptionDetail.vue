<template>
  <v-app>
    <v-layout fill-height align-center justify-center>
      <v-card width="100%" max-width="500px">
        <v-list three-line>
          <v-container class="pa-5">
            <v-row align="center">
              <v-col class="ml-2">
                <v-row>
                  <v-img
                    max-width="150px"
                    class="rounded"
                    :aspect-ratio="16 / 9"
                    src="https://dbscthumb-phinf.pstatic.net/3323_000_9/20171126022112845_RQZOH3G3T.jpg/A11A4290B001503.jpg?type=m250&wm=N"
                  ></v-img>
                </v-row>
                <v-row>
                  <v-card
                    width="100%"
                    flat
                    class="rounded-pill text-center mt-1"
                    color="green"
                    style="color: white"
                    >주의
                  </v-card>
                </v-row>
              </v-col>
              <v-col class="ml-2" cols="8">
                <v-container>
                  <v-row>
                    <v-col class="pr-0">
                      <v-row class="font-weight-bold">
                        <div class="ellipsis">광동플레리토로마이신정25</div>
                      </v-row>
                      <v-row class="caption">
                        <div style="color: #9c9c9c">20.03.20 ~ 20.04.20</div>
                        <div class="ml-1 font-weight-medium">| 총 30일</div>
                      </v-row>
                      <v-row class="caption font-weight-medium">
                        1정 | 하루 2번 | 매일
                      </v-row>
                      <v-row class="caption mt-5">
                        <v-card
                          flat
                          class="mr-2 px-2 font-weight-bold"
                          color="#DFDFDF"
                          >아침</v-card
                        >
                        <v-card
                          flat
                          class="mr-2 px-2 font-weight-bold"
                          color="#DFDFDF"
                          >점심</v-card
                        >
                        <v-card
                          flat
                          class="mr-2 px-2 font-weight-bold"
                          color="#DFDFDF"
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
          <v-divider></v-divider>
        </v-list>
      </v-card>
    </v-layout>
  </v-app>
</template>

<script>
export default {
  data() {
    return {};
  },
};
</script>

<style>
.ellipsis {
  width: 100%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
