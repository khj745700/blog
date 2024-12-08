<script setup>

import HashTagListComponent from "@/components/HashTagListComponent.vue";

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
  <div class="boardContainer">
    <div class="componentContainer" v-for="board in boards" key="board.id">
      <div class="dummyImage"></div>
      <div class="componentBody">
        <p class="componentDate">{{board.date}}</p>
        <p class="componentTitle">{{board.title}}</p>

        <HashTagListComponent :hash-tags="board.hashTags"/>
      </div>
    </div>
  </div>
</template>

<style scoped>

.boardContainer {
  display: flex;
  width: calc(54rem + 60px);

  height: 100%;
  flex-wrap: wrap;
  gap: 30px;
  justify-content: center;
}


.componentContainer {
  width: 27rem;
  display: flex;
  flex-direction: column;
  border: 1px solid darkgoldenrod;
  position: relative;

  transition: transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1), box-shadow 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
  &:hover {
    transform : translate(-5px, -5px);
    box-shadow: 5px 5px 5px dimgrey;
  }


}

.dummyImage {
  width: 100%;
  height: 15rem;
  background-color: lightgray;
}

.componentTitle {
  font-size: 1.5rem;
  font-family: 'ptd';
  font-weight: 700;
  color: darkgoldenrod;
  margin-top: -0.8rem;
  flex-grow: 1;
}

.componentBody {
  padding: 10px 20px;
  display: flex;
  flex-grow: 1;
  flex-direction: column;

}

.componentDate {
  color: #a1a1a1;
  font-size: 0.9rem;
  text-align: right;
  margin-bottom: 1.3rem;
}

.hashTagContainer {
  align-self: flex-end;
}


</style>