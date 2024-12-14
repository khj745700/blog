import axiosInstance from "@/api/index.js";


const getBoardDetails = (boardId, success, fail) => {

    axiosInstance.get(`/boards/${boardId}`).then(success).catch(fail);
}

const getBoardPaging = (page, size, success, fail) => {
    axiosInstance.get(`/boards/paging?page=${page}&size=${size}`).then(success).catch(fail);
}

const getBoardsByHashTag = (page, size, hashTagId, success, fail) => {
    axiosInstance.get(`/boards/search?page=${page}&size=${size}&hashTagId=${hashTagId}`).then(success).catch(fail);
}


export default {getBoardDetails, getBoardPaging, getBoardsByHashTag};