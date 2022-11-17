import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/HomeView.vue";
import NoticeView from "@/views/NoticeView.vue";
import QnaView from "@/views/QnaView.vue";
import TradeView from "@/views/TradeView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/trade",
    name: "trade",
    component: TradeView,
  },
  {
    path: "/notice",
    name: "notice",
    component: NoticeView,
  },
  {
    path: "/qna",
    name: "qna",
    component: QnaView,
    redirect: "/qna/list",
    children: [
      {
        path: "list",
        name: "qnalist",
        component: () => import("@/components/qna/QnaList.vue"),
      },
      {
        path: "detail/:no",
        props: true,
        name: "qnadetail",
        component: () => import("@/components/qna/QnaDetail.vue"),
      },
      {
        path: "modify/:no",
        props: true,
        name: "qnamodify",
        component: () => import("@/components/qna/QnaModify.vue"),
      },
      {
        path: "register",
        name: "qnaregister",
        component: () => import("@/components/qna/QnaRegister.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
