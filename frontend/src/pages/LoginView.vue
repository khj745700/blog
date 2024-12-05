<script setup>

import LoginTextBox from "@/components/LoginTextBox.vue";
import PageContainer from "@/atomic/PageContainer.vue";
import RippleButton from "@/atomic/RippleButton.vue";
</script>

<template>
  <PageContainer>
    <div class="container">
      <LoginTextBox label="ID" input-type="text"/>
      <LoginTextBox label="password" input-type="password"/>
      <RippleButton>Login</RippleButton>
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