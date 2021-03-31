<template>
  <div>
    <v-card>
      <v-card-title class="font-weight-bold">
        환절기 피부질환 외1<v-spacer></v-spacer
        ><v-icon @click="expand = !expand">mdi-chevron-down</v-icon>
      </v-card-title>

      <v-card-subtitle class="pb-0">2020.05.30</v-card-subtitle>
      <v-card-actions>
        <v-row class="ma-0">
          <v-col class="d-flex align-center" cols="9">
            <v-progress-linear value="50" color="main"></v-progress-linear>
          </v-col>
          <v-col class="pa-0 d-flex">
            <b class="ml-1 day">25일/</b>
            <b>40일</b>
          </v-col>
        </v-row>
      </v-card-actions>
    </v-card>
    <v-expand-transition>
      <v-layout class="mt-2" v-show="expand">
        <v-card color="main" width="49%" class="ma-auto pa-0">
          <v-container>
            <v-row
              ><v-col class="pa-1 d-flex justify-center">
                <v-icon color="white" x-large>mdi-file-document-edit</v-icon>
              </v-col>
            </v-row>
            <v-row class="pa-0"
              ><v-col class="pa-0 text-center font-weight-bold white--text"
                >수정</v-col
              ></v-row
            >
          </v-container>
        </v-card>
        <v-card color="remove_pink" width="49%" class="ma-auto pa-0">
          <v-container>
            <v-row
              ><v-col class="pa-1 d-flex justify-center">
                <v-icon color="white" x-large>mdi-trash-can</v-icon>
              </v-col>
            </v-row>
            <v-row class="pa-0"
              ><v-col class="pa-0 text-center font-weight-bold white--text"
                >삭제</v-col
              ></v-row
            >
          </v-container>
        </v-card>
      </v-layout>
    </v-expand-transition>
  </div>
</template>

<script>
export default {
  props: {},
  data() {
    return {
      expand: false,
    };
  },
};
</script>

<style scoped>
.day {
  color: #5b67ff;
}
</style>
