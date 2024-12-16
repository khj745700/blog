<script setup>

import {onBeforeMount, ref} from "vue";
import {getHashTagAll} from "@/api/hashtag/FindHashTag.js";

const hashTags = ref([]);


onBeforeMount(() => {
  getHashTagAll()
      .then(res => hashTags.value = res.data);
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
  <div v-if="hashTags.length !== 0" class="hashTagContainer">
    <div class="hashTagTitle">TagList</div>
    <div class="borderBottom"></div>
      <div class="hashtag" v-for="hashTag in hashTags" :key="hashTag.hashtagId" @click="$router.push({name: 'main', query:{hashTagId:hashTag.hashtagId, hn:hashTag.hashtag}})">{{hashTag.hashtag}} ({{hashTag.count}})</div>
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

  .hashTagTitle {
    font-size: 1.5rem;
    font-weight: bolder;
  }

  .hashTagContainer {
    display: flex;
    flex-direction: column;
    color: darkgoldenrod;
    gap : 20px;

  }

  .borderBottom {
    border-bottom: 1px solid darkgoldenrod;

  }

</style>