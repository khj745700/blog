<script setup>
import PageContainer from "@/atomic/PageContainer.vue";
import MainBoardComponent from "@/components/MainBoardComponent.vue";
import MainProfileComponent from "@/components/MainProfileComponent.vue";

const boards = [
  {
    id: 1,
    title: "안녕하세요, 게시판입니다.",
    date: "2024-09-24",
    hashTags: ["MySQL", "Redis", "Spring Data Jpa", "Spring Framework", "Spring Boot", "Linux"]
  },
  {
    id: 2,
    title: "안녕하세요, 게시판입니다. 안녕하세요, 게시판입니다. 안녕하세요, 게시판입니다.",
    date: "2024-09-24",
    hashTags: ["MySQL", "Redis", "Spring Data Jpa", "Spring Framework", "Spring Boot", "Linux"]
  },
  {
    id: 3,
    title: "안녕하세요, 게시판입니다. 안녕하세요, 게시판입니다. 안녕하세요, 게시판입니다. 안녕하세요, 게시판입니다. 안녕하세요, 게시판입니다.",
    date: "2024-09-24",
    hashTags: ["MySQL", "Redis", "Spring Data Jpa", "Spring Framework", "Spring Boot", "Linux"]
  },
  {
    id: 4,
    title: "안녕하세요, 게시판입니다. 안녕하세요, 게시판입니다. 안녕하세요, 게시판입니다.",
    date: "2024-09-24",
    hashTags: ["MySQL", "Redis", "Spring Data Jpa", "Spring Framework", "Spring Boot", "Linux"]
  },
];
</script>

<template>
<PageContainer >
  <MainProfileComponent />
  <div class="boardContainer">
    <MainBoardComponent v-for="board in boards" key="board.id" :title="board.title" :date="board.date" :hashtags="board.hashTags"/>
  </div>
</PageContainer>
</template>

<style scoped>
  .boardContainer {
    display: flex;
    width: 60%;
    height: 100%;
    flex-wrap: wrap;
    gap: 30px;

    justify-content: center;
  }
</style>