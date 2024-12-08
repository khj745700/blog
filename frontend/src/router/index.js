import { createRouter, createWebHistory } from "vue-router";
import LoginView from "@/pages/LoginView.vue";
import MainView from "@/pages/MainView.vue";
import ComponentView from "@/pages/ComponentView.vue";
import BoardView from "@/pages/BoardView.vue";
import BoardEditView from "@/pages/BoardEditView.vue";


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
          path: "/board",
          name: "board",
          component: BoardView,
        },
        {
            path: "/component",
            name: "component",
            component: ComponentView,
        },
        {
            path: "/post",
            name: "post",
            component: BoardEditView,
        }
    ]
})

export default router;