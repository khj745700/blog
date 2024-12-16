<script setup>
import HashTagListComponent from "@/components/HashTagListComponent.vue";
import {onBeforeMount, ref, onMounted, onUnmounted} from "vue";
import {onBeforeRouteUpdate, useRoute, useRouter} from "vue-router";
import {getBoardPaging, getBoardsByHashTag} from "@/api/board/FindBoard.js";

const route = useRoute();

const boards = ref([]);  // 게시글 목록
let page = ref(0);       // 페이지 번호
let isEnd = ref(false);  // 마지막 페이지 여부
let isLoading = ref(false); // 데이터 요청 중 여부
const hashTag = ref(null);

const router = useRouter();



// 데이터 불러오는 함수
const loadBoards = () => {
  if (isEnd.value || isLoading.value) return; // 이미 마지막 페이지이거나 로딩 중이면 중단
  isLoading.value = true;

  if (route.query.hashTagId != null) {
    getBoardsByHashTag(page.value, 20, route.query.hashTagId).then((res) => {
      const body = res.data;
      boards.value.push(...body.content);
      isEnd.value = body.end;
      page.value += 1; // 다음 페이지 준비
      isLoading.value = false;
    });
  } else {
    getBoardPaging(page.value, 20).then((res) => {
      const body = res.data;
      boards.value.push(...body.content);
      isEnd.value = body.end;
      page.value += 1; // 다음 페이지 준비
      isLoading.value = false;
    });
  }
};

loadBoards();

if(route.query.hn != null) {
  hashTag.value = route.query.hn ;
}

// 스크롤 이벤트 핸들러
const handleScroll = () => {
  const bottomReached =
      window.innerHeight + window.scrollY >= document.documentElement.scrollHeight - 10;

  if (bottomReached) {
    loadBoards(); // 스크롤이 맨 아래로 내려갔을 때 다음 페이지 불러오기
  }
};

// 컴포넌트 마운트 시 초기 데이터 로드 및 이벤트 추가
onBeforeMount(() => {

});

onBeforeRouteUpdate((to, from) => {
  hashTag.value = to.query.hn;
})

onMounted(() => {
  loadBoards();


  window.addEventListener("scroll", handleScroll);
});

// 컴포넌트 언마운트 시 이벤트 제거
onUnmounted(() => {
  window.removeEventListener("scroll", handleScroll);
});

// 날짜 파싱 함수
const splitDate = (date) => date.split("T")[0];

const hashTagClickEvent = (event) => {
  event.stopPropagation();
  const id = event.target.dataset.id;
  const hashtag = event.target.innerText.replace("#","");

  router.push({
    name: 'main',
    query: {hashTagId : id, hn : hashtag}
  })
}
</script>

<template>
  <div class="boardContainer">
    <div>
      <div v-if="boards.length === 0">게시글이 없습니다.</div>
      <div v-if="boards.length !== 0 && route.query.hashTagId != null"><strong id="hashTagTitle">{{hashTag}}</strong>으로 검색된 결과의 게시글입니다.</div>
    </div>

    <div class="boardContainer">
      <div class="componentContainer" v-for="board in boards" :key="board.id" @click="$router.push({name : 'board', params:{id:board.id}})">
        <div v-if="board.thumbnailUrl == null" class="dummyImage"></div>
        <img :src="board.thumbnailUrl" class="dummyImage">
        <div class="componentBody">
          <p class="componentDate">{{ splitDate(board.wroteDate) }}</p>
          <p class="componentTitle">{{ board.title }}</p>
          <HashTagListComponent :hash-tags="board.hashTags" :hash-tag-click-event="hashTagClickEvent"/>
        </div>
      </div>
    </div>
    <!-- 로딩 표시 -->

  </div>
</template>

<style scoped>
.boardContainer {
  display: flex;
  width: calc(54rem + 60px);
  flex-wrap: wrap;
  gap: 30px;
  justify-content: center;
}

#hashTagTitle {
  font-size: 1.3rem;
}

.componentContainer {
  width: 27rem;
  display: flex;
  flex-direction: column;
  border: 1px solid darkgoldenrod;
  transition: transform 0.3s, box-shadow 0.3s;
}

.componentContainer:hover {
  transform: translate(-5px, -5px);
  box-shadow: 5px 5px 5px dimgrey;
}

.dummyImage {
  width: 100%;
  height: 15rem;
  background-color: lightgray;
}

.componentTitle {
  font-size: 1.5rem;
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

.loading {
  text-align: center;
  font-size: 1.2rem;
  color: gray;
}

.endMessage {
  text-align: center;
  font-size: 1.2rem;
  color: darkred;
  margin-top: 10px;
}
</style>