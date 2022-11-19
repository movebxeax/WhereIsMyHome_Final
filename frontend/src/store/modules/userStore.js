import Constant from "@/utils/constant.js";
const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    userInfo: null,
    accessToken: "",
    refreshToken: "",
  },
  getters: {
    userInfo: function (state) {
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
  },
  actions: {
    [Constant.SAVE_TOKENS](state, { accessToken, refreshToken }) {
      state.commit(Constant.SET_ACCESS_TOKEN, accessToken);
      state.commit(Constant.SET_REFRESH_TOKEN, refreshToken);
    },
  },
};

export default userStore;
