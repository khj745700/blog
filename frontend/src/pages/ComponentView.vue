<script setup>
import Label from "@/atomic/Label.vue";
import TextBox from "@/atomic/InputBox.vue";
</script>

<template>
  <div class="component-container">
    <Label :content="`Label`" :is-dark="false" font-weight="600" ></Label>
    <Label :content="`Label`" is-dark font-size="2" font-weight="400" ></Label>
  </div>
  <div class="component-container">
    <TextBox input-type="text" active-border-color="green"></TextBox>
    <TextBox input-type="text" active-border-color="green" is-dark></TextBox>
  </div>

</template>

<style scoped>
.component-container {
  display: flex;
  gap: 10px;
  background-color: gray;
  width: 100%;
  height: 100%;
  margin-bottom: 10px;
  padding: 10px 10px;
}


</style>