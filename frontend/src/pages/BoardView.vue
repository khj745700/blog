<script setup>

import PageContainer from "@/atomic/PageContainer.vue";
import {computed, onBeforeMount, ref} from "vue";
import MarkdownViewerComponent from "@/components/MarkdownViewerComponent.vue";
import HashTagListComponent from "@/components/HashTagListComponent.vue";
import getProfile from "@/api/user/Profile.js";
import {getBoardDetails} from "@/api/board/FindBoard.js";
import {useRoute} from "vue-router";
import sessionCheck from "@/api/user/SessionCheck.js";


const nickname = ref('');

const title = ref('');
const date = ref('');
const markdown = ref('');

const sessions = ref(false);

onBeforeMount(() => {

});
const hashTags = ref([]);

const route = useRoute();

onBeforeMount(async () => {
  sessionCheck(()=> {
    sessions.value = true;
  }, () => {
    sessions.value = false;
  })

  await getProfile().then((res) => {
    nickname.value = res.data.nickname;
  })

  await getBoardDetails(route.params.id)
      .then(res => {
        const body = res.data;
        title.value = body.title;
        markdown.value = body.description;
        date.value = body.wroteDate.split('T')[0];
        hashTags.value = body.hashTags;
      })
});
</script>


<template>
<PageContainer>
  <div class="body" >
    <div class="title">{{title}}</div>
    <div class="semiProfileContainer">
      <div style="display: flex; gap: 1rem;">
        <p style="color: darkgoldenrod; font-weight: bolder; font-size: 1.1rem">@{{nickname}}</p> <p class="date">{{date}}</p>
      </div>
      <HashTagListComponent :hash-tags="hashTags"/>
    </div>
    <MarkdownViewerComponent class="markdownContainer" :markdown="markdown"/>
  </div>
  <div v-if="sessions" class="boardUpdateButton" @click="$router.push({name: 'post', params:{id:$route.params.id}})">✎</div>
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

  .markdownContainer {
    padding: 5rem 1rem;
  }

  .boardUpdateButton {
    border-radius: 50%;
    background-color: darkgoldenrod;
    position: fixed;
    bottom: 4rem;
    right: 4rem;
    width: 4rem;
    height: 4rem;
    text-align: center;
    color: white;
    font-size: 4rem;
  }
</style>