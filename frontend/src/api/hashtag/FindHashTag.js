import axiosInstance from "@/api/index.js";


const getHashTagListForEnroll = async (keyword) => {
    return await axiosInstance.get(`/hashtags/search?keyword=${keyword}`);
}

export default getHashTagListForEnroll;