import axiosInstance from "@/api/index.js";


const boardEnroll = async (boardId) => {
    const body = {
        boardId
    }
    return await axiosInstance.put("/boards/enroll", body);
}


export default boardEnroll;