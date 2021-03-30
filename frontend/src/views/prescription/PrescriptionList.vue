<template>
  <v-app>
    <v-layout fill-height align-center>
      <v-container>
        <v-row v-for="index in 1" :key="index">
          <v-col>
            <prescription-item></prescription-item>
          </v-col>
        </v-row>
      </v-container>
    </v-layout>
  </v-app>
</template>

<script>
import PrescriptionItem from '../../components/prescription/PrescriptionItem.vue';
export default {
  components: { PrescriptionItem },
  data() {
    return {
      list: [{}],
    };
  },
};
</script>

<style></style>

<style scoped>
.container {
  max-width: 500px;
  width: 80%;
}
</style>
