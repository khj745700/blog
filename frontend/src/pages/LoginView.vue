<script setup>

import LoginTextBox from "@/components/LoginTextBox.vue";
import PageContainer from "@/atomic/PageContainer.vue";
import RippleButton from "@/atomic/RippleButton.vue";
import loginRequest from "@/api/user/Login.js";
import {ref} from "vue";

const loginClick = () => {
  loginRequest(username.value, password.value, () => {}, () => {});
}

const username = ref('');
const password = ref('');

const usernameHandler = (event) => {
  username.value = event.target.value;
  console.log(username.value);
}

const passwordHandler = (event) => {
  password.value = event.target.value;
}

</script>

<template>
  <PageContainer>
    <div class="container">
      <LoginTextBox label="ID" input-type="text" :value="username" :value-handler="usernameHandler" />
      <LoginTextBox label="password" input-type="password" :value="username" :value-handler="passwordHandler"/>
      <RippleButton @click="loginClick">Login</RippleButton>
    </div>
  </PageContainer>
</template>

<style scoped>
  .container {
    height: 100vh;
    display: flex;
    flex-direction: column;
    gap: 10px;
    align-items: center;
    justify-content: center;
  }
</style>