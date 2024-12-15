<script setup>

import {onBeforeMount, ref} from "vue";
import {getHashTagAll} from "@/api/hashtag/FindHashTag.js";

const hashTags = ref([]);


onBeforeMount(() => {
  getHashTagAll()
      .then(res => console.log(res.data));
});

//
// const hashTags = [
//   {
//     id : 1,
//     value: 'MySQL',
//     count: 4
//   },
//   {
//     id : 2,
//     value: 'Redis',
//     count: 5
//   },
//   {
//     id : 3,
//     value: 'Spring Data Jpa',
//     count: 7
//   },
//   {
//     id : 4,
//     value: 'Spring Framework',
//     count: 6
//   },
// ];
</script>


<template>
  <div>
      <div class="hashtag" v-for="hashTag in hashTags" :key="hashTag.id">{{hashTag.value}} ({{hashTag.count}})</div>
  </div>
</template>


<style scoped>

  .hashtag{
    margin-bottom: 8px;
    font-family: 'ptd';
    font-size: 1rem;

    &:hover {
      text-decoration: underline;
    }
  }
</style>