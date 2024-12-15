<script setup>
import PageContainer from "@/atomic/PageContainer.vue";
import MainBoardComponent from "@/components/MainBoardComponent.vue";
import MainProfileComponent from "@/components/MainProfileComponent.vue";
import MainHashTagListComponent from "@/components/MainHashTagListComponent.vue";
import {useRouter} from "vue-router";
import {onBeforeMount, ref} from "vue";
import sessionCheck from "@/api/user/SessionCheck.js";
import tempBoardRegister from "@/api/board/TempBoardRegister.js";
import {showToast} from "@/utils/toast.js";

const router = useRouter();

const sessions = ref(false);

onBeforeMount(() => {
  sessionCheck(()=> {
    sessions.value = true;
  }, () => {
    sessions.value = false;
  })
});

const boardEnrollButtonClick = () => {
  tempBoardRegister((res) => {
    const boardId = res.data.boardId;
    router.push(`/post/${boardId}`);
  }, () => {showToast('게시글을 생성할 수 없습니다.', 'error')});
}

</script>

<template>
<PageContainer >
  <MainProfileComponent />
  <div class="bodyContainer">
    <MainBoardComponent />
    <div class="hashTagContainer">
      <MainHashTagListComponent style="align-self: flex-start"/>
    </div>
    </div>
  <div v-if="sessions" class="boardEnrollButton" @click="boardEnrollButtonClick">+</div>
</PageContainer>
</template>

<style scoped>
  .bodyContainer {
    display: flex;
    justify-content: center;
    height: 100%;
  }

  .hashTagContainer {
    display: flex;
    flex-direction: column;
    color: darkgoldenrod;
    gap : 20px;

  }

  .boardEnrollButton {
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