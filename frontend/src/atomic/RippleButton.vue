
<script setup>
import { ref } from "vue";

const button = ref(null);

const createRipple = (event) => {
  const buttonElement = button.value;

  const rippleExists = buttonElement.querySelector(".ripple");
  if(rippleExists) {
    rippleExists.remove();
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
  }, 600);

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

  border: 1px solid black;
  width: 100%;

  font-weight: 700;

  border-radius: 4px;
  cursor: pointer;
  outline: none;
  color: white;
  mix-blend-mode: difference;

  background-image: linear-gradient(45deg, #000, #fff,  #fff, #000);
  background-size: 400% 400%;
  animation: backgroundColorChange 4s ease infinite;
  transition: background-image 1s ease;

  &:hover {
    background: none;
    animation: none;
  }
}

@keyframes backgroundColorChange {
  0% {
    background-position: 0 0;
  }
  90% {
    background-position: 100% 0;
  }
  100% {
    background-position: 0 0;
  }

}

</style>