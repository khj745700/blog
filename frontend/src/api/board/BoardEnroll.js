import axiosInstance from "@/api/index.js";


const boardEnroll = (boardId, success, fail) => {
    const body = {
        boardId
    }
    axiosInstance.put("/boards/enroll", body).then(success).catch(fail);
}


export default boardEnroll;