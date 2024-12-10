import { toast } from "vue3-toastify";
import "vue3-toastify/dist/index.css";

export const showToast = (message, type = "info", options = {}) => {
    toast(message, {
        type: type,
        position: "top-right",
        autoClose: 2000,
        theme: "light",
        hideProgressBar: false,
        ...options,
    });
}