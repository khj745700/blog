<script setup>
  import { ref, onMounted } from 'vue'
  import axios from "axios";
  import { marked } from 'marked';
  import { markedHighlight } from "marked-highlight";
  import hljs from 'highlight.js';
  import 'highlight.js/styles/github-dark.css';
  import 'vue3-markdown/dist/style.css'

  const markdown = ref(`
  # 한국어

  ## 한국어1
  ## Subheading 1.2
  ### Subheading 1.2.1
  # Heading 2
  ## Subheading 2.1

  > # this is h1!
  > * list
  > \`textbox\`
`);
  const markdownHtml = ref('');
  const toc = ref([]);

  const markdownToHtml = (() => {

    marked.use(markedHighlight({
      langPrefix: 'hljs language-',
      highlight(code, lang) {
        const language = hljs.getLanguage(lang) ? lang : 'plaintext';
        return hljs.highlight(code, { language }).value;
      }
    }));


    const renderer = new marked.Renderer();
    let i = 1;
    renderer.heading = ({tokens, depth}) => {

      if (depth === 1 || depth === 2 || depth === 3) {
        // const slug = tokens[0].raw.toLowerCase().replace(/[^\w]+/g, '-'); // Slugify 제목
        const slug = 'slug'+i;
        toc.value.push({ depth, text:tokens[0].text, slug }); // 목차 데이터 추가
        i++;
        return `<h${depth} id="${slug}">${tokens[0].text}</h${depth}>`;
      }
      return `<h${depth}>${tokens[0].text}</h${depth}>`;
    };

    // renderer.heading = ({ tokens, depth }) => {
    //   marked.walkTokens(tokens, (token) => {
    //     if( token.type === 'heading') {
    //
    //     }
    // };

    const output = marked(markdown.value, {renderer, gfm: true});
    return output;
  });

  const nav = ref(null);

  const currentSection = ref(null);
  let beforeSection = null;

  onMounted(async () => {
    // const path = 'https://raw.githubusercontent.com/khj745700/khj745700/refs/heads/main/README.md';
    // await getMarkdown(path);

    markdownHtml.value = await markdownToHtml();

    if(toc.value.length !== 0) {
      currentSection.value = toc.value[0].slug;
      beforeSection = toc.value[0].slug;
    }

    const ul = nav.value;

    for (const li of ul.querySelectorAll('li')) {

      li.addEventListener('click', () => {
        const scrollPosition = document.querySelector(li.dataset.target).offsetTop - 150;

        window.scrollTo({top: scrollPosition+10, behavior: 'smooth'});

      })
    }


    window.addEventListener('scroll', handleScroll);
  });


  const handleScroll = () => {

    let activeSection = beforeSection;

    for(let i = 0 ; i < toc.value.length; i++) {
      const element = document.getElementById(toc.value[i].slug);
      const nowOffsetTop = element.offsetTop;
      let nextOffsetTop =  document.body.offsetHeight;
      if(i + 1 < toc.value.length) {
        const nextElement = document.getElementById(toc.value[i+1].slug);
        nextOffsetTop = nextElement.offsetTop;
      }

      if(nowOffsetTop - 150 < scrollY  && scrollY < nextOffsetTop - 150) {
        activeSection = toc.value[i].slug;
        beforeSection = activeSection;
      }
    }
    // 활성화된 섹션 업데이트
    currentSection.value = activeSection;
  }

  const getMarkdown = async (path) => {
    try {
      const response = await axios.get(path);
      markdown.value = response.data;
    } catch (error) {
      console.error(error);
    }
  };

</script>

<template>
  <div style="width:100%; display: flex">
    <div class="markdownContainer">
      <section class="markdown-body custom" data-theme="light" v-html="markdownHtml"></section>
    </div>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <ul ref="nav">
      <li
          v-for="content in toc"
          :key="content.slug"
          :data-target="'#' + content.slug"
          :class="{ active: currentSection === content.slug }"
          :style="{ paddingLeft: (content.depth - 1) * 20 + 'px', fontSize: (1.2 - content.depth/13) + 'rem' }"
      >
        {{ content.text }}</li>
    </ul>
  </div>
</template>

<style scoped>
.markdownContainer {
  width: 100%; /* 부모 컨테이너에 딱 맞추기 */
  box-sizing: border-box; /* 패딩 포함해 전체 크기 조정 */
}

.custom {
  height: auto; /* 세로 크기는 콘텐츠에 맞게 조정 */
  word-wrap: break-word; /* 긴 단어 줄바꿈 */
  box-sizing: border-box; /* 패딩 포함 크기 계산 */
}

.custom :deep(code:not([class])) {
  padding: 2px 4px;
  font-size: 90%;
  color: #c7254e;
  background-color: #f9f2f4;
  border-radius: 4px;
}

.custom :deep(img) {
  max-width: 100%; /* 이미지가 컨테이너를 넘지 않도록 설정 */
  height: auto; /* 비율을 유지하면서 높이를 조정 */
}

.custom :deep(h2:after) {
  display: none;
}


ul {
  list-style-type: none;
  position: fixed;
  left: 50px;
  border-left: 1px solid darkgoldenrod;
  margin-left: 10px;
  padding-left: 10px;
}

ul li {
  color: #999999;
  margin-bottom: 5px;
  transition: font-weight 0.07s ease;
}

.active {
  color : darkgoldenrod;
  font-weight: bolder;
}

</style>