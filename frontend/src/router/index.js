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
    meta: {
      title: "홈",
    },
  },
  {
    path: "/trade",
    name: "trade",
    props: true,
    component: TradeView,
    meta: {
      title: "거래 정보",
    },
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
        meta: {
          title: "공지사항",
        },
      },
      {
        path: "detail/:no",
        props: true,
        name: "noticedetail",
        component: () => import("@/components/notice/NoticeDetail.vue"),
        meta: {
          title: "공지사항 조회",
        },
      },
      {
        path: "modify/:no",
        props: true,
        name: "noticemodify",
        component: () => import("@/components/notice/NoticeModify.vue"),
        meta: {
          title: "공지사항 수정",
        },
      },
      {
        path: "register",
        name: "noticeregister",
        component: () => import("@/components/notice/NoticeRegister.vue"),
        meta: {
          title: "공지사항 작성",
        },
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
        meta: {
          title: "Q&A",
        },
      },
      {
        path: "detail/:no",
        props: true,
        name: "qnadetail",
        component: () => import("@/components/qna/QnaDetail.vue"),
        meta: {
          title: "Q&A 조회",
        },
      },
      {
        path: "modify/:no",
        props: true,
        name: "qnamodify",
        component: () => import("@/components/qna/QnaModify.vue"),
        meta: {
          title: "Q&A 수정",
        },
      },
      {
        path: "register",
        name: "qnaregister",
        component: () => import("@/components/qna/QnaRegister.vue"),
        meta: {
          title: "Q&A 읽기",
        },
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: UserView,
    children: [
      {
        path: "interest",
        name: "interest",
        component: () => import("@/components/user/InterestList.vue"),
      },
      {
        path: "modify",
        name: "modify",
        component: () => import("@/components/user/UserInfoTab.vue"),
        meta: {
          title: "마이 페이지",
        },
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
        meta: {
          title: "회원 가입 및 로그인",
        },
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

const makeTitle = (title) => (title ? `${title} | HappyHouse` : "HappyHouse");

router.afterEach((to) => {
  Vue.nextTick(() => {
    document.title = makeTitle(to.meta.title);
  });
});

export default router;
