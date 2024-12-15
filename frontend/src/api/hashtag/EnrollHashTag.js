import axiosInstance from "@/api/index.js";


const enrollHashTag = async (keyword) => {
    const body = {
        hashTag: keyword
    }
    return await axiosInstance.put('/hashtags', body);
}

export default enrollHashTag;