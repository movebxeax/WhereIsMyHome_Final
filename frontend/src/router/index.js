import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/HomeView.vue";
import NoticeView from "@/views/NoticeView.vue";
import QnaView from "@/views/QnaView.vue";
import TradeView from "@/views/TradeView.vue";
import UserView from "@/views/UserView.vue";

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
    redirect: "/notice/list",
    component: NoticeView,
    children: [
      {
        path: "list",
        name: "noticeList",
        component: () => import("@/components/notice/NoticeList.vue"),
      },
      {
        path: "detail/:no",
        props: true,
        name: "noticedetail",
        component: () => import("@/components/notice/NoticeDetail.vue"),
      },
      {
        path: "modify/:no",
        props: true,
        name: "noticemodify",
        component: () => import("@/components/notice/NoticeModify.vue"),
      },
      {
        path: "register",
        name: "noticeregister",
        component: () => import("@/components/notice/NoticeRegister.vue"),
      },
    ],
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
  {
    path: "/user",
    name: "user",
    component: UserView,
    children: [
      {
        path: "modify",
        name: "modify",
        component: () => import("@/components/user/UserInfoTab.vue"),
      },
      {
        path: "signout",
        name: "signout",
        component: () => import("@/components/user/SignOut.vue"),
      },
      {
        path: ":selected",
        name: "selected",
        props: (route) => ({ selected: String(route.params.selected) }),
        component: () => import("@/components/user/SignTab.vue"),
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
