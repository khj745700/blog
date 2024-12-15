import axiosInstance from "@/api/index.js";


const getBoardDetails = async (boardId) => {

    return await axiosInstance.get(`/boards/${boardId}`);
}

const getBoardPaging = async (page, size) => {
    return await axiosInstance.get(`/boards/paging?page=${page}&size=${size}`);
}

const getBoardsByHashTag = async (page, size, hashTagId) => {
    return await axiosInstance.get(`/boards/search?page=${page}&size=${size}&hashTagId=${hashTagId}`);
}


export {getBoardDetails, getBoardPaging, getBoardsByHashTag};