import { createRouter, createWebHistory } from "vue-router";
import LoginView from "@/pages/LoginView.vue";
import MainView from "@/pages/MainView.vue";
import ComponentView from "@/pages/ComponentView.vue";


const router = createRouter({
    history : createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "main",
            component: MainView,
        },
        {
            path: "/login",
            name: "login",
            component: LoginView,
        },
        {
            path: "/component",
            name: "component",
            component: ComponentView,
        },
    ]
})

export default router;