const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    userInfo: null,
  },
  getters: {
    userInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {},
  actions: {},
};

export default userStore;
