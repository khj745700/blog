import axiosInstance from "@/api/index.js";

const getProfile = async () => {
    return await axiosInstance.get("/user/profile/1")
}


export default getProfile;