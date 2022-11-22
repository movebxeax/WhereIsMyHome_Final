import Constant from "@/utils/constant.js";

const userStore = {
  namespaced: true,
  getters: {
    isLogin(state) {
      return state.isLogin;
    },
    userInfo(state) {
      return state.userInfo;
    },
    accessToken(state) {
      return state.accessToken;
    },
    refreshToken(state) {
      return state.refreshToken;
    },
  },
  mutations: {
    [Constant.SET_ACCESS_TOKEN](state, accessToken) {
      state.accessToken = accessToken;
    },
    [Constant.SET_REFRESH_TOKEN](state, refreshToken) {
      state.refreshToken = refreshToken;
    },
    [Constant.SET_LOGIN_STATE](state, payload) {
      state.isLogin = payload;
    },
    [Constant.SET_USER_INFO](state, userInfo) {
      state.userInfo = userInfo;
    },
  },
  actions: {
    [Constant.SAVE_TOKENS](state, { accessToken, refreshToken }) {
      state.commit(Constant.SET_ACCESS_TOKEN, accessToken);
      state.commit(Constant.SET_REFRESH_TOKEN, refreshToken);
    },
    [Constant.SET_LOGIN_STATE](state, isLoggedin) {
      state.commit(Constant.SET_LOGIN_STATE, isLoggedin);
    },
    [Constant.SET_USER_INFO](state, userInfo) {
      state.commit(Constant.SET_USER_INFO, userInfo);
    },
  },
};

export default userStore;
