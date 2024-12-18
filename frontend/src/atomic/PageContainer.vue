<script setup>

</script>

<template>
  <div class="pageContainer">
    <slot />
  </div>
</template>

<style scoped>
.pageContainer {
  max-width: 54rem;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  box-sizing: border-box;
  margin: 0 auto;
  padding: 0 10px;
}
</style>