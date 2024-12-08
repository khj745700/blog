<script setup>

import PageContainer from "@/atomic/PageContainer.vue";
import {computed, ref} from "vue";
import MarkdownViewerComponent from "@/components/MarkdownViewerComponent.vue";
import HashTagListComponent from "@/components/HashTagListComponent.vue";



const title = ref('안녕하세요, 개발자 김현진입니다.');
const date = ref('2024-09-25');




const hashTags = ref(["MySQL", "Redis", "Spring Data Jpa", "Spring Framework", "Spring Boot", "Linux"]);
</script>


<template>
<PageContainer>
  <div class="body" >
    <div class="title">{{title}}</div>
    <div class="semiProfileContainer">
      <div style="display: flex; gap: 1rem;">
        <p style="color: darkgoldenrod; font-weight: bolder; font-size: 1.1rem">@KimHyunJin</p> <p class="date">{{date}}</p>
      </div>
      <HashTagListComponent :hash-tags="hashTags"/>
    </div>
    <MarkdownViewerComponent/>
  </div>
</PageContainer>

</template>


<style scoped>
  .title {
    font-weight: 500;
    font-family: 'ptd';
    font-size: 2rem;
    width: 100%;
    padding-bottom: 1.5rem;
  }

  .date {
    font-family: "Noto Sans KR", sans-serif;
    font-weight: lighter;
    color: #a1a1a1;
  }

  .body {
    width: 64%;
    height: 100%;
    margin-top: 100px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }

  .semiProfileContainer {
    display: flex;
    gap: 0.5rem;
    width: 100%;

    border-bottom: 1px solid darkgoldenrod;
    flex-direction: column;
    padding-bottom: 15px;
  }
</style>