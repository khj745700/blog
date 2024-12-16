import axiosInstance from "@/api/index.js";

const sessionCheck = (success, fail) => {
    axiosInstance.get("/user/check").then(success).catch(fail);
}


export default sessionCheck;