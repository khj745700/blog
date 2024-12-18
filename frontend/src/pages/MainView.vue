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

sessionCheck(()=> {
  sessions.value = true;
}, () => {
  sessions.value = false;
})


const boardEnrollButtonClick = () => {
  tempBoardRegister((res) => {
    const boardId = res.data.boardId;
    router.push(`/post/${boardId}`);
  }, () => {showToast('게시글을 생성할 수 없습니다.', 'error')});
}

</script>

<template>
  <PageContainer>
    <MainProfileComponent />

    <div style="position: relative">
      <div class="bodyContainer">
        <MainBoardComponent />

      </div>
      <div class="hashTagContainer">
        <MainHashTagListComponent/>
      </div>
    </div>
    <div v-if="sessions" class="boardEnrollButton" @click="boardEnrollButtonClick">+</div>
  </PageContainer>


</template>

<style scoped>
.bodyContainer {
  display: flex;
  justify-content: space-evenly;
  height: 100%;
  width: 100%;
}

.hashTagContainer {
  display: flex;
  flex-direction: column;
  color: darkgoldenrod;
  gap : 20px;

  width: 200px;

  position: absolute;
  top: 0;
  right: -130px;
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

@media screen and (max-width: 1400px) {
  .hashTagContainer {
    display: none;

  }
}



</style>