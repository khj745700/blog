import axiosInstance from "@/api/index.js";


const boardUpdater = (description, title, thumbnailId, boardId, success, fail) => {
    const body = {
        description, thumbnailId, title, boardId
    }
    axiosInstance.put("/boards/update", body).then(success).catch(fail);
}


export default boardUpdater;