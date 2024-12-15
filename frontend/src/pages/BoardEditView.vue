<script setup>

import PageContainer from "@/atomic/PageContainer.vue";
import {VMarkdownEditor} from "vue3-markdown";
import 'vue3-markdown/dist/style.css'

import {onBeforeMount, ref} from 'vue';
import InputBox from "@/atomic/InputBox.vue";
import HashTagListComponent from "@/components/HashTagListComponent.vue";
import imageUploadRequest from "@/api/image/ImageUpload.js";
import {getBoardDetails} from '@/api/board/FindBoard.js';
import {useRoute, useRouter} from "vue-router";
import {showToast} from "@/utils/toast.js";
import getHashTagListForEnroll from "@/api/hashtag/FindHashTag.js";
import enrollHashTag from "@/api/hashtag/EnrollHashTag.js";
import boardUpdater from "@/api/board/BoardUpdate.js";
import boardEnroll from "@/api/board/BoardEnroll.js";

const handleUpload = async (file) => {
  console.log(file)
  let path;
  await imageUploadRequest(file, res => path = res.headers['location'], err => console.error(err));
  console.log(path);
  return path;
}

const router = useRouter();
const route = useRoute();

const hashtags = ref([]);

const text = ref('');
const title = ref('');
const thumbnail = ref('');

async function getFileName(files) {
  console.log(files);
  const file = files[0];
  await imageUploadRequest(file, (res)=> {
    thumbnail.value = res.headers['location'];
  }), (err) => {showToast(err, 'error', {autoClose: false})};
  await setThumbnail();
}

async function setThumbnail() {
  // 비동기적으로 동작하기 위하여 promise를 return 해준다.
    const previewImage = document.getElementById('preview');
    previewImage.src = thumbnail.value;

}

onBeforeMount(() => {
  getBoardDetails(route.params.id)
      .then(res => {
        const body = res.data;
        title.value = body.title;
        thumbnail.value = body.thumbnailUrl;
        // hashtags.value = body.hashTags;
      });
});

const hashtagClickEvent = (event) => {
  const target = event.target.dataset.id;
  hashtags.value = hashtags.value.filter(hashtag => hashtag.hashtagId != target);
}

const hashtag = ref('');

const hashTagListForEnroll = ref([]);

const hashtagBoxHandler = async (event) => {
  if(event.keyCode == 13) {
    // 엔터 입력했을 때
    if(hashtag.value == undefined || hashtag.value === '') {
      return;
    }

    if(hashTagListForEnroll.value.length === 0) {
      await enrollHashTag(hashtag.value).then(res => {
        const body = res.data;
        hashtags.value.push(body);
      });
    }else {
      hashtags.value.push(hashTagListForEnroll.value[0]);
      hashTagListForEnroll.value = [];
    }
    hashtag.value = '';

    return;
  }


}

const hashtagInput = async (event) => {
  hashtag.value = event.target.value;

  await getHashTagListForEnroll(hashtag.value).then(res => {
    hashTagListForEnroll.value = res.data;
  });
}

const boardUpdateAction = async () => {
  const hashTagIds = hashtags.value.map(hashtag => hashtag.hashtagId);
  return boardUpdater(text.value, title.value, thumbnail.value, route.params.id, hashTagIds);
}

const boardEnrollAction = async () => {
  return boardEnroll(route.params.id);
}

const boardAndEnrollAction = async () => {
  boardUpdateAction().then(() => {
    boardEnroll()
        .then(() => router.push('/'))
        .catch((err) => showToast(`게시글 등록에 실패했습니다. errorCode : ${err.status}`));
  }).catch((err) => {
    console.log(err);
    showToast(`게시글 저장에 실패했습니다. errorCode : ${err}`);
  });
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
            <input :type="text"
                   :value="hashtag"
                   @keyup="hashtagBoxHandler"
                   @input="hashtagInput"
            />
            <HashTagListComponent :hash-tags="hashTagListForEnroll"></HashTagListComponent>
          </div>
          <HashTagListComponent :hash-tags="hashtags" :hash-tag-click-event="hashtagClickEvent"></HashTagListComponent>
        </div>
        <div style="display: flex; gap: 10px">
          <div class="button" @click="boardUpdateAction">임시 저장하기</div>
          <div class="button" @click="boardAndEnrollAction">저장하기</div>
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