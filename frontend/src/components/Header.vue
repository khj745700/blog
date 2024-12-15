<script setup>
import { ref, onMounted, onUnmounted } from 'vue';

const isMenuOpen = ref(false);
const showHeader = ref(true);
let lastScrollPosition = 0;

const handleScroll = () => {
  const currentScrollPosition = window.scrollY;

  if (currentScrollPosition > lastScrollPosition && currentScrollPosition > 100) {
    
    showHeader.value = false;
  } else {
    showHeader.value = true;
  }
  lastScrollPosition = currentScrollPosition;
};

onMounted(() => {
  window.addEventListener('scroll', handleScroll);
});

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll);
});
</script>

<template>
  <div class="header" :class="{'hidden': !showHeader}">
    <div class="header__inner">
      <div class="logoWrap">
      <div class="header__logo">Kim Hyunjin</div>
      <div class="logo_inner">
      </div>
    </div>
    </div>
  </div>
</template>

<style scoped>
.header {
  background-color: #e5e5e5;

  position: fixed;
  width: 100%;
  z-index: 1000;
  top: 0;
  transition: transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
}

.header__inner {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 1200px;
  margin: 0 auto;
  padding: 10px;

  overflow: hidden;
}

.hidden {
  transform: translateY(-100px);
}

.header__logo {
  font-size: 35px;
  font-weight: bold;
  font-family: Georgia, 'Times New Roman', Times, serif;
  z-index: 1;
  line-height: 30px;
  color: #fff;
  mix-blend-mode: difference;
}

.logoWrap {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  
  
  padding: 5px;
}

.logo_inner {
  width: 50px;
  height: 50px;
  background-color: #000; /* 검은색 배경 */
  position: absolute;
  z-index: 0;
  top: -5px;
  left: 0;
  border-radius: 50%;
  animation: pulse 2s infinite;
}

@keyframes pulse {
  0% { transform: scale(1.4); }
  63% { transform: scale(1.3); }
  100% { transform: scale(1.4); }
}

.logo_text {
  color: transparent; /* 투명한 글씨 */
  font-size: 12px; /* 글씨 크기 조정 */
  text-align: center; /* 중앙 정렬 */
  line-height: 50px; /* 세로 중앙 정렬 */
  background: linear-gradient(white, white) no-repeat center/100% 50%;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}
</style>
