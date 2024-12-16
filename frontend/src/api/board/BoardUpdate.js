import axiosInstance from "@/api/index.js";


const boardUpdater = async (description, title, thumbnailUrl, boardId, hashTagIds) => {
    const body = {
        description, thumbnailUrl, title, boardId, hashTagIds
    }
    console.log(body);
    return await axiosInstance.put("/boards/update", body);
}


export default boardUpdater;