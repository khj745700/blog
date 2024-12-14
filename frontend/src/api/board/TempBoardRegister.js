import axiosInstance from "@/api/index.js";


const tempBoardRegister = (success, fail) => {
    axiosInstance.post("/boards/").then(success).catch(fail);
}


export default tempBoardRegister;