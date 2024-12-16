import axiosInstance from "@/api/index.js";


const imageUploadRequest = async (file, success, fail) => {
    const body = new FormData();
    body.append("file", file);
    await axiosInstance.post('/images', body).then(success).catch(fail);
}


export default imageUploadRequest;