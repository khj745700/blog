<script setup>

import PageContainer from "@/atomic/PageContainer.vue";
import {VMarkdownEditor} from "vue3-markdown";
import 'vue3-markdown/dist/style.css'

import {computed, ref} from 'vue';
import InputBox from "@/atomic/InputBox.vue";
import RippleButton from "@/atomic/RippleButton.vue";
import HashTagListComponent from "@/components/HashTagListComponent.vue";
import imageUploadRequest from "@/api/image/ImageUpload.js";

const handleUpload = async (file) => {
  console.log(file)
  let path;
  await imageUploadRequest(file, res => path = res.headers['location'], err => console.error(err));
  console.log(path);
  return path;
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

async function getFileName(files) {
  console.log(files);
  const fileName = files[0];
  imageUploadRequest(fileName, (res)=>console.log(res.headers['location']), err => console.error(err));
  await uploadFile(fileName);

}

function uploadFile(file) {
  // 비동기적으로 동작하기 위하여 promise를 return 해준다.
  return new Promise(resolve => {
    // 업로드된 파일을 읽기 위한 FileReader() 객체 생성
    let a = new FileReader()
    // 읽기 동작이 성공적으로 완료됐을 때 발생
    a.onload = e => {
      resolve(e.target.result)
      // 썸네일을 보여주고자 하는 <img>에 id값을 가져와 src에 결과값을 넣어준다.
      const previewImage = document.getElementById('preview')
      previewImage.src = e.target.result
    }
    // file 데이터를 base64로 인코딩한 문자열. 이 문자열을 브라우저가 인식하여 원래 데이터로 만들어준다.
    a.readAsDataURL(file)
  })
}

</script>


<template>
  <PageContainer>
    <div class="titleContainer">
      <div class="titleBody">
        <InputBox  input-type="text" placeholder="제목을 입력하세요" ></InputBox>
      </div>
      <div style="display: flex; justify-content: center; align-items: center">
        <input type="file" id="upload-thumbnail" hidden @change="getFileName($event.target.files)"/>
        <label for="upload-thumbnail">
          <img src="@/assets/camera.png" id="preview"/>
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

  #preview {
    width: 124px;
    height: 124px;
  }

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