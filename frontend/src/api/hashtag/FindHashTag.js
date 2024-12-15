import axiosInstance from "@/api/index.js";


const getHashTagListForEnroll = async (keyword) => {
    return await axiosInstance.get(`/hashtags/search?keyword=${keyword}`);
}

const getHashTagAll = async () => {
    return await axiosInstance.get(`/hashtags/counts`);
}

export {getHashTagListForEnroll, getHashTagAll};