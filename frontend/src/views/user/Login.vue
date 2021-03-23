<template>
  <v-app>
    <v-layout fill-height align-center>
      <v-container style="max-width: 500px">
        <v-row>
          <v-col class="text-center text-h4"> 로그인 </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-text-field
              v-model="description"
              :rules="rules"
              counter
              maxlength="25"
              hint="This field uses maxlength attribute"
              label="Email"
              outlined
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-text-field
              v-model="description"
              :rules="rules"
              counter
              maxlength="25"
              hint="This field uses maxlength attribute"
              label="Password"
              outlined
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-text-field
              v-model="description"
              :rules="rules"
              counter
              maxlength="25"
              hint="This field uses maxlength attribute"
              label="Password"
              outlined
            ></v-text-field>
          </v-col>
        </v-row>
      </v-container>
    </v-layout>
  </v-app>
</template>

<script>
export default {};
</script>

<style></style>
