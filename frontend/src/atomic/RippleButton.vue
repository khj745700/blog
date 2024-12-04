
<script setup>
import { ref } from "vue";

const button = ref(null);

const createRipple = (event) => {
  const buttonElement = button.value;

  // 기존 Ripple 제거
  const existingRipple = buttonElement.querySelector(".ripple");
  if (existingRipple) {
    existingRipple.remove();
  }

  const rect = buttonElement.getBoundingClientRect();
  const size = Math.max(rect.width, rect.height);
  const x = event.clientX - rect.left - size / 2.2;
  const y = event.clientY - rect.top - size / 2.2;

  // Ripple 요소 생성
  const ripple = document.createElement("div");
  ripple.style.width = `${size}px`;
  ripple.style.height = `${size}px`;
  ripple.style.left = `${x}px`;
  ripple.style.top = `${y}px`;
  ripple.className = "ripple";

  buttonElement.appendChild(ripple);

  // Ripple 애니메이션이 끝난 뒤 제거
  setTimeout(() => {
    ripple.remove();
  }, 60000);
};
</script>

<template>
  <button class="rippleButton" @click="createRipple" ref="button">
    <slot></slot>
  </button>
</template>


<style scoped>
/* 버튼 기본 스타일 */
.rippleButton {
  position: relative;
  overflow: hidden;
  padding: 10px 20px;
  font-size: 20px;

  background-color: inherit;
  border: 1px solid black;
  width: 100%;

  font-weight: 700;

  border-radius: 4px;
  cursor: pointer;
  outline: none;
  color: white;
  mix-blend-mode: difference;

}



</style>