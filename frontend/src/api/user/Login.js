import axiosInstance from "@/api/index.js";

const loginRequest = (username, password, success, fail) => {
    const body = {
        username,
        password
    }
    axiosInstance.post("/auth/login", body).then(success).catch(fail)
}

export default loginRequest;

