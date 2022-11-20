import { aptList, aptListWithCds, searchOptionList } from "@/api/trade";

const tradeStore = {
  namespaced: true,
  state: {
    dong: {},
    searchOptions: [],
    apts: [],
    apt: {},
  },
  getters: {},
  mutations: {
    SET_SEARCH_OPTION_LIST(state, searchOptions) {
      state.searchOptions = searchOptions;
    },
    SET_APT_LIST(state, apts) {
      state.apts = apts;
    },
    CLEAT_APT_LIST(state) {
      state.apts = [];
    },
    SET_DONG(state, dong) {
      state.dong = dong;
    },
  },
  actions: {
    setDong: ({ commit }, dong) => {
      commit("SET_DONG", dong);
    },
    getSearchOptionList: ({ commit }, keyword) => {
      const params = {
        keyword: keyword,
      };
      return searchOptionList(
        params,
        ({ data }) => {
          commit("SET_SEARCH_OPTION_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAptList: ({ commit }, dongcode) => {
      return aptList(
        dongcode,
        ({ data }) => {
          commit("SET_APT_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAptListWithCds: ({ commit }, params) => {
      return aptListWithCds(
        params,
        ({ data }) => {
          console.log(data);
          commit("SET_APT_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    clearAptList: ({ commit }) => {
      commit("CLEAT_APT_LIST");
    },
  },
};

export default tradeStore;
