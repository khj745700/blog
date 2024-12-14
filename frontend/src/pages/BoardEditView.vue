<script setup>

import PageContainer from "@/atomic/PageContainer.vue";
import {VMarkdownEditor} from "vue3-markdown";
import 'vue3-markdown/dist/style.css'

import {computed, ref} from 'vue';
import InputBox from "@/atomic/InputBox.vue";
import RippleButton from "@/atomic/RippleButton.vue";
import HashTagListComponent from "@/components/HashTagListComponent.vue";

const handleUpload = async (file) => {
  console.log(file)
  return 'https://i.postimg.cc/52qCzTVw/pngwing-com.png'
}

const hashtags = ref([
  {
    name: "MySQL",
    hashTagId: 1,
  },
  {
    name: "운영체제",
    hashTagId: 2,
  }
]);

const text = ref('# Hello Editor');

const hashtagComputed = computed(() => {
  return hashtags.value.map(hashtag => hashtag.name);
})

</script>


<template>
  <PageContainer>
    <div class="titleContainer">
      <div class="titleBody">
        <InputBox  input-type="text" placeholder="제목을 입력하세요"></InputBox>
      </div>
      <div style="display: flex; justify-content: center; align-items: center">
        <input type="file" id="upload-thumbnail" hidden/>
        <label for="upload-thumbnail">
          <img src="@/assets/camera.png" />
        </label>
      </div>
    </div>
    <div class="editorBody">
      <VMarkdownEditor class="editor" v-model="text" :upload-action="handleUpload" locale="en"></VMarkdownEditor>

      <div style="display: flex; gap : 10px; justify-content: space-between">
        <div style="align-self: flex-start; display: flex; gap : 10px; align-items: baseline">
          <div style="border: 1px solid black;">
            <InputBox  input-type="text" placeholder="해쉬태그를 입력하세요"></InputBox>
          </div>
          <HashTagListComponent :hash-tags="hashtagComputed"></HashTagListComponent>
        </div>
        <div style="display: flex; gap: 10px">
          <div class="button">임시 저장하기</div>
          <div class="button">저장하기</div>
        </div>
      </div>
    </div>

  </PageContainer>
</template>


<style scoped>
  .titleBody {

    border: 1px solid black;
    width: 70%;

  }

  .titleContainer {
    width: 100%;
    display: flex;
    justify-content: space-evenly;
    align-items: center;

    margin-top: 100px;
  }

  .editorBody {
    padding: 0 1rem;
    width: 93vw;
    height: auto;
    display: flex;
    flex-direction: column;
    gap: 10px;
  }

  .editor {
    min-height: 70vh ;

    width: auto;
    margin-top: 30px;

  }

  .button {
    align-self: flex-end;
    border: 1px solid forestgreen;
    padding : 1rem 1rem;
    border-radius: 8px;
    font-family: 'ptd';
    font-weight: 700;
    color: forestgreen;

    transition: background-color 0.1s ease-in-out, color 0.1s ease-in-out;
    &:hover {
      color: white;
      background-color: forestgreen;
    }
  }

</style>