<script setup>

const props = defineProps({
  title: String,
  date: String,
  hashtags: Array,
})
</script>

<template>
  <div class="componentContainer">
    <div class="dummyImage"></div>
    <div class="componentBody">
      <p class="componentDate">{{date}}</p>
      <p class="componentTitle">{{title}}</p>

      <div class="hashTagContainer">
          <span class="hashTags" v-for="hashtag in hashtags" key="hashtag">
          #{{hashtag}}
        </span>
      </div>
    </div>
  </div>
</template>

<style scoped>

.componentContainer {
  width: 27rem;
  display: flex;
  flex-direction: column;
  border: 1px solid darkgoldenrod;
  position: relative;

  transition: transform 0.3s cubic-bezier(0.165, 0.84, 0.44, 1), box-shadow 0.3s cubic-bezier(0.165, 0.84, 0.44, 1);
  &:hover {
    transform : translate(-5px, -5px);
    box-shadow: 5px 5px 5px grey;
  }

  &:hover:after {
    content: '보러가기';
    position: absolute;
    display: flex;
    align-items: flex-end;
    justify-content: flex-end;
    width: calc(100% - 2rem);
    height: calc(100% - 2rem);
    padding: 1rem 1rem;
    font-family: 'ptd';
    font-weight: 600;
    color: black;
    mix-blend-mode: difference;
    background-color: rgba(50,50,50, 0.1);
  }

}

.dummyImage {
  width: 100%;
  height: 15rem;
  background-color: lightgray;

}

.componentTitle {
  font-size: 1.5rem;
  font-family: 'ptd';
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

.hashTagContainer {

  align-self: flex-end;
}

.hashTags {
  border: 1px solid darkgoldenrod;
  border-radius: 8px;
  color: darkgoldenrod;
  display: inline-block;
  margin-right: 1rem;
  padding: 2px 5px;
  margin-bottom: 10px;
  font-weight: 400;
  font-size: 0.9rem;
  transition: background-color 0.1s ease-in-out, color 0.1s ease;

  &:hover {
    background-color: darkgoldenrod;
    color: white;
  }
}
</style>