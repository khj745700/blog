<script setup>
import PageContainer from "@/atomic/PageContainer.vue";
import MainBoardComponent from "@/components/MainBoardComponent.vue";
import MainProfileComponent from "@/components/MainProfileComponent.vue";
import MainHashTagListComponent from "@/components/MainHashTagListComponent.vue";


</script>

<template>
<PageContainer >
  <MainProfileComponent />
  <div class="bodyContainer">
    <MainBoardComponent />
    <div class="hashTagContainer">
      <div class="hashTagTitle">TagList</div>
      <div class="borderBottom"></div>
      <MainHashTagListComponent style="align-self: flex-start"/>
    </div>
    </div>
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

  .hashTagTitle {
    font-size: 1.5rem;
    font-weight: bolder;
  }

  .borderBottom {
    border-bottom: 1px solid darkgoldenrod;

  }
</style>